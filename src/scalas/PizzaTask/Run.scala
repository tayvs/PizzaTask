package scalas.PizzaTask

import scalas.PizzaTask.util.{Numbers, Point}

/**
  * Created by tayvs on 07.02.2017.
  */
object Run extends App {

  //  val inputPizza = Array(
  //    Array("T", "T", "T", "T", "T"),
  //    Array("T", "T", "M", "M", "T"),
  //    Array("T", "T", "T", "T", "T")
  //  )
  val inputData = new InputFileReader("small.in")

  val pizza = new Pizza(inputData.pizzaMatrix, inputData.minIngredientCount, inputData.maxSlice)

  println("Pizza")
  pizza.printPizza()

  //todo: search for ingredient, that can be only at one slice and slicing they first
  //zero stage
  println("Zero stage begin")
  for {
    r <- pizza.pizza.indices
    c <- pizza.pizza(0).indices
  } {
    val vars = Numbers.searchSingleton(pizza, Point(r, c), inputData.minIngredientCount, inputData.maxSlice)
    if (vars.size == 1) {
      println("r " + r, "c " + c)
      println("vars" + vars)
      pizza.fillRec(Point(r, c), vars.head)
    }
  }
  pizza.printPizzaMap()
  println("Zero stage end")

  var alongIngredient = pizza.findAloneIngredient
  while (alongIngredient != Point(-1, -1)) {
    //    println("alongIngredient at " + alongIngredient)
    val otherIng = pizza.findOtherIngredient(alongIngredient)
    //    println("otherIngredient at " + otherIng)

    if (alongIngredient != otherIng)
      pizza.fillRec(alongIngredient, otherIng)

    alongIngredient = pizza.findAloneIngredient
  }

  println("PizzaMap")
  pizza.printPizzaMap()
}
