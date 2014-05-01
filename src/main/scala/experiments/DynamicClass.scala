package experiments

/**
 * Created by petr on 1.5.14.
 */
object DynamicClass extends App {

  val dynPerson = Map[String, Any](
    "name" -> "Petr",
    "surname" -> "Zapletal",
    "action" -> ((obj: Map[String, Any]) => "resting"),
    "toString" -> ((obj: Map[String, Any]) => obj("name") + " " + obj("surname"))
  )

  println(dynPerson("name"))
  println(dynPerson("surname"))

  val actionFun = dynPerson("action").asInstanceOf[Function1[Map[String,Any],Any]]
  println(actionFun.apply(dynPerson))

  val updatedPerson = dynPerson.updated("action", ((obj: Map[String, Any]) => "running"))
  println(actionFun.apply(updatedPerson))

  val updatedActionFun = updatedPerson("action").asInstanceOf[Function1[Map[String,Any],Any]]
  println(updatedActionFun.apply(updatedPerson))

  val toStringFun = updatedPerson("toString").asInstanceOf[Function1[Map[String,Any],Any]]
  println(toStringFun.apply(updatedPerson))


}
