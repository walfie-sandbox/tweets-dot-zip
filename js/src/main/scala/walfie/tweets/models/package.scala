package walfie.tweets

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

package models {
  /** data/js/tweet_index.js */
  @js.native
  class TweetsFile extends js.Object {
    val year: Int = js.native
    val month: Int = js.native
    @JSName("file_name") val fileName: String = js.native
    @JSName("var_name") val varName: String = js.native
    @JSName("tweet_count") val tweetCount: Int = js.native
  }

  /** data/js/user_details.js */
  @js.native
  class UserDetails extends js.Object with CreatedAt {
    val id: String = js.native
    val bio: String = js.native
    @JSName("screen_name") val screenName: String = js.native
    @JSName("full_name") val fullName: String = js.native
  }

  /** data/js/payload_details.js */
  @js.native
  class PayloadDetails extends js.Object with CreatedAt {
    val tweets: Int = js.native
    val lang: String = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  // TODO: Missing geo
  @js.native
  class Tweet extends js.Object with CreatedAt {
    val id: String = js.native
    val text: String = js.native
    val user: User = js.native
    val source: String = js.native
    val entities: Entities = js.native
    @JSName("in_reply_to_status_id_str") val inReplyToStatusId: js.UndefOr[String] = js.native
    @JSName("in_reply_to_user_id_str") val inReplyToUserId: js.UndefOr[String] = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class User extends js.Object {
    val id: String = js.native
    val name: String = js.native
    @JSName("screen_name") val screenName: String = js.native
    @JSName("protected") val isProtected: Boolean = js.native
    @JSName("profile_image_url") val profileImageUrl: String = js.native
    val verified: Boolean = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class Entities extends js.Object {
    val media: js.Array[Media] = js.native
    val hashtags: js.Array[Hashtag] = js.native
    val urls: js.Array[Url] = js.native
    @JSName("user_mentions") val userMentions: js.Array[UserMention] = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class Hashtag extends js.Object with Indices {
    val text: String = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class UserMention extends js.Object with Indices {
    val id: String = js.native
    val name: String = js.native
    @JSName("screen_name") val screenName: String = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class Media extends js.Object with Indices {
    val id: String = js.native
    val url: String = js.native
    val sizes: js.Array[Size] = js.native
    @JSName("expanded_url") val expandedUrl: String = js.native
    @JSName("media_url") val mediaUrl: String = js.native
    @JSName("display_url") val displayUrl: String = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class Size extends js.Object {
    val width: Int = js.native
    val height: Int = js.native
    val resize: String = js.native
  }

  /** data/js/tweets/yyyy_mm.js */
  @js.native
  class Url extends js.Object with Indices {
    val url: String = js.native
    @JSName("expanded_url") val expandedUrl: String = js.native
    @JSName("display_url") val displayUrl: String = js.native
  }
}

package object models {
  /** Convenience trait for accessing start/end indices */
  @js.native
  trait Indices extends js.Object {
    val indices: js.Array[Int] = js.native
  }

  @js.native
  trait CreatedAt extends js.Object {
    @JSName("created_at") val createdAt: String = js.native
  }

  implicit class IndicesOps(val obj: Indices) extends AnyVal {
    def startIndex: Int = obj.indices(0)
    def endIndex: Int = obj.indices(1)
  }

  implicit class CreatedAtOps(val obj: CreatedAt) extends AnyVal {
    def createdAtDate: js.Date = new js.Date(obj.createdAt)
  }
}

