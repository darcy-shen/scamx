/*
 * Copyright (C) 2010 The Guava Authors
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

package net.xmacs.base

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import scala.language.implicitConversions

class StringsTest extends AnyFunSuite with Matchers {
  test("padStart: noPadding") {
    Strings.padStart("", 0, '-') should be ("")
    Strings.padStart("x", 0, '-') should be ("x")
    Strings.padStart("x", 1, '-') should be ("x")
    Strings.padStart("xx", 0, '-') should be ("xx")
    Strings.padStart("xx", 2, '-') should be ("xx")
  }

  test("padStart: somePadding") {
    Strings.padStart("", 1, '-') should be ("-")
    Strings.padStart("", 2, '-') should be ("--")
    Strings.padStart("x", 2, '-') should be ("-x")
    Strings.padStart("x", 3, '-') should be ("--x")
    Strings.padStart("xx", 3, '-') should be ("-xx")
  }
}
