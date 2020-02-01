package example

import scala.annotation.tailrec

object LazyListExamples extends App {
  import scala.math.Numeric.Implicits._

  def fibFrom(a: Int, b: Int): LazyList[Int] = a #:: fibFrom(b, a + b)

  def exp(x: Long, a: Long): LazyList[Long] = (x * a) #:: exp(x * a, a)

  def factorialsGeneric[A: Numeric] = {
    val num = implicitly[Numeric[A]]
    factNumbersGeneric(num.one, num.one)
  }

  //lazy recursive implementation
  private def factNumbers(n: Int, fact: Int): LazyList[Int] =
    fact #:: factNumbers(n + 1, fact * (n + 1))

  //recursive implementation
  @tailrec
  def factNum(n: Int, fact: Int): Int =
    if (n == 1) fact else factNum(n - 1, fact * (n - 1))

  private def factNumbersGeneric[A: Numeric](n: A, fact: A): LazyList[A] = {
    val num = implicitly[Numeric[A]]
    fact #:: factNumbersGeneric(n + num.one, fact * (n + num.one))
  }

}
