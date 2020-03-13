package example

import scala.io.Source
import scala.util.{Failure, Try}

/**
 * Created by Bondarenko on Jan, 30, 2020
 * 12:57.
 * Project: learning-fp
 */
object TryExamples extends App {

  case class Item(id: Long, name: String, description: Option[String])

  //works for scala 2.13 only
  def readLines(path: String): Try[LazyList[String]] =
    Try(
      Source
        .fromURL(getClass.getClassLoader.getResource(path))
        .getLines()
        .to(LazyList)
    ).filter(!_.isEmpty)

  def parseLong(s: String): Try[Long] = Try(s.toLong)

  def parseLine(s: String): Try[Item] = s.split(",").toList match {
    case id :: name :: tail =>
      parseLong(id).map(id => Item(id, name, tail.headOption))
    case Nil => Failure(new Exception("Line is empty"))
  }

  def parseLines(lines: LazyList[String]): Try[LazyList[Item]] =
    lines.foldLeft(Try(LazyList[Item]())) { (items, line) =>
      for {
        parsedItems <- items
        newItem <- parseLine(line)
      } yield newItem #:: parsedItems
    }

  def readCSV(path: String): Try[LazyList[Item]] =
    for {
      lines <- readLines(path)
      allItems <- parseLines(lines)
    } yield allItems.reverse

}
