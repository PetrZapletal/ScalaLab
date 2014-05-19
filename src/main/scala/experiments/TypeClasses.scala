package experiments

/**
 * Created by petr on 19.5.14.
 */


//trait + default implementations in companion object
object Numberz {

  trait NumberLike[T] {
    def plus(x: T, y: T): T

    def minus(x: T, y: T): T

    def mult(x: T, y: T): T

    def div(x: T, y: T): T

    def mod(x: T, y: T): T

    def one : T

    def zero : T
  }

  implicit object NumberLikeInt extends NumberLike[Int] {

    override def plus(x: Int, y: Int): Int = x + y

    override def minus(x: Int, y: Int): Int = x - y

    override def mult(x: Int, y: Int): Int = x * y

    override def div(x: Int, y: Int): Int = x / y

    override def mod(x: Int, y: Int): Int = x % y

    override def one: Int = 1

    override def zero: Int = 0

  }

  implicit object NumberLikeDouble extends NumberLike[Double] {

    override def plus(x: Double, y: Double): Double = x + y

    override def minus(x: Double, y: Double): Double = x - y

    override def mult(x: Double, y: Double): Double = x * y

    override def div(x: Double, y: Double): Double = x / y

    override def mod(x: Double, y: Double): Double = x % y

    override def one: Double = 1.0

    override def zero: Double = 0
  }

}

//some operation that can be used with multiple types via typeclass
object Math {

  import Numberz.NumberLike

  def mean[T](xs: Vector[T])(implicit cat: NumberLike[T]) = cat.div(sum(xs), sum(xs.map(_ => cat.one)))

  def sum[T](xs: Vector[T])(implicit cat: NumberLike[T]) = xs.reduce(cat.plus(_, _))

}

//custom number system based on String :-)

object StringNumbersImplicits {

  import Numberz.NumberLike

  implicit object StringSystem extends NumberLike[String] {

    override def plus(x: String, y: String): String = x + y

    override def minus(x: String, y: String): String = x.substring(0, y.length)

    override def mult(x: String, y: String): String = plus(x, y)

    override def div(x: String, y: String): String = minus(x, y)

    override def mod(x: String, y: String): String = x.substring(y.length - x.length, y.length)

    override def zero: String = ""

    override def one: String = "1"
  }

}


//execution
object TypeClasses extends App {

  import StringNumbersImplicits._

  val ints = Vector[Int](3, 5, 21, 36, 2, -23, 5, 213, -34, 0)

  val doubles = Vector[Double](2.3, 553.2, -213.5, 23, 0.123, 0, -23.2)

  val strs = Vector[String]("dasd", "as", "123dsa", "as", "oewvs", "", "fytr")

  println(Math.mean[Int](ints))

  println(Math.mean[Double](doubles))

  println(Math.mean[String](strs))

}
