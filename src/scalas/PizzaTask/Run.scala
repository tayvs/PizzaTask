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
  val fileReader = new FileReader("small.in")

  val minCount = fileReader.minCount
  val maxSlice = fileReader.maxSlice

  val pizza = new Pizza(fileReader.pizzaMatrix)

  
  println("Pizza")
  pizza.printPizza()
  println("PizzaMap")
  pizza.printPizzaMap()

  //todo: search for ingredient, that can be only at one slice and slicing they first

  var alongIngredient = pizza.findAloneIngredient
  while (alongIngredient != Point(-1, -1)) {
    println("alongIngredient at " + alongIngredient)
    val otherIng = pizza.findOtherIngredient(alongIngredient, minCount, maxSlice)
    println("otherIngredient at " + otherIng)
    
    if (pizza.isEmptyRec(alongIngredient, otherIng))
      pizza.fillRec(alongIngredient, otherIng)

    println("PizzaMap")
    pizza.printPizzaMap()
    alongIngredient = pizza.findAloneIngredient
  }
}
