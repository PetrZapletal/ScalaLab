package betting

import scala.io.Source
import scala.collection.mutable

/*
 * Created by petr on 28.3.14.
 */

/*
 * Calculates and displays the profit or loss I would have made for each team if I had bet £10 with Bet365 on that team to win every game.
 * No Fees.
 */

object BestBetCalc {

  case class BetRecord(row: String) {
    val data = row.split(SEPARATOR)

    //Home Team
    val homeTeam = data(2)

    //Away Team
    val awayTeam = data(3)

    //Full Time Home Team Goals
    val FTHG = data(4)

    //Full Time Away Team Goals
    val FTAG = data(5)

    //Bet365 home win odds
    val B365H = data(23).toDouble

    //Bet365 draw odds
    val B365D = data(24).toDouble

    //Bet365 away win odds
    val B365A = data(25).toDouble

    override def toString = "BetRecord: HT=" + homeTeam + " AT=" + awayTeam + " FTHG=" + FTHG + " FTAG=" + FTAG +
      " B365H=" + B365H + " B365D=" + B365D + " B365A=" + B365A
  }

  final val PATH = "src/main/resources/09-10.csv"
  final val SEPARATOR = ","
  final val BET = 10

  def main(args: Array[String]) {

    val teams = mutable.ListMap[String, Double]()

    val records = Source.fromFile(PATH).getLines().drop(1).map(row => BetRecord(row))

    for {record <- records} {

      //println(record)
      if (!teams.contains(record.homeTeam)) teams += record.homeTeam -> 0.0
      if (!teams.contains(record.awayTeam)) teams += record.awayTeam -> 0.0

      if (record.FTHG > record.FTAG) {
        teams(record.homeTeam) += BET * record.B365H
        teams(record.awayTeam) -= BET
      } else if (record.FTHG < record.FTAG) {
        teams(record.homeTeam) -= BET
        teams(record.awayTeam) += BET * record.B365A
      } else if (record.FTHG == record.FTAG) {
        teams(record.homeTeam) -= BET
        teams(record.awayTeam) -= BET
      }

      //println(s"${record.homeTeam}:${teams(record.homeTeam)}");
      //println(s"${record.awayTeam}:${teams(record.awayTeam)}");
    }

    teams.toList.sortBy(t => -t._2).map(t => println(s"${t._1}: ${Math.round(t._2)}"))
  }
}
