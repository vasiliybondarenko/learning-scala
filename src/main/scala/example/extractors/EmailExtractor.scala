package example.extractors

/**
 * Created by Bondarenko on Mar, 13, 2020
 * 21:22.
 * Project: learning-fp
 */
object EmailExtractor extends App {
  "xxxxx@gmail.com" match {
    case EMail(user, domain) => println(s"user: $user, domain: $domain")
    case _ => println("It's not a valid email!")
  }
}

object EMail {

  def apply(user: String, domain: String) = user + "@" + domain

  def unapply(str: String): Option[(String, String)] = {
    val parts = str.split("@")
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }

}
