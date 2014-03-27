package circuits

import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: petr
 * Date: 11/17/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */

abstract class Simulation {
  type Action = () => Unit
  case class Event(time : Int, action: Action)
  type Agenda = List[Event]
  private var agenda: Agenda = List()

  var currTime: Int = 0
  def getCurrentTime: Int = currTime

  def afterDelay(delay: Int)(block: => Unit) : Unit = {
    val item = Event(currTime + delay, () => block)
    agenda = insert (agenda, item)
  }

  def insert(agenda : Agenda, event : Event) : Agenda =
    agenda match {
      case first::rest if first.time <= event.time => first :: insert(rest, event)
      case _ => event::agenda
    }

  def run() = {
    println("*** Simulation start ***")
    println(s"Time: ${(new Date).getTime} millis. Events: ${agenda.size}" )
    loop
  }

  def loop(): Unit = {
    agenda match {
      case first::rest => {
        first.action()
        currTime = first.time
        agenda = rest
        loop
      }

      case _ =>
        println("*** Simulation finished ***")
        println(s"Time: ${(new Date).getTime} millis")
    }
  }
}