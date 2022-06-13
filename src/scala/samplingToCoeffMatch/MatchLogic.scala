package scala.samplingToCoeffMatch

import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer
import scala.lossfunctions.Coefficients

object MatchLogic {

  val matchNormPath = "data/matchingFirstNorm/"
  val matchNormEnding = "_matching.csv"

  def bulkCalcAll(probs: List[(Double, Double)], coeffsNeutral: List[Coefficients], dataSetNameCoeffsSpecific: List[(String, List[Coefficients])]): Unit = {

    val result = new ListBuffer[MatchingRecord]()

    for ((alpha, c) <- probs) {
      for ((name, coeffsSpecific) <- dataSetNameCoeffsSpecific) {

        val length = getDataSetSize(name)

        for (coeffs <- coeffsNeutral) {
          val probDist = CalcProbabilityDistribution.calcDist(alpha, c, length)
          val norm = calcFirstNorm(coeffs.getCoeffIterator(length).toList, probDist)
          val record = MatchingRecord(name, alpha, c, coeffs.getCoefficientsType, norm)
          result.addOne(record)
        }

        for (coeffs <- coeffsSpecific) {
          val coeffList = coeffs.getCoeffIterator(0).toList
          val probDist = CalcProbabilityDistribution.calcDist(alpha, c, coeffList.length)
          val norm = calcFirstNorm(coeffList, probDist)
          val record = MatchingRecord(name, alpha, c, coeffs.getCoefficientsType, norm)
          result.addOne(record)
        }


      }


      println("all norms calculated for alpha=" + alpha + " and c=" + c)

    }
    val sortedResult = result.toList.sortWith((x1, x2) => x1.firstNormValue < x2.firstNormValue)

    val writer = new PrintWriter(new File(matchNormPath + "all" + matchNormEnding))

    writer.println(
      "dataset" + "," +
      "alpha" + "," +
      "c" + "," +
      "coeffName" + "," +
      "firstNormValue"
    )

    for (record <- sortedResult) {
      writer.println(
        record.dataset + "," +
          record.alpha + "," +
          record.c + "," +
          record.coeffName + "," +
          record.firstNormValue
      )
    }

    writer.close()

  }

  def calcFirstNorm(coeffs: List[Double], probDist: List[Double]): Double = {
    coeffs.zip(probDist).map(x => math.abs(x._1 - x._2)).sum
  }

  def getDataSetSize(name: String): Int = {
    val path = "data/extracted/"
    val ending = "_ext.csv"
    val reader = io.Source.fromFile(path + name + ending)
    val result = reader.getLines.length
    reader.close()
    result
  }

}
