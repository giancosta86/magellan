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

import javax.naming.InitialContext

import info.gianlucacosta.clustering.magellan.ejb.CounterRemote

/**
  * This service implements the CounterRemote interface by calling the methods of
  * our remote stateful bean
  */
object CounterBeanService extends CounterRemote {
  /*
  "lazy val" is an interesting and very effective Scala feature
   */
  private lazy val bean: CounterRemote = {
    val context = new InitialContext()

    val beanLookupName = "Magellan/magellan-ejb/CounterBean!info.gianlucacosta.clustering.magellan.ejb.CounterRemote"

    context.lookup(beanLookupName).asInstanceOf[CounterRemote]
  }


  override def reset(): Unit =
    bean.reset()

  override def getValue: Int =
    bean.getValue

  override def increase(): Unit =
    bean.increase()
}
