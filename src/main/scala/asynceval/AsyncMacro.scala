package asynceval

import scala.concurrent.{Promise, Future,Await}
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Failure, Success}
import util.Random.nextInt
import scala.concurrent.duration._
import scala.async.Async.{async, await}

object AsyncMacro {

  val gen = new Random()

  def main(args: Array[String]) {
    println(s"Test started [${System.currentTimeMillis()}]")

    val composition = async {
      println(s"Res1 started [${System.currentTimeMillis()}]")
      val res1 = slowComputation

      println(s"Res2 started [${System.currentTimeMillis()}]")
      val res2 = slowComputation

      await(res1) + await(res2)
    }

    val finalResult = Await.result(composition, 20.seconds )
    println(s"Test finished with result [${finalResult}]")

    println(s"Test finished [${System.currentTimeMillis()}]")
  }

  def slowComputation: Future[Int] = Future {
    Thread.sleep(getDelayNumber)
    21
  }

  private def getDelayNumber: Int = gen.nextInt(10000) + 1000


}
