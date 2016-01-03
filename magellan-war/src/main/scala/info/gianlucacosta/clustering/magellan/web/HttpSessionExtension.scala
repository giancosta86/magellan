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

package info.gianlucacosta.clustering.magellan.web

import javax.servlet.http.HttpSession


object HttpSessionExtension {
  /**
    * Implicitly converts a session to an HttpSessionExtension,
    * in order to have dedicated methods
    * @param session the session to convert
    * @return an HttpSessionExtension object
    */
  implicit def convertHttpSessionToHttpSessionExtension(session: HttpSession): HttpSessionExtension = {
    require(session != null)

    new HttpSessionExtension(session)
  }
}

/**
  * Wraps an HttpSession, providing useful methods
  *
  * @param session the session to wrap
  */
class HttpSessionExtension(private val session: HttpSession) {
  /**
    * Returns Some[T](value) if the requested attribute exists in the session, None otherwise
    * @param name the attribute name
    * @tparam T the expected type
    * @return always a suitable Option[T]
    */
  def getOptionAttribute[T](name: String): Option[T] = {
    val value: T = session.getAttribute(name).asInstanceOf[T]

    Option(value)
  }
}
