package example.implicits

/**
 * Created by Bondarenko on Mar, 14, 2020 
 19:53.
 Project: learning-fp
 */
object ImplicitParameters extends App{

  def sum[T](elems: Seq[T])(implicit numeric: Numeric[T]) = {
    elems.sum(numeric)
  }

  println(sum(List(1, 2, 3)))

}



