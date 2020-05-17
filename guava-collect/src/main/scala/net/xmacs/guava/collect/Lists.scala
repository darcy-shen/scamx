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
package net.xmacs.guava.collect

import java.util
import java.util.RandomAccess

import net.xmacs._

import scala.Predef.require
import net.xmacs.guava.base.Preconditions._
import net.xmacs.guava.primitives.Ints
import net.xmacs.guava.math.IntMath

object Lists {
  // ArrayList

  /**
    * Creates a <i>mutable</i>, empty {@code ArrayList} instance.
    */
  def newArrayList[T](): util.ArrayList[T] = new util.ArrayList[T]()

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
  def newArrayList[T](elements: T*): util.ArrayList[T] = {
    checkNotNull(elements); // for GWT
    // Avoid integer overflow when a large array is passed in
    val capacity = computeArrayListCapacity(elements.length)
    val list = new util.ArrayList[T](capacity)

    // TODO: should not depend on scala.collection
    elements.foreach(list.add)
    list
  }

  def newArrayList[T](elements: Iterable[_ <: T]): util.ArrayList[T] = {
    checkNotNull(elements)

    if (elements.isInstanceOf[util.Collection[_ <: T]]) {
      new util.ArrayList[T](Collections2.cast(elements))
    } else {
      newArrayList(elements.iterator)
    }
  }

  def newArrayList[T](elements: Iterator[_ <: T]): util.ArrayList[T] = {
    val list = newArrayList[T]()
    Iterators.addAll(list, elements)
    list
  }

  def newLinkedList[T](): util.LinkedList[T] = {
    new util.LinkedList[T]
  }

  def newLinkedList[T](elements: Iterable[_ <: T]): util.LinkedList[T] = {
    val list = newLinkedList[T]()
    Iterables.addAll(list, elements)
    new util.LinkedList
  }

  def asList[T](first: T, rest: Array[T]): List[T] =
    new OnePlusArrayList(first, rest)

  def asList[T](first: T, second: T, rest: Array[T]): List[T] =
    new TwoPlusArrayList(first, second, rest)

  class OnePlusArrayList[T](first: T, rest: Array[T]) extends util.AbstractList[T]
    with Serializable with RandomAccess {

    override def get(index: Int): T = {
      if (index == 0) first
      else rest(index - 1)
    }

    override def size(): Int = {
      IntMath.saturatedAdd(rest.length, 1)
    }
  }

  class TwoPlusArrayList[T](first: T, second: T, rest: Array[T]) extends util.AbstractList[T]
    with Serializable with RandomAccess {

    override def size(): Int = {
      IntMath.saturatedAdd(rest.length, 1)
    }

    override def get(index: Int): T = {
      index match {
        case 0 =>
          first
        case 1 =>
          second
        case _ =>
          // check explicitly so the IOOBE will have the right message
          checkElementIndex(index, size())
          rest(index-2)
      }
    }

    private val serialVersionUID = 0
  }
}

