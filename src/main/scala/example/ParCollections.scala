package example

import scala.collection.parallel.CollectionConverters._
import Math._

/**
 * Created by Bondarenko on Feb, 01, 2020
 22:17.
 Project: learning-fp
 */
object ParCollections extends App {

  def isPrime(n: Int): Boolean = (2 to (sqrt(n).toInt + 1)).forall(n % _ != 0)

  val xxx = (1 to 10000000).par

  def exec[A](task: => A) = {
    val start = System.nanoTime()
    val result = task
    println(
      s"execution time: ${(System.nanoTime() - start) / 1000000}. Result: ${result}"
    )
    result
  }

  exec {
    xxx.filter(isPrime).take(1000).foreach(println)
  }

}
