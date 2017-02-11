import scalas.PizzaTask.Pizza
import scalas.PizzaTask.util.Numbers._
import scalas.PizzaTask.util.Point

val inputPizza = Array(
  Array("T", "T", "T", "T", "T"),
  Array("T", "T", "M", "M", "T"),
  Array("T", "T", "T", "T", "T")
)

val pizza = new Pizza(inputPizza, 1, 6)

val position = Point(0, 0)

val inputList = List((1, 2))
val withRevers =
  inputList.map(c => (c._2 - 1, c._1 - 1)) ++
    inputList.map(c => (c._1 - 1, c._2 - 1))

val variants = getDeltasForMatrix(withRevers)

val positions = variants.map(dx =>
  Point(position.row + dx._1, position.col + dx._2)
)

positions.foreach(println)


