package experiments

import scalaz._
import std.option._, std.list._
/**
 * Created by petr on 21.5.14.
 */
object ScalazFeatures extends App{

  println(Apply[Option].apply2(some(1), some(2))((a, b) => a + b))

  println(Traverse[List].traverse(List(1, 2, 3))(i => some(i)))

}
