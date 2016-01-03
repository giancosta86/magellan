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

package info.gianlucacosta.clustering.magellan.ejb

import javax.ejb.{Local, Remote, Stateful}

@Remote(Array(classOf[CounterRemote]))
@Local(Array(classOf[CounterLocal]))
@Stateful
class CounterBean extends CounterLocal with CounterRemote {
  private var value = 0

  override def reset(): Unit = {
    value = 0
  }

  override def getValue: Int = value

  override def increase(): Unit = {
    value += 1
  }
}
