package scala.samplingToCoeffMatch

import scala.collection.mutable.ListBuffer

object CalcProbabilityDistribution {

  def calcDist(alpha: Double, c: Double, length: Int): List[Double] = {
    val result = new ListBuffer[Double]
    var keepProbability = 1.0
    for (i <- (1 to length).reverse) {
      val singleProbability = calcSingleProb(alpha, c, i)
      val acceptanceProbability = singleProbability * keepProbability
//      println("keep prob: " + keepProbability)
//      println("single prob: " + calcSingleProb(alpha, c, i))
//      println("acceptance prob: " + acceptanceProbability)
//      println()
      keepProbability *= (1 - singleProbability)
      result.addOne(acceptanceProbability)
    }
    result.toList.reverse
  }

  def calcSingleProb(alpha: Double, c: Double, i: Int): Double = {
    math.min(1, c * math.pow(i, -alpha))
  }

}
