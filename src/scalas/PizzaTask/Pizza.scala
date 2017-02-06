package scalas.PizzaTask

class Pizza {

  case class Point(row : Int, col : Int)

  var sliceCount = 1

  /**
    * Matrix with ingredients
    */
  val pizza = Array(
    Array("T", "T", "T"),
    Array("T", "T", "M")
  )

  /**
    * Matrix with rectangles of slice
    */
  val pizzaMap = Array.ofDim[Int](2, 3)

  def printPizza = printMatrix(pizza)
  def printPizzaMap = printMatrix(pizzaMap)

  def printMatrix[T](mat : Array[Array[T]]) = {
    mat.foreach(row =>
      println(row.mkString(" "))
    )
  }

  findOtherIngredient(0, 0)

  def findOtherIngredient(row : Int, col : Int) : (Int, Int) = {
    val char = pizza(row)(col)
    var notFound = true
    var res = (row, col)
    for {r <- row until pizza.length
         c <- col until pizza(0).length
         if notFound } {
      if (pizza(r)(c) != char) {
        notFound = !notFound
        res = (r, c)
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
}

