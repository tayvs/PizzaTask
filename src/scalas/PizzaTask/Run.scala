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

  var alongIngredient = pizza.findAloneIngredient
  while (alongIngredient != pizza.Point(-1, -1)) {
    val otherIng = pizza.findOtherIngredient(alongIngredient.row, alongIngredient.col)
    if (pizza.isEmptyRec(alongIngredient, pizza.Point(otherIng._1, otherIng._2)))
      pizza.fillRec(alongIngredient, pizza.Point(otherIng._1, otherIng._2))

    println("PizzaMap")
    pizza.printPizzaMap
    alongIngredient = pizza.findAloneIngredient
  }
}
