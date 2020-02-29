package net

package object xmacs {
  type Array[T]                = scala.Array[T]
  val  Array: scala.Array.type = scala.Array

  type Set[T] = java.util.Set[T]
  type List[T] = java.util.List[T]

  type Unit = scala.Unit

  type Boolean = scala.Boolean
  type Byte = scala.Byte
  type Char = scala.Char
  type Double = scala.Double
  type Any = scala.Any

  type Short                   = scala.Short
  val  Short: scala.Short.type = scala.Short

  type Int                 = scala.Int
  val  Int: scala.Int.type = scala.Int

  type Long                  = scala.Long
  val  Long: scala.Long.type = scala.Long

  type String = java.lang.String
  type NullPointerException = java.lang.NullPointerException
  type IllegalArgumentException = java.lang.IllegalArgumentException
  type RuntimeException = java.lang.RuntimeException
  type Error = java.lang.Error

  def ??? : Nothing = throw new NotImplementedError
}
