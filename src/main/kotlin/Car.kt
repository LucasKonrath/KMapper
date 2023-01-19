data class Car(
    @property:MapperModelField(destinationField = "nome")
    var name: String? = null,
    @property:MapperModelField(destinationField = "cavalos")
    var hp: Int? = null)
