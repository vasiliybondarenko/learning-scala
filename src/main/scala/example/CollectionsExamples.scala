package example

/**
  * Created by Bondarenko on Jan, 30, 2020
 15:39.
 Project: learning-fp
  */
object CollectionsExamples extends App {

  //sliding
  (1 to 10)
    .sliding(2, 2)
    .toList //List(ArraySeq(1, 2), ArraySeq(3, 4), ArraySeq(5, 6), ArraySeq(7, 8), ArraySeq(9, 10)

  ((1 to 10)
    .sliding(2)) //List(ArraySeq(1, 2), ArraySeq(2, 3), ArraySeq(3, 4), ArraySeq(4, 5), ArraySeq(5, 6), ArraySeq(6, 7), ArraySeq(7, 8), ArraySeq(8, 9), ArraySeq(9, 10))

  //partitioning
  (1 to 10)
    .grouped(3)
    .toList //List(Range 1 to 3, Range 4 to 6, Range 7 to 9, Range 10 to 10)

  //foreach
  (1 to 10).foreach(println)

  //map
  List(1, 2, 3).map(_ % 2) //List(1, 0, 1)

  //flatMap
  List("One", "Two", "Three").flatMap(x => x.toList) //List(O, n, e, T, w, o, T, h, r, e, e)
  def dividers(n: Int): List[Int] = (2 until n).filter(x => n % x == 0).toList
  (1 to 6).flatMap(dividers).toList // List(2, 2, 3)

  //collect
  (1 to 10).collect { case x if (x % 2 == 0) => x.toBinaryString }.toList //List[String] = List(10, 100, 110, 1000, 1010)

  //slice
  (1 to 10).slice(2, 5).toList //List(3, 4, 5)

  //span
  (1 to 10).span(_ < 5) //(Range 1 to 4,Range 5 to 10)

  //partition
  (1 to 10).partition(_ % 2 == 0) //(Vector(2, 4, 6, 8, 10),Vector(1, 3, 5, 7, 9))

  //grouping
  (1 to 10).groupBy(_ % 3) //  HashMap(0 -> Vector(3, 6, 9), 1 -> Vector(1, 4, 7, 10), 2 -> Vector(2, 5, 8))

  //foldLeft
  (1 to 10).foldLeft("") { (a, b) =>
    s"$a, $b"
  } // String = , 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

  //reduceLeft
  (1 to 10).reduceLeft(_ + _) //55

  //zipping
  (1 to 10)
    .zip(10 to 20)
    .toList //List[(Int, Int)] = List((1,10), (2,11), (3,12), (4,13), (5,14), (6,15), (7,16), (8,17), (9,18), (10,19))

  //conditions
  (1 to 10).forall(_ % 2 == 0) //false
  List(2, 4, 8).forall(_ % 2 == 0) //true
  List.empty[Int].forall(_ % 2 == 0) //true
  (1 to 10).exists(_ % 2 == 0) //true
  List(1, 3, 7).exists(_ % 2 == 0) //false

  //initializations
  List.fill(10)(1) //List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

  List.tabulate(10)(_ % 4) //List(0, 1, 2, 3, 0, 1, 2, 3, 0, 1)

  LazyList
    .unfold(1) { state =>
      Some(state * state -> (state + 1))
    }
    .take(10)
    .toList // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

  List.iterate(1, 10)(x => x + 2) //List(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)

  //apply
  val m = Map(1 -> "Monday", 2 -> "Tuesday", 3 -> "Wednesday")
  //m is PartialFunction
  m(1) //Monday
  m.isDefinedAt(1) //true
  m.isDefinedAt(0) //false

  (-2 to 10).collect(m) //Vector(Monday, Tuesday, Wednesday)

  Array(1, 2, 3)(0) //1

  Set(1, 2, 3)(1) //true
  Set(1, 2, 3)(0) //false

  //todo: see also
  // https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html
  // https://docs.scala-lang.org/overviews/collections-2.13/trait-iterable.html

}
