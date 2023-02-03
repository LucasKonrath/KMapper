package org.kmapper.generated

import kotlin.Any
import kotlin.reflect.KClassifier
import org.kmapper.IKMapper
import org.kmapper.converters.Converters
import org.kmapper.testClasses.EmptyConstructorClass
import org.kmapper.testClasses.OriginalClass

public class OriginalClassToEmptyConstructorClassKMapper(
  private val from: OriginalClass,
) : IKMapper {
  public fun constructClass(): EmptyConstructorClass = EmptyConstructorClass()

  public override fun map(): EmptyConstructorClass {
    val to = constructClass()
    to.testInt = convert(from.testInt, from.testInt!!::class, kotlin.Int::class) as kotlin.Int?
    to.testString = convert(from.testString, from.testString!!::class, kotlin.String::class) as
        kotlin.String?
    return to
  }

  public fun convert(
    from: Any,
    typeClassifier: KClassifier,
    toClassifier: KClassifier,
  ): Any = Converters().getConverter(typeClassifier, toClassifier)?.invoke(from)!!
}
