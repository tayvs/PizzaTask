import scalas.PizzaTask.Pizza

val inputPizza = Array(
  Array("T", "T", "T", "T", "T"),
  Array("T", "T", "M", "M", "T"),
  Array("T", "T", "T", "T", "T")
)

val pizza = new Pizza(inputPizza)

pizza.getRangeOfMultipliers(10).foreach{
  println(_)
}