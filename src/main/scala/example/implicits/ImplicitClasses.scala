package example.implicits

/**
 * Created by Bondarenko on Mar, 14, 2020 
 * 19:42.
 * Project: learning-fp
 */
object ImplicitClasses extends App {
  import Implicits._

  println("scala".multiply(2))
  println("scala".multiply(3))
}


object Implicits {
  implicit class StringOps(str: String) {
	 def multiply(n: Int) = Range(0, n).map(_ => str).mkString
  }
}

