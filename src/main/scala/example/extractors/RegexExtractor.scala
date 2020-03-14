package example.extractors

import scala.util.matching.Regex

/**
 * Created by Bondarenko on Mar, 13, 2020 
 * 21:42.
 * Project: learning-fp
 */
object RegexExtractor extends App{
  val emailPattern: Regex = "([a-zA-Z]+)@([a-zA-Z.]+)".r

  def matchEmail(s: String): (String, String) = s match {
    case emailPattern(user, domain) => user -> domain
  }

  println(matchEmail("xxxx@gmail.com"))
  println(matchEmail("gmail.com"))//MatchError



}
