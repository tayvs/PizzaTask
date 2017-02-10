package scalas.PizzaTask

import scala.io.Source


class InputFileReader(private val name: String) {

  private val source = Source.fromFile(name, "UTF-8")
  private val inputData = source.getLines().toList

  val (rowCount, colCount, minIngredientCount, maxSlice) = getMetaData(inputData.head)
  val pizzaMatrix: Array[Array[String]] = inputData.slice(1, inputData.size).map(_.toCharArray.map(_.toString)).toArray

  private def getMetaData(str: String): (Int, Int, Int, Int) = {
    val ints = str.split(" ").map(_.toInt)
    (ints(0), ints(1), ints(2), ints(3))
  }

}
