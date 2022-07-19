package game.logic


import compat.Platform.EOL

object Field {

  //value of wall is 0
  //value of empty cell is -1
  //value of empty cell is positive number

  type Field = Map[Cell,Int]

  /**
    * The cell state when the game starts.
    */
  private var positionX = 1
  private var positionY = 1
  private var xLimit = 10
  private var yLimit = 10
  private var toX = 10
  private var toY = 10
  
  def startingField: Field = Map(
    Cell(1,1) -> -999,  //number indicating player
    Cell(2,1) -> -1,    //number indicating empty cell
    Cell(3,1) -> -1,
    Cell(4,1) -> 3,     //number indicating coin
    Cell(5,1) -> -1,
    Cell(6,1) -> -1,
    Cell(7,1) -> 0,     //wall
    Cell(8,1) -> -1,
    Cell(9,1) -> -1,
    Cell(10,1) -> -1,

    Cell(1,2) -> -1,
    Cell(2,2) -> -1,
    Cell(3,2) -> -1,
    Cell(4,2) -> -1,
    Cell(5,2) -> -1,
    Cell(6,2) -> -1,
    Cell(7,2) -> -1,
    Cell(8,2) -> -1,
    Cell(9,2) -> -1,
    Cell(10,2) -> -1,

    Cell(1,3) -> -1,
    Cell(2,3) -> -1,
    Cell(3,3) -> -1,
    Cell(4,3) -> 4,
    Cell(5,3) -> 0,
    Cell(6,3) -> -1,
    Cell(7,3) -> -1,
    Cell(8,3) -> -1,
    Cell(9,3) -> -1,
    Cell(10,3) -> -1,

    Cell(1,4) -> -1,
    Cell(2,4) -> -1,
    Cell(3,4) -> -1,
    Cell(4,4) -> -1,
    Cell(5,4) -> -1,
    Cell(6,4) -> -1,
    Cell(7,4) -> -1,
    Cell(8,4) -> -1,
    Cell(9,4) -> -1,
    Cell(10,4) -> -1,

    Cell(1,5) -> -1,
    Cell(2,5) -> -1,
    Cell(3,5) -> -1,
    Cell(4,5) -> -1,
    Cell(5,5) -> -1,
    Cell(6,5) -> -1,
    Cell(7,5) -> -1,
    Cell(8,5) -> -1,
    Cell(9,5) -> -1,
    Cell(10,5) -> -1,

    Cell(1,6) -> -1,
    Cell(2,6) -> -1,
    Cell(3,6) -> -1,
    Cell(4,6) -> -1,
    Cell(5,6) -> -1,
    Cell(6,6) -> -1,
    Cell(7,6) -> -1,
    Cell(8,6) -> -1,
    Cell(9,6) -> -1,
    Cell(10,6) -> -1,

    Cell(1,7) -> -1,
    Cell(2,7) -> -1,
    Cell(3,7) -> -1,
    Cell(4,7) -> -1,
    Cell(5,7) -> -1,
    Cell(6,7) -> -1,
    Cell(7,7) -> -1,
    Cell(8,7) -> -1,
    Cell(9,7) -> -1,
    Cell(10,7) -> -1,

    Cell(1,8) -> -1,
    Cell(2,8) -> -1,
    Cell(3,8) -> -1,
    Cell(4,8) -> -1,
    Cell(5,8) -> -1,
    Cell(6,8) -> -1,
    Cell(7,8) -> -1,
    Cell(8,8) -> -1,
    Cell(9,8) -> -1,
    Cell(10,8) -> -1,

    Cell(1,9) -> -1,
    Cell(2,9) -> -1,
    Cell(3,9) -> -1,
    Cell(4,9) -> -1,
    Cell(5,9) -> -1,
    Cell(6,9) -> -1,
    Cell(7,9) -> -1,
    Cell(8,9) -> -1,
    Cell(9,9) -> -1,
    Cell(10,9) -> -1,

    Cell(1,10) -> -1,
    Cell(2,10) -> -1,
    Cell(3,10) -> -1,
    Cell(4,10) -> -1,
    Cell(5,10) -> -1,
    Cell(6,10) -> -1,
    Cell(7,10) -> -1,
    Cell(8,10) -> -1,
    Cell(9,10) -> -1,
    Cell(10,10) -> -1
    )
  
  /**
  
    * Shows the cell.
    */
  // def showField(field: Field): String = {
  //   def rowToString(row: Int) = 1.to(10).map(col=>
  //     field.get(Cell(col,row)).map(_.toString).getOrElse(".")).mkString
  //   " abcdefghij" + EOL + 10.to(1,-1).map(row =>
  //   row.toString + rowToString(row) + row.toString + EOL).mkString + " abcdefghij"
  // }

  def attemptToMovePlayer(field: Field, direction: Int) = {
    direction match {
      case 1 =>
        toX = positionX - 1
        toY = positionY
      case 2 => 
        toX = positionX + 1
        toY = positionY
      case 3 => 
        toY = positionY - 1 
        toX = positionX
      case 4 => 
        toY = positionY + 1 
        toX = positionX
    }
    var cur_cell = Cell(positionX, positionY)
    var to_cell = Cell(toX, toY)
    var coin = 0
    if(!to_cell.isValid || field(to_cell) == 0)
    {
      (cur_cell, cur_cell, field, coin)
    }
    else{
      coin = field(to_cell)
      positionX = toX
      positionY = toY
      var tempField = field
        .get(cur_cell).fold(field)(value =>
        field - cur_cell + (cur_cell -> -1)
        )
      (cur_cell, to_cell,   
        tempField
        .get(to_cell).fold(tempField)(value =>
        tempField - to_cell + (to_cell -> -999)
        )
        , coin)
  }
  }
  /**
    * Returns a new cell, updated with a move.
    */
  def updateField(field: Field, move: Move): Field = {
    
    move match {
    case RegularMove(from,to) =>{
      var temp = Cell(999,999)
      field.get(from).fold(field)(value =>
        field + (temp->value)
        )
        field.get(to).fold(field)(value =>
        field - from + (from->value)
        )
        field.get(temp).fold(field)(value =>
        field - temp + (to->value)
        )
      // field.get(to) = -999
      // field.get(from) = -1
    }
  }
  }
}
