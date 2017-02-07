package scalas.PizzaTask

/**
  * Created by tayvs on 07.02.2017.
  */
object Run extends App {

  val inputPizza = Array(
    Array("T", "T", "T", "T", "T"),
    Array("T", "M", "M", "M", "T"),
    Array("T", "T", "T", "T", "T")
  )
  
  val pizza = new Pizza(inputPizza)
  
  println("Pizza")
  pizza.printPizza
  println("PizzaMap")
  pizza.printPizzaMap

  var alongIngredient = pizza.findAloneIngredient
  while (alongIngredient != Point(-1, -1)) {
    val otherIng = pizza.findOtherIngredient(alongIngredient)
    val isFindOtherIng = otherIng != alongIngredient
    if (pizza.isEmptyRec(alongIngredient, otherIng))
      pizza.fillRec(alongIngredient, otherIng)

    println("PizzaMap")
    pizza.printPizzaMap
    alongIngredient = pizza.findAloneIngredient
  }
}
