# This is a framework for converting Kotlin objects seamslessly.

### It functions just like the ModelMapper framework for Java, if the fields have the same name, they'll be automatically converted.

### If the fields have different names, you must specify their destiny class (simple name) and their name on that class, using the KMappedField annotation.

# Sample Usage

# Origin class with the mappings declared. If they aren't declared in the annotation, mapping will be done by name (it will map to the same fieldName on the destination class). Else, field will be mapped to the value present on the destinationField property of the annotation.

```kotlin
data class Car(
    var name: String? = null,
    @property:KMappedField(destinationField = "horsePower")
    val hp: Int
)
```

# Destination class

```kotlin
class TargetCar() {
    var name: String? = null
    var horsePower: Double? = null
}
```

# Usage

```kotlin
val from = Car("Jetta", 216)
val mapped = KMapper().map(from, TargetCar::class)
```

## Field Conversion Strategies

### If you want to add a custom field conversion strategy, in addition to the default ones provided, you can do so in the following Manner:

```kotlin

var mapper: org.kmapper.KMapper()

mapper.converters.addConverter(
    OriginClass::class, DestinyClass::class
) { from ->
    from as OriginClass
    DestinyClass.valueOf(from)
    //using DestinyClass as an Enum for this example
}

// then convert your objects
mapper.map(...
```

### Default Conversions Mapped:

- String -> Int, Double, Byte, Short
- Int -> String, Double, Byte, Short
- Double -> String, Int, Byte, Short
- Byte -> String, Int, Double, Short
- Short -> String, Int, Double, Byte

# How it works

### Both the target and the origin classes have their fields introspected via reflection, then the fields are converted using the registered converters (which are saved in the org.kmapper.converters.Converters class).

### The primary constructor of the destiny class is then called, and after that, all of its mutable fields are injected with the values of the original object via reflection.

### When an KClass has its declaredMemberProperties loaded via reflection, an in-memory cache is created to speed up future invocations, by using a HashMap implementation. By default, this cache has 15 minutes of TTL. At this current release, you can't customize this TTL, but it will be possible in the future. This cache is unique per instance of KMapper().

# Performance

## You can see the performance Tests and Results on this repo: https://github.com/LucasKonrath/KMapper-Perfomance-Tests, which uses JMH for benchmarks.
## It will always be kept up to date with the latest KMapper version released.
