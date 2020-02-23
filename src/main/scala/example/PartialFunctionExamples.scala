package example

/**
  * Created by Bondarenko on Feb, 22, 2020
  * 22:18.
  * Project: learning-fp
  */
object PartialFunctionExamples extends App with NumOps {

  //see https://www.scala-lang.org/api/2.12.8/scala/PartialFunction.html

  val numbers = List(-2.0, -1, 0, 4, 9, 16)

  println("collect:")
  //selects only non-negative values and computes square roots on its
  numbers.collect(sqrtPF).foreach(println)
  //0.0
  //2.0
  //3.0
  //4.0

  println("map:")
  //selects ALL values and computes square roots on its
  numbers.map(sqrtFunc).foreach(println)
  //NaN
  //NaN
  //0.0
  //2.0
  //3.0
  //4.0

}

trait NumOps {
  import scala.math.sqrt

  val sqrtPF: PartialFunction[Double, Double] = {
    case x if x >= 0 => sqrt(x)
  }

  //the same!
  val sqrtPF1 = new PartialFunction[Double, Double] {
    def isDefinedAt(x: Double): Boolean = x >= 0

    def apply(x: Double): Double = sqrt(x)
  }

  def sqrtFunc(x: Double): Double = sqrt(x)

  //the same!
  val sqrtFuncDesugared = new Function1[Double, Double] {
    def apply(x: Double): Double = sqrt(x)
  }

}
