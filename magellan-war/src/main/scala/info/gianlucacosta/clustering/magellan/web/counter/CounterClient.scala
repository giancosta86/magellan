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

package info.gianlucacosta.clustering.magellan.web.counter

import javax.ejb.EJB
import javax.enterprise.context.SessionScoped

import info.gianlucacosta.clustering.magellan.ejb.CounterLocal

@SessionScoped
class CounterClient extends Serializable {
  @EJB
  private var counter: CounterLocal = _

  /**
    * Gets the current value, increases it and return the old value.
    *
    * Actually, this method is not really elegant - because a method
    * should either perform a query or have a side effect; however,
    * its purpose is to simulate a more complex interaction with
    * our bean - involving both queries and actions - which would happen
    * in real-life situations.
    *
    * @return the value of the counter before it was increased
    */
  def getAndIncrease(): Int = {
    val result = counter.getValue

    counter.increase()

    result
  }


  /**
    * Resets the counter
    */
  def reset(): Unit =
    counter.reset()
}
