package circuits

/**
 * Created with IntelliJ IDEA.
 * User: petr
 * Date: 11/17/13
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */

object sim extends Circuits with Configuration{

}

object Runner {
  def main(args: Array[String]) {
    println("Hello, world!")

    val in, out = new sim.Wire
    sim.probe(out, "inv probe")
    in.setSignal(false)
    sim.inverter(in, out)

    sim.run()

  }

}
