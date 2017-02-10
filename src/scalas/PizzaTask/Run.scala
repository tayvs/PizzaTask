package scalas.PizzaTask

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
  println("PizzaMap")
  pizza.printPizzaMap()

  //todo: search for ingredient, that can be only at one slice and slicing they first

  var alongIngredient = pizza.findAloneIngredient
  while (alongIngredient != Point(-1, -1)) {
    println("alongIngredient at " + alongIngredient)
    val otherIng = pizza.findOtherIngredient(alongIngredient)
    println("otherIngredient at " + otherIng)
    
    if (alongIngredient != otherIng)
      pizza.fillRec(alongIngredient, otherIng)

    println("PizzaMap")
    pizza.printPizzaMap()
    alongIngredient = pizza.findAloneIngredient
  }
}
