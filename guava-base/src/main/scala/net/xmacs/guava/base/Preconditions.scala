/*
 * Copyright (C) 2007 The Guava Authors
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

import net.xmacs._

object Preconditions {
  /**
    * Ensures that an object reference passed as a parameter to the calling method is not null.
    *
    * @param reference an object reference
    * @return the non-null reference that was validated
    * @throws NullPointerException if {@code reference} is null
    * @see Verify#verifyNotNull Verify.verifyNotNull()
    */
  def checkNotNull[T](reference: T): T = {
    if (reference == null) {
      throw new NullPointerException
    }
    reference
  }

  /**
    * Ensures that an object reference passed as a parameter to the calling method is not null.
    *
    * @param reference    an object reference
    * @param errorMessage the exception message to use if the check fails; will be converted to a
    *                     string using { @link String#valueOf(Object)}
    * @return the non-null reference that was validated
    * @throws NullPointerException if { @code reference} is null
    * @see Verify#verifyNotNull Verify.verifyNotNull()
    */
  def checkNotNull[T](reference: T, errorMessage: String): T = {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage))
    }
    reference
  }

  def checkElementIndex(index: Int, size: Int): Int =
    checkElementIndex(index, size, "index")

  def checkElementIndex(index: Int, size: Int, desc: String): Int = { // Carefully optimized for execution by hotspot (explanatory comment above)
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(badElementIndex(index, size, desc))
    index
  }

  private def badElementIndex(index: Int, size: Int, desc: String): String = {
    if (index < 0) String.format("%s (%s) must not be negative", desc, index)
    else if (size < 0) throw new IllegalArgumentException("negative size: " + size)
    else { // index >= size
      String.format("%s (%s) must be less than size (%s)", desc, index, size)
    }
  }

  // def lenientFormat(template: String, pArgs: Any*): String = {
  //   val template = String.valueOf(template) // null -> "null"

  //   if (pArgs == null) {
  //     args = Array[AnyRef]("(Object[])null")
  //   }
  //   else {
  //     for (i <- 0 until args.length) {
  //       args(i) = lenientToString(args(i))
  //     }
  //   }
  //   // start substituting the arguments into the '%s' placeholders
  //   val builder: StringBuilder = new StringBuilder(template.length + 16 * args.length)
  //   var templateStart: Int = 0
  //   var i: Int = 0
  //   while ( {
  //     i < args.length
  //   }) {
  //     val placeholderStart: Int = template.indexOf("%s", templateStart)
  //     if (placeholderStart == -(1)) {
  //       break //todo: break is not supported

  //     }
  //     builder.append(template, templateStart, placeholderStart)
  //     builder.append(args({
  //       i += 1; i - 1
  //     }))
  //     templateStart = placeholderStart + 2
  //   }
  //   builder.append(template, templateStart, template.length)
  //   // if we run out of placeholders, append the extra args in square braces
  //   if (i < args.length) {
  //     builder.append(" [")
  //     builder.append(args({
  //       i += 1; i - 1
  //     }))
  //     while ( {
  //       i < args.length
  //     }) {
  //       builder.append(", ")
  //       builder.append(args({
  //         i += 1; i - 1
  //       }))
  //     }
  //     builder.append(']')
  //   }
  //   return builder.toString
  // }

  private def lenientToString(o: Any): String = {
    try {
      String.valueOf(o)
    } catch {
      case e: Exception =>
        // Default toString() behavior - see Object.toString()
        val objectToString: String = o.getClass.getName + '@' + Integer.toHexString(System.identityHashCode(o))
        "<" + objectToString + " threw " + e.getClass.getName + ">"
    }
  }
}
