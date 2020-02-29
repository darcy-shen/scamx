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

import java.util.function.Function

import net.xmacs._

/** Implementation of an {@link Optional} not containing a reference. */
final class Absent[T] extends Optional[T] {
  override def isPresent: Boolean = false

  override def get: T = {
    throw new IllegalStateException("Optional.get() cannot be called on an absent value")
  }

  override def orNull: T = null.asInstanceOf[T]

  override def asSet: Set[T] = ???

  override def transform[V](function: Function[T, V]): Optional[V] = ???

  override def equals(obj: Any): Boolean = ???

  override def hashCode(): Int = ???

  override def toString: String = ???
}

object Absent {
  val INSTANCE = new Absent[AnyRef]

  @SuppressWarnings(Array("unchecked")) // implementation is "fully variant"
  def withType[T]: Optional[T] =  INSTANCE.asInstanceOf[Optional[T]]
}
