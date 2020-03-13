package example.traits

import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bondarenko on Feb, 24, 2020
 14:11.
 Project: learning-fp
 */
object TraitsMixins extends App {

  println("q1:")
  val q1 = new BasicIntQueue with Incrementing with Filtering
  q1.put(-1)
  q1.put(1)
  println(q1.get()) //2

  println("q2:")
  val q2 = new BasicIntQueue with Incrementing with Doubling
  q2.put(0)
  q2.put(2)
  println(q2.get()) //1
  println(q2.get()) //5

  println("q3:")
  val q3 = new BasicIntQueue with Doubling with Incrementing
  q3.put(0)
  q3.put(2)
  println(q3.get()) //4
  println(q3.get()) //6

}

trait IntQueue {
  def get(): Int
  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get() = buf.remove(0)

  def put(x: Int) { buf += x }
}

trait Doubling extends IntQueue {
  //super is NOT defined at compile time!
  abstract override def put(x: Int) { super.put(2 * x) }
}

trait Incrementing extends IntQueue {
  //super is NOT defined at compile time!
  abstract override def put(x: Int) { super.put(x + 1) }
}
trait Filtering extends IntQueue {
  //super is NOT defined at compile time!
  abstract override def put(x: Int) { if (x >= 0) super.put(x) }
}
