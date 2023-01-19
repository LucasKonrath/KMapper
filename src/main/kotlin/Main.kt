import java.util.Objects
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val car = Car(name = "Jetta", hp = 216)

    car.hp = 10

    var model: Any = MapperModel().map(car, Carro::class)

    println(model)

}