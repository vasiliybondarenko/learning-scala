package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.util
import scala.util.{Failure, Success}

class HelloSpec extends AnyFlatSpec with Matchers {

  //todo: add errors handling tests
  "TryExamples" should "read and parse data from CVS file" in {
    import TryExamples._

    readCSV("items_1.csv") shouldBe Success(
      LazyList(
        Item(101101101001L, "item1", None),
        Item(101101101002L, "item2", Some("some description"))
      )
    )
  }

  it should "failed for empty CSV file" in {
    import TryExamples._
    readCSV("items_2.csv").isFailure shouldBe (true)
  }

  "EitherExamples" should "parse and divide numbers" in {
    import EitherExamples._

    parseAndDivide("12", "2") shouldBe (Right[String, Int](6))
    parseAndDivide("12", "") shouldBe (Left[String, Int]("'' is not a number"))
    parseAndDivide("12", "0") shouldBe (Left[String, Int]("Divide by 0!"))
  }

  "LazyListExamples" should "calculate fibonacci numbers" in {
    import LazyListExamples._

    fibFrom(1, 1).take(10).toList shouldBe
      List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)

  }

  "LazyListExamples" should "calculate factorials" in {
    import LazyListExamples._

    factorialsGeneric[Long].take(5).toList shouldBe
      List(1, 2, 6, 24, 120)

    factNum(5, 5) shouldBe (120)

  }

}
