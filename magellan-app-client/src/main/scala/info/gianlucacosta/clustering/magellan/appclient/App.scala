/*§
  ===========================================================================
  Magellan
  ===========================================================================
  Copyright (C) 2016 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.clustering.magellan.appclient

import javafx.application.Application
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.stage.Stage

import info.gianlucacosta.clustering.magellan.appclient.ExceptionUtils._

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{HBox, VBox}


object App {
  def main(args: Array[String]) {
    /*
    This overloaded version of Application.launch() is especially suitable for ScalaFX
     */
    Application.launch(classOf[App], args: _*)
  }
}

class App extends Application {
  override def start(primaryStage: Stage): Unit = {
    val counterLabel = new Label {
      text = "Click a button to connect to the EJB"
    }

    val counterValue = new SimpleIntegerProperty(0)

    /*
    Whenever the model changes, the label is updated
     */
    counterValue.addListener(new ChangeListener[Number] {
      override def changed(observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number): Unit = {
        counterLabel.text = s"Counter value: ${counterValue.get()}"
      }
    })


    /*
      This is an example of ScalaFX's simple and elegant declarative style
      for the creation og UIs
     */
    val scene = new Scene {
      root = new VBox {
        padding = Insets(30)
        spacing = 20

        children = Seq(
          counterLabel,


          new HBox {
            spacing = 20
            alignment = Pos.Center

            children = Seq(
              new Button("Get value") {
                onAction = (eventHandler: ActionEvent) => {
                  handleExceptions {
                    CounterBeanService.increase()
                    counterValue.set(CounterBeanService.getValue)
                  }
                }
              },

              new Button("Reset") {
                onAction = (eventHandler: ActionEvent) => {
                  handleExceptions {
                    CounterBeanService.reset()
                    counterValue.set(CounterBeanService.getValue)
                  }
                }
              }
            )
          }
        )
      }
    }

    primaryStage.resizable = false
    primaryStage.title = "Magellan - EJB Client"
    primaryStage.setScene(scene)
    primaryStage.centerOnScreen()
    primaryStage.show()
  }
}
