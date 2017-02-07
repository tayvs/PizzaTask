package scalas.PizzaTask

case class Point(row : Int, col : Int)

class Pizza(inputArr : Array[Array[String]]) {

  private var sliceCount = 1

  /**
    * Matrix with ingredients
    */
  val pizza = inputArr

  /**
    * Matrix with rectangles of slice
    */
  val pizzaMap = Array.ofDim[Int](pizza.length, pizza(0).length)

  def printPizza = printMatrix(pizza)
  def printPizzaMap = printMatrix(pizzaMap)

  def printMatrix[T](mat : Array[Array[T]]) = {
    mat.foreach(row =>
      println(row.mkString(" "))
    )
  }

  def findOtherIngredient(point: Point): Point = {
    val char = pizza(point.row)(point.col)
    var notFound = true
    var res = point
    for {r <- point.row until pizza.length
         c <- point.col until pizza(0).length
         if notFound } {
      if (pizza(r)(c) != char) {
        notFound = !notFound
        res = Point(r, c)
      }
    }
    res
  }

  /**
    * @param begin (row, col)
    * @param end (row, col)
    * @return true if rectangle not fill other's numbers (not part of other slice)
    */
  def isEmptyRec(begin : Point, end : Point): Boolean = {
    var recIsEmpty = true
    for {r <- begin.row to end.row
         c <- begin.col to end.col
         if recIsEmpty} {
      if (pizzaMap(r)(c) != 0) recIsEmpty = false
    }
    recIsEmpty
  }

  def fillRec(begin : Point, end : Point) = {
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
}

