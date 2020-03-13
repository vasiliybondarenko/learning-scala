package example

/**
 * Created by Bondarenko on Mar, 13, 2020
 21:22.
 Project: learning-fp
 */
object Extractors extends App {}

object EMail {

  def apply(user: String, domain: String) = user + "@" + domain

  def unapply(str: String): Option[(String, String)] = {
    val parts = str.split("@")
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }

}
