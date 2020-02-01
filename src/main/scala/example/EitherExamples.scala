package example

import scala.util.Try

/**
Algebraic data type

A type defined by providing several alternatives,
each of which comes with its own constructor.
It usually comes with a way to decompose the type through pattern matching.
The concept is found in specification languages and functional programming languages.
Algebraic data types can be emulated in Scala with case classes.

https://docs.scala-lang.org/glossary/#algebraic-data-type
  */
object EitherExamples extends App {

  def parseInt(s: String): Either[String, Int] = Try(s.toInt).toEither.fold(
    _ => Left[String, Int](s"'$s' is not a number"),
    value => Right[String, Int](value)
  )

  def divide(a: Int, b: Int): Either[String, Int] =
    if (b == 0) Left[String, Int]("Divide by 0!") else Right[String, Int](a / b)

  //can't be composed with rest methods
  def divide1(a: Int, b: Int): Try[Int] = Try(a / b)

  def parseAndDivide(a: String, b: String): Either[String, Int] =
    for {
      x <- parseInt(a)
      y <- parseInt(b)
      result <- divide(x, y) //with divide1 doesn't compile!
    } yield result

  println(parseAndDivide("21", "7"))
  println(parseAndDivide("21", "0"))
  println(parseAndDivide("21", "x"))

}
