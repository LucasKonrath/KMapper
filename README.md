# This is a framework for converting Kotlin objects seamslessly.

### It functions just like the ModelMapper framework for Java, if the fields have the same name, they'll be automatically converted.

### If the fields have different names, you must specify their destiny class (simple name) and their name on that class, using the MapperModelField annotation.

# Sample Usage

# Origin class with the mappings declared. If they aren't declared in the annotation, mapping will be done by name.

```kotlin
data class Car(
    @property:MapperModelField(destinationField = "id", destinationClass = "TargetCar")
    var name: String? = null,
    @property:MapperModelField(destinationField = "horsePower", destinationClass = "TargetCar")
    val hp: Int
)
```

# Destination class

```kotlin
class TargetCar() {
    var id: Int? = null
    var horsePower: Double? = null
}
```

# Usage

```kotlin
val from = Car("Jetta", 216)
val mapped = ModelMapper().map(from, TargetCar::class)
```

## Field Conversion Strategies

### If you want to add a custom field conversion strategy, in addition to the default ones provided, you can do so in the following Manner:

```kotlin

var model: MapperModel()

model.converters.addConverter(
    OriginClass::class, DestinyClass::class
) { from ->
    from as OriginClass
    DestinyClass.valueOf(from)
    //using DestinyClass as an Enum for this example
}
```

### Default Conversions Mapped:

- String -> Int, Double, Byte, Short
- Int -> String, Double, Byte, Short
- Double -> String, Int, Byte, Short
- Byte -> String, Int, Double, Short
- Short -> String, Int, Double, Byte

# How it works

### Both the target and the origin classes have their fields introspected via reflection, then the fields are converted using the registered converters (which are saved in the mapper.converters.Converters class).

### The primary constructor of the destiny class is then called, and after that, all of its mutable fields are injected with the values of the original object via reflection.
