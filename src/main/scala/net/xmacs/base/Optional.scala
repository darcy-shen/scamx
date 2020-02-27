/*
 * Copyright (C) 2011 The Guava Authors
 *               2020 Darcy Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package net.xmacs.base

import net.xmacs._

/**
  * An immutable object that may contain a non-null reference to another object. Each instance of
  * this type either contains a non-null reference, or contains nothing (in which case we say that
  * the reference is "absent"); it is never said to "contain {@code null}".
  *
  * <p>A non-null {@code Optional<T>} reference can be used as a replacement for a nullable {@code T}
  * reference. It allows you to represent "a {@code T} that must be present" and a "a {@code T} that
  * might be absent" as two distinct types in your program, which can aid clarity.
  *
  * <p>Some uses of this class include
  *
  * <ul>
  * <li>As a method return type, as an alternative to returning {@code null} to indicate that no
  * value was available
  * <li>To distinguish between "unknown" (for example, not present in a map) and "known to have no
  * value" (present in the map, with value {@code Optional.absent()})
  * <li>To wrap nullable references for storage in a collection that does not support {@code null}
  * (though there are <a
  * href="https://github.com/google/guava/wiki/LivingWithNullHostileCollections">several other
  * approaches to this</a> that should be considered first)
  * </ul>
  *
  * <p>A common alternative to using this class is to find or create a suitable <a
  * href="http://en.wikipedia.org/wiki/Null_Object_pattern">null object</a> for the type in question.
  *
  * <p>This class is not intended as a direct analogue of any existing "option" or "maybe" construct
  * from other programming environments, though it may bear some similarities.
  *
  * <p><b>Comparison to {@code java.util.Optional} (JDK 8 and higher):</b> A new {@code Optional}
  * class was added for Java 8. The two classes are extremely similar, but incompatible (they cannot
  * share a common supertype). <i>All</i> known differences are listed either here or with the
  * relevant methods below.
  *
  * <ul>
  * <li>This class is serializable; {@code java.util.Optional} is not.
  * <li>{@code java.util.Optional} has the additional methods {@code ifPresent}, {@code filter},
  * {@code flatMap}, and {@code orElseThrow}.
  * <li>{@code java.util} offers the primitive-specialized versions {@code OptionalInt}, {@code
  * OptionalLong} and {@code OptionalDouble}, the use of which is recommended; Guava does not
  * have these.
  * </ul>
  *
  * <p><b>There are no plans to deprecate this class in the foreseeable future.</b> However, we do
  * gently recommend that you prefer the new, standard Java class whenever possible.
  *
  * <p>See the Guava User Guide article on <a
  * href="https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained#optional">using {@code
  * Optional}</a>.
  *
  * @sparam T the type of instance that can be contained. { @code Optional} is naturally covariant on
  *                                                                 this type, so it is safe to cast an { @code Optional<T>} to { @code Optional<S>} for any
  *                                                                 supertype { @code S} of { @code T}.
  * @author Kurt Alfred Kluever
  * @author Kevin Bourrillion
  * @since 10.0
  */
abstract class Optional[T] extends Serializable {
  /**
    * Returns the equivalent {@code java.util.Optional} value to this optional.
    *
    * <p>Unfortunately, the method reference {@code Optional::toJavaUtil} will not work, because it
    * could refer to either the static or instance version of this method. Write out the lambda
    * expression {@code o -> o.toJavaUtil()} instead.
    *
    * @since 21.0
    */
  def toJavaUtil: java.util.Optional[T] = {
    java.util.Optional.ofNullable(orNull)
  }

  /**
    * Returns {@code true} if this holder contains a (non-null) instance.
    *
    * <p><b>Comparison to {@code java.util.Optional}:</b> no differences.
    */
  def isPresent: Boolean

  /**
    * Returns the contained instance, which must be present. If the instance might be absent, use
    * {@link #or(Object)} or {@link #orNull} instead.
    *
    * <p><b>Comparison to {@code java.util.Optional}:</b> when the value is absent, this method
    * throws {@link IllegalStateException}, whereas the Java 8 counterpart throws {@link
    * java.util.NoSuchElementException NoSuchElementException}.
    *
    * @throws IllegalStateException if the instance is absent ({ @link #isPresent} returns { @code
    * false}); depending on this <i>specific</i> exception type (over the more general { @link
    * RuntimeException}) is discouraged
    */
  def get: T

  /**
    * Returns the contained instance if it is present; {@code null} otherwise. If the instance is
    * known to be present, use {@link #get()} instead.
    *
    * <p><b>Comparison to {@code java.util.Optional}:</b> this method is equivalent to Java 8's
    * {@code Optional.orElse(null)}.
    */
  def orNull: T

  /**
    * Returns an immutable singleton {@link Set} whose only element is the contained instance if it
    * is present; an empty immutable {@link Set} otherwise.
    *
    * <p><b>Comparison to {@code java.util.Optional}:</b> this method has no equivalent in Java 8's
    * {@code Optional} class. However, this common usage:
    *
    * <pre>{@code
    * for (Foo foo : possibleFoo.asSet()) {
   *   doSomethingWith(foo);
   * }
    * }</pre>
    *
    * ... can be replaced with:
    *
    * <pre>{@code
    * possibleFoo.ifPresent(foo -> doSomethingWith(foo));
   * }</pre>
    *
    * <p><b>Java 9 users:</b> some use cases can be written with calls to {@code optional.stream()}.
    *
    * @since 11.0
    */
  def asSet: Set[T]
}

object Optional {
  /**
    * Returns an {@code Optional} instance with no contained reference.
    *
    * <p><b>Comparison to {@code java.util.Optional}:</b> this method is equivalent to Java 8's
    * {@code Optional.empty}.
    */
  def absent[T]: Optional[T] = {
    Absent.withType
  }

  /**
    * Returns the equivalent {@code java.util.Optional} value to the given {@code
    * com.google.common.base.Optional}, or {@code null} if the argument is null.
    *
    * <p>If {@code googleOptional} is known to be non-null, use {@code googleOptional.toJavaUtil()}
    * instead.
    *
    * <p>Unfortunately, the method reference {@code Optional::toJavaUtil} will not work, because it
    * could refer to either the static or instance version of this method. Write out the lambda
    * expression {@code o -> Optional.toJavaUtil(o)} instead.
    *
    * @since 21.0
    */
  def toJavaUtil[T](googleOptional: Optional[T]): java.util.Optional[T] = {
    googleOptional.toJavaUtil
  }
}
