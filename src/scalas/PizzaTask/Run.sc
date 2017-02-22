import scalas.PizzaTask.util._


def isInnerRec(fBegin: Point, fEnd: Point, sBegin: Point, sEnd: Point): Boolean = {
  (fBegin.row <= sBegin.row && fBegin.col <= sBegin.col) &&
    (fEnd.row >= sEnd.row && fBegin.col >= sBegin.col)
}

val curr = Point(5, 3)
val maxSquare = Point(1, 3)
val maxRecPoints = Numbers.normalizeRecCoordinates(curr, maxSquare)
val normalizePoint = Numbers.normalizeRecCoordinates(Point(5, 6), curr)
(maxRecPoints._1, maxRecPoints._2, normalizePoint._1, normalizePoint._2)
Numbers.isInnerRec(maxRecPoints._1, maxRecPoints._2, normalizePoint._1, normalizePoint._2)