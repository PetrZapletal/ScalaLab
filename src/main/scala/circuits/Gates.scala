package circuits

/**
 * Created by petr on 12/25/13.
 */
abstract class Gates extends Simulation{

  def InverterDelay: Int

  class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()

    def getSignal = sigVal

    def setSignal(s: Boolean) = {
      if (s != sigVal) {
        sigVal = s
        actions foreach (_())
      }
    }

    def addAction(a: Action) = {
      actions = a :: actions
      a()
    }
  }

  def inverter(in: Wire, out: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSig = in.getSignal
      afterDelay(InverterDelay) (
        out setSignal !inputSig
      )
    }
    in addAction invertAction
  }

  def probe(in: Wire, name: String): Unit = {
    def probeAction(): Unit = {
      println(s"Probe $name - ${in.getSignal}")
    }
    in addAction probeAction
  }
}