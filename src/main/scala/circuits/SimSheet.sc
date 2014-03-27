import circuits.{Configuration, Circuits}
/**
 * Created by petr on 12/26/13.
 */
object SimSheet{
  object sim extends Circuits with Configuration
  import sim._

  val in, out = new Wire

  in.setSignal(false)
  inverterWrapper(in, out)
  print(out.getSignal)
  run()





}