package example.traits

import scala.io.Source

/**
  * Created by Bondarenko on Feb, 25, 2020
  * 11:42.
  * Project: learning-fp
  */
object SelfTypeExamples extends App {

  println("calculator1:")
  //creating an instance of Calculator with
  val calculator1 = new Calculator with Parser with SimpleInput {}
  calculator1.calculateAll.foreach(println)

  println("calculator2:")
  val calculator2 = new Calculator with Parser with FileInput {}
  calculator2.calculateAll.foreach(println)

}

trait Input {
  def expressions: LazyList[String]
}

trait SimpleInput extends Input {
  def expressions: LazyList[String] = LazyList("12+5", "60/5")
}

trait FileInput extends Input {
  def expressions: LazyList[String] =
    Source
      .fromURL(getClass.getClassLoader.getResource("expressions.txt"))
      .getLines()
      .to(LazyList)
}

trait Parser {

  private val exprPattern = "(-?[0-9]+)([*\\-+/])([0-9]+)".r

  def parse(s: String) = s match {
    case exprPattern(a, op, b) => Some((a, op, b))
    case _                     => None
  }
}

trait Calculator {
  //it means the trait must inherit both Input and Parser traits while creating an instance of Calculator
  //a Scala way to make dependency injection in compile time
  self: Input with Parser =>

  protected def value(a: Int, op: String, b: Int) = op match {
    case "+" => a + b
    case "-" => a - b
    case "*" => a * b
    case "/" => a / b
  }

  protected def calculateExpr(s: String) = parse(s).map {
    case (a, op, b) => value(a.toInt, op, b.toInt)
  }

  def calculateAll: LazyList[Int] =
    expressions.map(calculateExpr).collect { case Some(value) => value }

}
