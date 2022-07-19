package game.ui

import game.logic.Life
import game.logic.Cell
import scalafx.Includes._
import scalafx.animation.KeyFrame
import scalafx.animation.Timeline
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event.ActionEvent
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.scene.text.Text
import scalafx.scene.{Group, Scene}
import scalafx.util.Duration

import scalafx.event.Event
import scalafx.scene.input.{KeyCode, KeyCombination, KeyCodeCombination, KeyEvent}


object Main extends JFXApp {
// val ctlA = new KeyCodeCombination(KeyCode.A, KeyCombination.ControlDown)

  val cellCanvas = new CellCanvas
  var score = 0


  stage = new PrimaryStage {

    title = "Faithful Coin Collector"
    width = 813
    height = 870
    resizable = false
          val Score = new Text("10")

val KEY_A = new KeyCodeCombination(KeyCode.A)
val KEY_W = new KeyCodeCombination(KeyCode.W)
val KEY_S = new KeyCodeCombination(KeyCode.S)
val KEY_D = new KeyCodeCombination(KeyCode.D)
    scene = new Scene {
      onKeyPressed = { key =>
        if (KEY_A.`match`(key)){
          score += cellCanvas.movePlayer(1)
          
        }
        else if(KEY_D.`match`(key)){
          score += cellCanvas.movePlayer(2)
                    // println(score.toString)

        }
          else if(KEY_W.`match`(key)){
          

          score += cellCanvas.movePlayer(3)
          // println(score.toString)
          
          }
          else if(KEY_S.`match`(key)){
          score += cellCanvas.movePlayer(4)
                    // println(score.toString)
          }
          else
          println("No match")
                Score.text = score.toString

      }



      root = new BorderPane {


        top = new ToolBar {
          
          private val resetButton = new Button("click here!") {
            handleEvent(ActionEvent.Any) {
              _: ActionEvent =>
                cellCanvas.initField()
                Score.text = "0"
            }
          }

          content = List(
            resetButton, 
            new Separator, 
            new Label("Score :"), 
            Score,
            )
        }

        center = new Group {
          cellCanvas.width <== width
          cellCanvas.height <== height
          children = cellCanvas
        }
      }
    }


    
  }
}
