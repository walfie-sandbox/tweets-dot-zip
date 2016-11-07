/** data/js/tweets/yyyy_mm.js */
package walfie.grailbirds.domain

import scala.scalajs.js
import scala.scalajs.js.annotation._

// TODO: Missing geo
@js.native
class Tweet extends js.Object with CreatedAt {
  val id: String = js.native
  val text: String = js.native
  val user: User = js.native
  val source: String = js.native
  val entities: Entities = js.native
  @JSName("retweeted_status") val retweetedStatus: js.UndefOr[Tweet] = js.native
  @JSName("in_reply_to_status_id_str") val inReplyToStatusId: js.UndefOr[String] = js.native
  @JSName("in_reply_to_user_id_str") val inReplyToUserId: js.UndefOr[String] = js.native
}

@js.native
class User extends js.Object {
  val id: String = js.native
  val name: String = js.native
  @JSName("screen_name") val screenName: String = js.native
  @JSName("protected") val isProtected: Boolean = js.native
  @JSName("profile_image_url") val profileImageUrl: String = js.native
  val verified: Boolean = js.native
}

@js.native
class Entities extends js.Object {
  val media: js.Array[Media] = js.native
  val hashtags: js.Array[Hashtag] = js.native
  val urls: js.Array[Url] = js.native
  @JSName("user_mentions") val userMentions: js.Array[UserMention] = js.native
}

@js.native
class Hashtag extends js.Object with Indices {
  val text: String = js.native
}

@js.native
class UserMention extends js.Object with Indices {
  val id: String = js.native
  val name: String = js.native
  @JSName("screen_name") val screenName: String = js.native
}

@js.native
class Media extends js.Object with Indices {
  val id: String = js.native
  val url: String = js.native
  val sizes: js.Array[Size] = js.native
  @JSName("expanded_url") val expandedUrl: String = js.native
  @JSName("media_url") val mediaUrl: String = js.native
  @JSName("display_url") val displayUrl: String = js.native
}

@js.native
class Size extends js.Object {
  val width: Int = js.native
  val height: Int = js.native
  val resize: String = js.native
}

@js.native
class Url extends js.Object with Indices {
  val url: String = js.native
  @JSName("expanded_url") val expandedUrl: String = js.native
  @JSName("display_url") val displayUrl: String = js.native
}

