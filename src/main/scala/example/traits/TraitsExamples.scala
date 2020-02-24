package example.traits

/**
  * Created by Bondarenko on Feb, 24, 2020
  * 12:32.
  * Project: learning-fp
  */
object TraitsExamples extends App {
  val python = new SimpleMovable with Swimmable with Crawlable
  println(python.move)

  val duck = new SimpleMovable with Flyable with Swimmable
  println(duck.move)

}

class SimpleMovable extends Movable {
  def move: String = "move"
}

trait Movable {
  def move: String
}

trait Flyable extends Movable {
  abstract override def move: String = {
    s"fly and ${super.move}"
  }
}

trait Swimmable extends Movable {
  abstract override def move: String = {
    s"swim and ${super.move}"
  }
}

trait Crawlable extends Movable {
  abstract override def move: String = {
    s"crawl and ${super.move}"
  }
}
