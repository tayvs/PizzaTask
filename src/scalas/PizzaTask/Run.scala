package scalas.PizzaTask

/**
  * Created by tayvs on 07.02.2017.
  */
object Run extends App {

  val pizza = new Pizza

  println("Pizza")
  pizza.printPizza
  println("PizzaMap")
  pizza.printPizzaMap

  println(pizza.findOtherIngredient(0, 0))
  val (r, c) = pizza.findOtherIngredient(0, 0)
  val beginPoint = pizza.Point(0, 0)
  val endPoint = pizza.Point(r, c)

  if (pizza.isEmptyRec(beginPoint, endPoint)) {
    pizza.fillRec(beginPoint, endPoint)
    println("PizzaMap")
    pizza.printPizzaMap
  }
}
