import java.util.Objects
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val car = Car(name = "10", hp = 216)

    var model: Any = MapperModel().map(car, Carro::class)

    var model2: EmptyCar = MapperModel().map(car, EmptyCar::class)

    println(model)

    println("${model2.cavalin} ${model2.id}")

}