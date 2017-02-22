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

  //zero stage
  println("Zero stage begin")
  for {
    r <- pizza.pizza.indices
    c <- pizza.pizza(0).indices
  } {
    val currPoint = Point(r, c)
    val vars = Numbers.searchSingleton(pizza, currPoint, inputData.minIngredientCount, inputData.maxSlice)
    if (vars.size == 1) {
      println("SingleTon Found")
      pizza.fillRec(Point(r, c), vars.head)
    } else {
      val squares = vars.map(p => pizza.square(currPoint, p))
      if (squares.size == vars.size) {
        val maxSquare = squares.max
        val maxSqPoint = vars.find(p => pizza.square(currPoint, p) == maxSquare).get
        val maxRecPoints = Numbers.normalizeRecCoordinates(currPoint, maxSqPoint)
        val allPointAreInners = vars.forall { p =>
          val normalizePoint = Numbers.normalizeRecCoordinates(p, currPoint)
          Numbers.isInnerRec(maxRecPoints._1, maxRecPoints._2, normalizePoint._1, normalizePoint._2)
        }
        if (allPointAreInners) {
          println("r " + r, "c " + c)
          println("vars" + vars)
          println(s"maxSquare is $maxSquare")
          println(s"maxSqPoint is $maxSqPoint")
          println(s"Square of maxSqPoint is ${pizza.square(currPoint, maxSqPoint)}")
          pizza.fillRec(currPoint, vars.find(p => pizza.square(currPoint, p) == squares.min).get)
        }
      }
    }
  }
  println("Zero stage end")
  pizza.printPizzaMap()

  //    First stage
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
