package org.kmapper.ksp
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import org.kmapper.annotations.KMapperDefinitions
import kotlin.reflect.KClass

class ListedProcessor(private val environment: SymbolProcessorEnvironment): SymbolProcessor {
    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString())
        .filterIsInstance<KSFunctionDeclaration>().filter {
            it.parameters.isEmpty()
        }
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val listedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(KMapperDefinitions::class)
        if(!listedFunctions.iterator().hasNext()) return emptyList()
        val functionNames = listedFunctions.map{ it.simpleName.asString() }

        val sourceFiles = listedFunctions.mapNotNull { it.containingFile }
        val fileText = buildString {
            append("// ")
            append(functionNames.joinToString(", "))
        }
        val file = environment.codeGenerator.createNewFile(
            Dependencies(
                false,
                *sourceFiles.toList().toTypedArray(),
            ),
            "your.generated.file.package",
            "GeneratedLists"
        )

        file.write(fileText.toByteArray())
        return (listedFunctions).filterNot { it.validate() }.toList()
    }
}