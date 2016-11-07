/** data/js/user_details.js */
package walfie.grailbirds.domain

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
class UserDetails extends js.Object with CreatedAt {
  val id: String = js.native
  val bio: String = js.native
  @JSName("screen_name") val screenName: String = js.native
  @JSName("full_name") val fullName: String = js.native
}

