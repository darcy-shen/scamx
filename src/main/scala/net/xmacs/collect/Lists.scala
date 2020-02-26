/*
 * Copyright (C) 2007 The Guava Authors
 *               2020 Darcy Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.xmacs.collect

import net.xmacs._

import java.util.{ArrayList, Collections}
import scala.Predef.require

import net.xmacs.base.Preconditions._
import net.xmacs.primitives.Ints

/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Lists {
  // ArrayList

  /**
    * Creates a <i>mutable</i>, empty {@code ArrayList} instance (for Java 6 and earlier).
    *
    * <p><b>Note:</b> if mutability is not required, use {@link ImmutableList#of()} instead.
    *
    * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and should be treated as
    * deprecated. Instead, use the {@code ArrayList} {@linkplain ArrayList#ArrayList() constructor}
    * directly, taking advantage of the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
    */
  def newArrayList[T]: ArrayList[T] = new ArrayList[T]()

  def computeArrayListCapacity(arraySize: Int): Int = {
    require(arraySize>=0, "arraySize must be non negative")

    // TODO(kevinb): Figure out the right behavior, and document it
    Ints.saturatedCast(5L + arraySize + (arraySize / 10))
  }

  /**
    * Creates a <i>mutable</i> {@code ArrayList} instance containing the given elements.
    *
    * <p><b>Note:</b> essentially the only reason to use this method is when you will need to add or
    * remove elements later. Otherwise, for non-null elements use {@link ImmutableList#of()} (for
    * varargs) or {@link ImmutableList#copyOf(Object[])} (for an array) instead. If any elements
    * might be null, or you need support for {@link List#set(int, Object)}, use {@link
    * Arrays#asList}.
    *
    * <p>Note that even when you do need the ability to add or remove, this method provides only a
    * tiny bit of syntactic sugar for {@code newArrayList(}{@link Arrays#asList asList}{@code
    * (...))}, or for creating an empty list then calling {@link Collections#addAll}. This method is
    * not actually very useful and will likely be deprecated in the future.
    */
  def newArrayList[T](elements: T*): ArrayList[T] = {
    checkNotNull(elements); // for GWT
    // Avoid integer overflow when a large array is passed in
    val capacity = computeArrayListCapacity(elements.length)
    val list = new ArrayList[T](capacity)

    // TODO: should not depend on scala.collection
    elements.foreach(list.add)
    list
  }
}
