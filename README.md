# This is a framework for converting Kotlin objects seamslessly. 
### It functions just like the ModelMapper framework for Java, if the fields have the same name, they'll be automatically converted.

### If the fields have different names, you must specify their destiny class (simple name) and their name on that class, using the MapperModelField annotation.

# How it works

### Both the target and the origin classes have their fields introspected via reflection, then the fields are converted using the registered converters (which are saved in the mapper.Converters class).
### The primary constructor of the destiny class is then called, and after that, all of it's mutable fields are injected with the values of the original object via reflection.