package game.ui

import scalafx.Includes._
import scalafx.event.Event
import scalafx.event.EventIncludes._
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.MouseEvent._
import scalafx.scene.paint.Color
import game.logic.Field._
import game.logic.Cell
import game.logic.RegularMove
import scalafx.scene.input.{KeyCode, KeyCombination, KeyCodeCombination, KeyEvent}
import scalafx.scene.text.Font
class CellCanvas extends Canvas {

  private val CellSize = 80
  private val gc = graphicsContext2D
  private var cells: Set[(Int, Int)] = Set.empty

  private val wallColor = Color.DarkGray
  private val playerColor = Color.Red
  private val coinColor = Color.Yellow
  private val textColor = Color.Green

  private var field = startingField

  def cellCoords: Set[(Int, Int)] = cells

  def clear(): Unit = {
    gc.clearRect(0, 0, width.value, height.value)
    cells = Set.empty
  }

  def plotCell(cell: Cell, value: Int) = {
    var x = cell.col - 1
    var y = cell.row - 1
    value match {
      case -1 => 
      gc.clearRect(x * CellSize, y * CellSize, CellSize, CellSize)
      case 0 => 
      gc.fill = wallColor
      gc.fillRect(x * CellSize, y * CellSize, CellSize, CellSize)
      case -999 => 
      gc.fill = playerColor
      gc.fillRect(x * CellSize, y * CellSize, CellSize, CellSize)
      case everythingElse => 
      gc.fill = coinColor
      gc.fillRect(x * CellSize, y * CellSize, CellSize, CellSize)
      gc.fill = textColor
      gc.setFont(new Font(40));
      gc.fillText(value.toString, (x) * CellSize + CellSize/3, (y+1) * CellSize - CellSize/3);
    }
  }

  def initField(): Unit = {
    for((cell,value) <- field) plotCell(cell, value)
  }

  def movePlayer(direction: Int): Int = {
    val curToCell = attemptToMovePlayer(field, direction)
    plotCell(curToCell._1, -1)
    plotCell(curToCell._2, -999)
    field = curToCell._3
    if(curToCell._4>0)
    curToCell._4
    else
    0
    // field(curToCell._1) = -1
    // field(curToCell._2) = -999
    // updateField(field, RegularMove(curToCell._1, curToCell._2))
  }

  handleEvent(MouseEvent.Any) {
    e: MouseEvent => e.eventType match {
      case MouseClicked | MouseDragged =>
        val x = ((e.x - (e.x % CellSize)) / CellSize).toInt
        val y = ((e.y - (e.y % CellSize)) / CellSize).toInt
        // plotCell(Cell(x, y), 5)
      case _ => ()
    }
  }

}
