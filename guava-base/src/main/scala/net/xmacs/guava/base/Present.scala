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

package net.xmacs.guava.base

import java.util.Collections
import java.util.function.Function

import net.xmacs._
import net.xmacs.guava.base.Preconditions.checkNotNull

/**
  * Implementation of an {@link Optional} containing a reference.
  *
  * NOTE: to avoid Present(null), the constructor is marked package private
  */
final class Present[T] private[base] (val reference: T) extends Optional[T] {
  override def isPresent = true

  override def orNull: T = reference

  override def get: T = reference

  override def asSet: Set[T] = Collections.singleton(reference)

  override def transform[V](function: Function[T, V]): Optional[V] = {
    Optional.fromNullable[V](function.apply(reference))
  }

  override def or(defaultValue: T): T = {
    checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)")
    reference
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case other: Present[_] =>
        other.reference.equals(reference)
      case _ =>
        false
    }
  }

  override def hashCode: Int = 0x598df91c + reference.hashCode

  override def toString: String = "Optional.of(" + reference + ")"
}
