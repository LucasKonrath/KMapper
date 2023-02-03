package org.kmapper.generated

import kotlin.Any
import kotlin.reflect.KClassifier
import org.kmapper.IKMapper
import org.kmapper.converters.Converters
import org.kmapper.testClasses.EmptyConstructorClass
import org.kmapper.testClasses.OriginalAnnotatedClass

public class OriginalAnnotatedClassToEmptyConstructorClassKMapper(
  private val from: OriginalAnnotatedClass,
) : IKMapper {
  public fun constructClass(): EmptyConstructorClass = EmptyConstructorClass()

  public override fun map(): EmptyConstructorClass {
    val to = constructClass()
    to.testInt = convert(from.int, from.int!!::class, kotlin.Int::class) as kotlin.Int?
    to.testString = convert(from.string, from.string!!::class, kotlin.String::class) as
        kotlin.String?
    return to
  }

  public fun convert(
    from: Any,
    typeClassifier: KClassifier,
    toClassifier: KClassifier,
  ): Any = Converters().getConverter(typeClassifier, toClassifier)?.invoke(from)!!
}
