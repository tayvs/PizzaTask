package scalas.PizzaTask.util

/**
  * Created by tayvs on 10.02.2017.
  */
object Numbers {


  /**
    * Check for available pieces and count them.
    * Search pieces with one available piece
    *
    * @param position of element
    * @param min      min count of each ingredient
    * @param max      max slice square
    */
  def searchSingleton(position: Point, min: Int, max: Int) = {
    var availableCount = 0

    val rangeOfMultiplier = getRangeOfMultipliers(min, max)
    println(position)
    for {
      multipliers <- rangeOfMultiplier
      (dRow, dCol) <- multipliers
    } {

    }
  }


  def getDeltasForMatrix(seq: Seq[(Int, Int)]): Seq[(Int, Int)] = {
    val preparedInput = seq.map(mult => (mult._1 - 1, mult._2 - 1))
    val vars: List[List[(Int, Int)]] = for {
      sign <- List(1, -1)
      c <- preparedInput
    } yield {
      List(
        (sign * c._1, sign * c._2),
        (sign * c._1, -1 * sign * c._2)
      )
    }

    vars.flatten.toSet.toSeq
  }

  def getRangeOfMultipliers(from: Int, max: Int): IndexedSeq[IndexedSeq[(Int, Int)]] = {
    (from to max).map(getMultipliers)
  }

  /**
    * @param num Square of slice
    * @return sequence with delta for row and col coordinates
    */
  def getMultipliers(num: Int): IndexedSeq[(Int, Int)] = {
    val list = for {
      i <- 1 to math.sqrt(num).toInt
      if num % i == 0
    } yield
      List(
        (i, num / i),
        (num / i, i)
      )

    list.flatten
  }

}
