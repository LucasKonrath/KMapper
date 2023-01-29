package org.kmapper.generated

import kotlin.Any
import kotlin.reflect.KClassifier
import org.kmapper.converters.Converters
import org.kmapper.testClasses.EmptyConstructorClass
import org.kmapper.testClasses.OriginalClass
import kotlin.reflect.full.createType

public class `OriginalClass'ToEmptyConstructorClass'KMapper`(
  private val from: OriginalClass,
) {
  public fun constructClass(): EmptyConstructorClass = EmptyConstructorClass()

  public fun map(): EmptyConstructorClass {
    val to = constructClass()
    to.testInt = convert(from.testInt, kotlin.String, kotlin.Int)
    to.testString = convert(from.testString, class kotlin.String, class kotlin.String)
    return to
  }

  public fun convert(
    from: Any,
    typeClassifier: KClassifier,
    toClassifier: KClassifier,
  ): Any {
    return Converters().getConverter(typeClassifier, toClassifier)?.invoke(from)!!
  }
}
