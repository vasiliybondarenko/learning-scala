package example.traits

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
  * Created by Bondarenko on Feb, 26, 2020
  * 14:13.
  * Project: learning-fp
  */
class CalculatorTest extends AnyFlatSpec with Matchers {
  "calculator" should "read and calculate expressions from input" in {
    trait TestInput extends Input {
      def expressions: LazyList[String] =
        LazyList("12*5", "12+5", "12-5", "12/5")
    }

    val calc = new CalculatorWithDefaultParser with TestInput

    calc.calculateAll shouldBe (
      LazyList(60, 17, 7, 2)
    )

  }

}
