/*
 * Copyright (C) 2010 The Guava Authors
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

import java.util.stream.IntStream

import net.xmacs._

/**
  * Static utility methods pertaining to {@code String} or {@code CharSequence} instances.
  *
  * @author Kevin Bourrillion
  * @since 3.0
  */
object Strings {
  /**
    * Returns the given string if it is non-null; the empty string otherwise.
    *
    * @param string the string to test and possibly return
    * @return {@code string} itself if it is non-null; {@code ""} if it is null
    */
  def nullToEmpty(string: String): String = {
    Platform.nullToEmpty(string)
  }

  /**
    * Returns the given string if it is nonempty; {@code null} otherwise.
    *
    * @param string the string to test and possibly return
    * @return {@code string} itself if it is nonempty; {@code null} if it is empty or null
    */
  def emptyToNull(string: String): String = {
    Platform.emptyToNull(string)
  }

  /**
    * Returns a string, of length at least {@code minLength}, consisting of {@code string} prepended
    * with as many copies of {@code padChar} as are necessary to reach that length. For example,
    *
    * <ul>
    *   <li>{@code padStart("7", 3, '0')} returns {@code "007"}
    *   <li>{@code padStart("2010", 3, '0')} returns {@code "2010"}
    * </ul>
    *
    * <p>See {@link java.util.Formatter} for a richer set of formatting capabilities.
    *
    * @param string the string which should appear at the end of the result
    * @param minLength the minimum length the resulting string must have. Can be zero or negative, in
    *     which case the input string is always returned.
    * @param padChar the character to insert at the beginning of the result until the minimum length
    *     is reached
    * @return the padded string
    */
  def padStart(string: String, minLength: Int, padChar: Char): String = {
    if (string.length >= minLength) {
      string
    } else {
      val sb = new StringBuilder(minLength)
      IntStream.range(string.length, minLength)
        .forEach(x => sb.append(padChar))
      sb.append(string)
      sb.toString()
    }
  }
}
