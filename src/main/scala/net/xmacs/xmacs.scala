package net

package object xmacs {
  type Array[T]                = scala.Array[T]
  val  Array: scala.Array.type = scala.Array

  type Unit = scala.Unit

  type Boolean = scala.Boolean
  type Byte = scala.Byte
  type Char = scala.Char
  type Double = scala.Double

  type Short                   = scala.Short
  val  Short: scala.Short.type = scala.Short

  type Int                 = scala.Int
  val  Int: scala.Int.type = scala.Int

  type Long                  = scala.Long
  val  Long: scala.Long.type = scala.Long
}
