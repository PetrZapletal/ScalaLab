package asynceval

import scala.concurrent.{Promise, Future}
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

case class LameExcuse(msg: String) extends Exception(msg)
case class TaxCut(reduction: Int)
case class RealCut(reduction: String)

object Politicians {

  def redeemCampaignPledge(): Promise[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)

      if (System.currentTimeMillis() % 2 == 0) {
        p.success(TaxCut(10))
        println("We reduced the taxes! You must reelect us!!!!1111")
      } else {
        p.failure(LameExcuse("global crisis !"))
        println("Sorry guys, but reelect us anyway :P")
      }

    }
    p
  }

  def main(args: Array[String]) {
    println("Politicians promises")

    val taxCutPromise: Promise[TaxCut] = redeemCampaignPledge()

    while (!taxCutPromise.isCompleted) {
      println("ZZZzz");
      Thread.sleep(1000);
    }

    taxCutPromise.future.onComplete {
      case Success(TaxCut(reduction)) => {
        println("They really lower taxes by " + reduction + " !!!")
      }
      case Failure(reason) => println("Tax cut postponed due to " + reason.getMessage)
    }

    val okReduction = taxCutPromise.future.flatMap {
      cut =>
        isReductionOk(cut.reduction)
    }

    okReduction.onComplete {
      case Success(result) => println("S:" + result)
      case Failure(reason) => println("F:" + reason)
    }

  }

  def isReductionOk( reduction: Int ): Future[Boolean] = Future{

    true
  }

}
