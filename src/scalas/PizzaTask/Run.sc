import scalas.PizzaTask.{Pizza, Point}

val inputPizza = Array(
  Array("T", "T", "T", "T", "T"),
  Array("T", "T", "M", "M", "T"),
  Array("T", "T", "T", "T", "T")
)

val pizza = new Pizza(inputPizza, 1, 6)

pizza.getRangeOfMultipliers(10).foreach {
  println(_)
}


val position = Point(0, 0)
val (dRow, dCol) = (1, 2)
val top1: Point = Point(position.row - (dRow - 1), position.col - (dCol - 1))
val top2: Point = Point(position.row - (dCol - 1), position.col - (dRow - 1))

val inputList = List((1, 2))
val withRevers =
  inputList.map(c => (c._2 - 1, c._1 - 1)) ++
    inputList.map(c => (c._1 - 1, c._2 - 1))
val vars: List[List[(Int, Int)]] = for {
  sign <- List(1, -1)
  c <- withRevers
} yield {
  List(
    (sign * c._1, sign * c._2),
    (sign * c._1, -1 * sign * c._2)
  )
}

val varients = vars.flatten.toSet

val positions = varients.map(dx =>
  Point(position.row + dx._1, position.col + dx._2)
)

positions.foreach(println)