package scalas.PizzaTask

case class Point(row: Int, col: Int)

class Pizza(inputArr: Array[Array[String]], minIngredientCount: Int, maxSliceSize: Int) {
  
  private var sliceCount = 1
  
  private var lastPoint = Point(0, 0)
  
  /**
    * Matrix with ingredients
    */
  val pizza: Array[Array[String]] = inputArr
  
  /**
    * Matrix with rectangles of slice
    * - Positive numbers means, that ingredient into some group
    * - Zero is default value
    * - -1 means, that this ingredient can't find opposite one in some range
    */
  val pizzaMap: Array[Array[Int]] = Array.ofDim[Int](pizza.length, pizza(0).length)
  
  def printPizza(): Unit = printMatrix(pizza)
  
  def printPizzaMap(): Unit = printMatrix(pizzaMap)
  
  def printMatrix[T](mat: Array[Array[T]]): Unit = {
    mat.foreach(row =>
      println(row.mkString(" "))
    )
  }

//  /**
//    * Searching for opposite ingredient
//    *
//    * @param point start point
//    * @return point of opposite ingredient
//    */
//  def findOtherIngredient(point: Point): Point = {
//    val char = pizza(point.row)(point.col)
//    var notFound = true
//    var res = point
//    for {
//      r <- point.row until pizza.length
//      c <- point.col until pizza(0).length
//      if notFound
//    } {
//      if (pizza(r)(c) != char && pizzaMap(r)(c) == 0) {
//        notFound = !notFound
//        res = Point(r, c)
//      }
//    }
//    res
//  }
  
  /**
    * Searching for opposite ingredient
    *
    * @param point start point
    * @return point of opposite ingredient, if find it
    *         either start point
    */
  def findOtherIngredient(point: Point): Point = {
    val char = pizza(point.row)(point.col)
    var notFound = true
    var res = point
    for {
      r <- point.row until pizza.length
      c <- point.col until pizza(0).length
      if notFound
      if square(point, Point(r, c)) <= maxSliceSize
      if isEmptyRec(point, Point(r, c))
      if isContainMinIngredient(point, Point(r, c), minIngredientCount)
    } {
      if (pizza(r)(c) != char && pizzaMap(r)(c) == 0) {
        notFound = !notFound
        res = Point(r, c)
      }
    }
    if (res == point) pizzaMap(point.row)(point.col) = -1
    res
  }
  
  /**
    * @param begin (row, col)
    * @param end   (row, col)
    * @return true if rectangle not fill other's numbers (not part of other slice)
    */
  def isEmptyRec(begin: Point, end: Point): Boolean = {
    var recIsEmpty = true
    for {
      r <- begin.row to end.row
      c <- begin.col to end.col
      if recIsEmpty
    } {
      if (pizzaMap(r)(c) != 0) recIsEmpty = false
    }
    recIsEmpty
  }
  
  def fillRec(begin: Point, end: Point): Unit = {
    for {r <- begin.row to end.row
         c <- begin.col to end.col} {
      pizzaMap(r)(c) = sliceCount
    }
    sliceCount += 1
  }
  
  def findAloneIngredient: Point = {
    var result = Point(-1, -1)
    for {row <- pizzaMap.indices
         col <- pizzaMap(0).indices
         if result == Point(-1, -1)} {
      if (pizzaMap(row)(col) == 0) result = Point(row, col)
    }
    
    result
  }
  
  def square(begin: Point, end: Point): Int = {
    (end.row - begin.row + 1) * (end.col - begin.col + 1)
  }
  
  def isContainMinIngredient(begin: Point, end: Point, min: Int): Boolean = {
    var tCount, mCount = 0
    for {
      r <- begin.row to end.row
      c <- begin.col to end.col
    } {
      if (pizza(r)(c) == "T") tCount += 1
      if (pizza(r)(c) == "M") mCount += 1
    }
    
    tCount >= min && mCount >= min
  }
  
  def getAvailableMultipliers(position: Point, num: Int) = {
    var availableCount = 0
    
    val rangeOfMultiplier = getRangeOfMultipliers(num)
    println(position)
    for {
      multipliers <- rangeOfMultiplier
      (dRow, dCol) <- multipliers
    } {
      println(dRow, dCol)
      //top-right
      val top1: Point = Point(position.row - (dRow - 1), position.col - (dCol - 1))
      if (isValidPoint(top1)) {
        println(top1)
        availableCount += 1
      }
      val top2: Point = Point(position.row - (dCol - 1), position.col - (dRow - 1))
      if (isValidPoint(top2)) {
        println(top2)
        availableCount += 1
      }
      //right
      //bottom
      //left
    }
  }
  
  
  def isValidPoint(pos: Point): Boolean = {
    (0 <= pos.row && pos.row < pizzaMap.length) && (0 <= pos.col && pos.col < pizzaMap(0).length)
  }
  
  def getRangeOfMultipliers(num: Int): IndexedSeq[IndexedSeq[(Int, Int)]] = {
    (1 to num).map(getMultipliers)
  }
  
  def getMultipliers(num: Int): IndexedSeq[(Int, Int)] = {
    for {
      i <- 1 to math.sqrt(num).toInt
      if num % i == 0
    } yield (i, num / i)
  }

//  def zeroStage
  
}

