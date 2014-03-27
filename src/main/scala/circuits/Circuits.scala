package circuits

/**
 * Created by petr on 12/25/13.
 */
abstract class Circuits extends Gates{

  //HA, Adder

  def inverterWrapper(in : Wire, out: Wire) = {
    inverter(in,out)
  }

}
