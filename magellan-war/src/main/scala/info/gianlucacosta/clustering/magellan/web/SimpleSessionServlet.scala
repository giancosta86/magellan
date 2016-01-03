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

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import info.gianlucacosta.clustering.magellan.web.HttpSessionExtension._

/*
In lieu of static members, Scala employs a "companion object"
 */
object SimpleSessionServlet {
  private val ViewCountKey = "viewCount"
}

@WebServlet(Array("/"))
class SimpleSessionServlet extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val writer = response.getWriter

    writer.println("<h1>Session value example</h1>")

    val session = request.getSession()

    val viewCount = session
      .getOptionAttribute(SimpleSessionServlet.ViewCountKey)
      .getOrElse(0)


    writer.println(s"<p>This page was requested ${viewCount} times")

    session.setAttribute(
      SimpleSessionServlet.ViewCountKey,
      viewCount + 1
    )
  }
}
