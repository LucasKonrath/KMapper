package mock.data

import mapper.MapperModelField

data class Car(
    @property:MapperModelField(destinationField = "nome", destinationClass = "Carro")
    @property:MapperModelField(destinationField = "id", destinationClass = "EmptyCar")
    var name: String? = null,
    @property:MapperModelField(destinationField = "cavalos", destinationClass = "Carro")
    @property:MapperModelField(destinationField = "cavalin", destinationClass = "EmptyCar")
    val hp: Int)
