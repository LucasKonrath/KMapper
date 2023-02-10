package org.kmapper

import com.squareup.kotlinpoet.*
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import org.kmapper.converters.Converters
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.*

class KMapper {

    val converters = Converters()
    private val cache = TimedCache()

    /**
     * Function called to convert an instance of an object to an instance of the specified KClass.
     * It'll get the origin class, insert all of its member properties onto a map with their corresponding name
     * (if MapperModelField is present, it'll get the destinationField value, else it'll get the property name).
     * Then the method will instantiate the destination object with the biggest constructor available, and will populate
     * the object with the values from the original object. The remaining member properties will be set via Reflection.
     *
     * For inserting each value on the destination class, a Converter is used. Converters are used to convert between
     * two data types, and they are registered on the Converters.kt file. If you wish to expand the current converters,
     * or create custom ones, you can add them on that file.
     */
    fun <T : Any> map(from: Any, toCls: KClass<T>): T {

        try{
            val constructor: KFunction<*>? = cache.get(getClaszName(from, toCls)) as KFunction<*>?
            val instance = constructor!!.call(from)
            val mapper = instance as IKMapper
            return mapper.map() as T
        } catch (e: Exception){
            println("Proceeding to class creation: $e")
        }

        val mapOfProps = mutableMapOf<String?, Any?>()
        val nameMappings = mutableMapOf<String?, String?>()

        val emptyConstructor: Boolean? = toCls.primaryConstructor?.parameters?.isEmpty()

        /** Build the base class with the constructor and the interface IKMapper **/
        val fileSpec = FileSpec.builder("org.kmapper.generated", getClaszName(from, toCls))
        val clasz = TypeSpec.classBuilder(getClaszName(from, toCls))
        clasz.addSuperinterface(IKMapper::class)

        val constructor = FunSpec.constructorBuilder()
        constructor.addParameter("from", from::class)

        from::class.declaredMemberProperties
            .forEach { m ->
                val ann: KMappedField? =
                    m.findAnnotation<KMappedField>()
                val nameToPut = ann?.destinationField ?: m.name
                mapOfProps[nameToPut] = m.getter.call(from)
                nameMappings[nameToPut] = m.name
            }


        val constructClass = FunSpec.builder("constructClass")
            .returns(toCls)

        if (emptyConstructor == true) {
            constructClass
                .addStatement("return %T()", toCls)
        } else {
            val accessors = toCls.primaryConstructor?.parameters?.map { param -> "from." + nameMappings[param.name] }
            constructClass.addStatement("return %L( %L )", toCls.simpleName!!, accessors!!.joinToString(", "))
        }

        clasz.primaryConstructor(constructor.build())
            .addProperty(PropertySpec.builder(
                "from", from::class)
                .initializer("from")
                .addModifiers(KModifier.PRIVATE)
                .build())

        clasz.addFunction(constructClass.build())

        val mappingMethod = FunSpec.builder("map")
                .returns(toCls)
            .addModifiers(KModifier.OVERRIDE)
                .addStatement("val to = constructClass()")


        toCls.declaredMemberProperties.forEach { m ->
            if (m is KMutableProperty<*>) {
                mappingMethod.addStatement("to.%L = convert(from.%L, from.%L!!::class, %L::class) as %L",  m.name, nameMappings[m.name]!!, nameMappings[m.name]!!,
                    m.returnType!!.toString().replace("?", "")
                    , m.returnType)
            }
        }

        mappingMethod.addStatement("return to")

        clasz.addFunction(mappingMethod.build())

        val convertFunction = FunSpec.builder("convert")
            .returns(Any::class)
            .addParameter("from", Any::class)
            .addParameter("typeClassifier", KClassifier::class)
            .addParameter("toClassifier", KClassifier::class)
            .addStatement("return %T().getConverter(typeClassifier, toClassifier)?.invoke(from)!!", Converters::class)

        clasz.addFunction(convertFunction.build())
        fileSpec.addType(clasz.build())

        val classLoader = Thread.currentThread().contextClassLoader

        /** Load class from KotlinScript engine manager. **/
        setIdeaIoUseFallback()
        val engineManager = ScriptEngineManager(classLoader)
        val ktsEngine: ScriptEngine = engineManager.getEngineByExtension("kts")
         val compiledClasz = ktsEngine.eval(fileSpec.build().toString() + "\n" + getClaszName(from, toCls) + "::class")


        /** Get the constructor for the compiled class, instantiate it and call the map method. **/
        val construc = (compiledClasz as KClass<*>).primaryConstructor

        cache.put(getClaszName(from, toCls), construc!!)
        val instance = construc!!.call(from)
        val mapper = instance as IKMapper
        return mapper.map() as T
    }

    fun getClaszName(to: Any, cls: KClass<*>): String {
        val toClass = to::class.simpleName
        val clsName = cls.simpleName
        return "$toClass" + "To" + "$clsName" + "KMapper"
    }
}