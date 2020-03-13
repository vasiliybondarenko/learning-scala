package example.traits

import java.util.Scanner
import scala.io.Source

/**
 * Created by Bondarenko on Feb, 25, 2020
 * 11:42.
 * Project: learning-fp
 */
object SelfTypeExamples extends App {

  println("console calculator:")
  println("input an expression: ('q' for exit)")
  val calculator0 = new CalculatorWithDefaultParser with ConsoleInput {}
  calculator0.calculateAll.foreach(println)

  println("calculator1:")
  val calculator1 = new CalculatorWithDefaultParser with SimpleInput {}
  calculator1.calculateAll.foreach(println)

  println("calculator2:")
  val calculator2 = new CalculatorWithDefaultParser with FileInput {}
  calculator2.calculateAll.foreach(println)

}

trait Input {
  def expressions: LazyList[String]
}

trait ConsoleInput extends Input {

  def expressions: LazyList[String] = {
    val sc = new Scanner(System.in)
    LazyList.continually(sc.next()).takeWhile(!_.contains('q'))
  }
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
  def parse(s: String): Option[(String, String, String)]
}

trait DefaultParser extends Parser {

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

trait CalculatorWithDefaultParser extends Calculator with DefaultParser {
  self: Input =>
}
