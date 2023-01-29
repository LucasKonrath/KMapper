package org.kmapper.generated

import kotlin.Any
import kotlin.reflect.KClassifier
import org.kmapper.IKMapper
import org.kmapper.converters.Converters
import org.kmapper.testClasses.DestinationRecordClass
import org.kmapper.testClasses.OriginalClass

public class `OriginalClass'ToDestinationRecordClass'KMapper`(
  private val from: OriginalClass,
) : IKMapper {
  public fun constructClass(): DestinationRecordClass = DestinationRecordClass( from.testString,
      from.testInt )

  public override fun map(): DestinationRecordClass {
    val to = constructClass()
    return to
  }

  public fun convert(
    from: Any,
    typeClassifier: KClassifier,
    toClassifier: KClassifier,
  ): Any = Converters().getConverter(typeClassifier, toClassifier)?.invoke(from)!!
}
