package net.xmacs.base

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

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
