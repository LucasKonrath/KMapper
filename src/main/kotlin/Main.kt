import mapper.MapperModel
import mock.data.Car
import mock.data.Carro
import mock.data.EmptyCar

fun main() {

    val car = Car(name = "10", hp = 216)

    var model: Any = MapperModel().map(car, Carro::class)

    var model2: EmptyCar = MapperModel().map(car, EmptyCar::class)

    println(model)

    println("${model2.cavalin} ${model2.id}")

}