package example

import scala.io.Source
import scala.util.Try

/**
 * Created by Bondarenko on Jan, 29, 2020
 * 12:08.
 * Project: learning-fp
 */
object FileReader extends App {
  //will throw NoSuchElementException if fileName is None
  //will throw any exception in case of IO errors (file doesn't exist and so on)
  private def loadData(fileName: Option[String]): String = {
    Option(
      this.getClass.getClassLoader.getResourceAsStream(s"data/${fileName.get}")
    ).map(t => Source.fromInputStream(t).mkString)
  }.get

  //better
  //but still not safe
  private def loadData1(fileName: Option[String]): Option[String] =
    fileName.map { fName =>
      Source
        .fromInputStream(
          this.getClass.getClassLoader.getResourceAsStream(s"data/${fName}")
        )
        .getLines()
        .mkString
    }

  //safe
  private def loadData2(fileName: String) =
    Try {
      Source
        .fromInputStream(
          this.getClass.getClassLoader.getResourceAsStream(s"data/${fileName}")
        )
        .getLines()
        .mkString
    }

}
