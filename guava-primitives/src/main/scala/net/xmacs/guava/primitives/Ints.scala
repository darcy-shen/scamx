/*
 * Copyright (C) 2008 The Guava Authors
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

package net.xmacs.guava.primitives

import net.xmacs._

object Ints {
  /**
    * Returns the {@code int} nearest in value to {@code value}.
    *
    * @param value any { @code long} value
    * @return the same value cast to { @code int} if it is in the range of the
    * { @code int} type, { @link Integer#MAX_VALUE} if it is too large, or {
    * @link Integer#MIN_VALUE} if it is too small
    */
  def saturatedCast(value: Long): Int = {
    if (value > Int.MaxValue) Int.MaxValue
    else if (value < Int.MinValue) Int.MinValue
    else value.toInt
  }
}
