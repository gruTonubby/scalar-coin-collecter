package game.logic

abstract sealed class Move {
  val from: Cell
  val to: Cell
}
case class RegularMove(from: Cell, to: Cell) extends Move

/*
scaladoc chess\*.scala
scalac chess\*.scala
*/

