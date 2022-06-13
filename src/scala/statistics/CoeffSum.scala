package scala.statistics

import java.io.{File, PrintWriter}
import java.util
import scala.collection.mutable.ListBuffer
import scala.util.Random

object CoeffSum {
  val sampledCoeffSumsPath = "data/sampledCoefficientsSums/"
  val sampledCoeffSumsEnding = "_SampledCoeffSums.csv"

  def calcCoeffSumBulkAndSave(coeffsWithSampledData: List[CoeffSumParameters], numberOfTrials: Int): Unit = {

    val paramsGroupedByCoeffType = coeffsWithSampledData.groupBy(params => params.coeffType)

    for((coeffsType,params) <- paramsGroupedByCoeffType){
      val fullPath = sampledCoeffSumsPath + coeffsType.replace(".",",").replace("*","times") + sampledCoeffSumsEnding
      val writer = new PrintWriter(new File(fullPath))
      writer.println(
        "samples" + "," +
          "alpha" + "," +
          "c" + "," +
          "coeffsSum" + "," +
          "noRepetitionsCoeffsSum" + "," +
          "lastNSum"
      )
      for(par <- params){
        println("beeing calculated: " +
          "samples_" + par.numberOfReservoirs +
          "alpha_" + par.alpha +
          "c_" + par.c +
          "coeffType_" + par.coeffType)

        val coeffSumAkaResult = calcCoeffSumAveraged(par, numberOfTrials)
        val coeffSumAkaResultNoRepetitions = calcCoeffSumAveragedNoRepetitions(par, numberOfTrials)

        writer.println(
          par.numberOfReservoirs + "," +
            par.alpha + "," +
            par.c + "," +
            coeffSumAkaResult + "," +
            coeffSumAkaResultNoRepetitions + "," +
            sumOfLastNCoeffs(par.coefficients,par.numberOfReservoirs)
        )
      }
      writer.close()
    }
  }

  def sumOfLastNCoeffs(coeffs:List[Double],n:Int): Double ={
    coeffs.takeRight(n).sum
  }

  def calcCoeffSumAveraged(paramSet: CoeffSumParameters, numberOfTrials: Int): Double = {
    var result = 0.0
    for (i <- 1 to numberOfTrials) {
      var dataLength = paramSet.coefficients.length
      val coeffs = paramSet.coefficients
      val indexes = sampleAlpaCIndecesOnly(paramSet.alpha, paramSet.c, paramSet.numberOfReservoirs, dataLength)
      result += calcCoeffSum(coeffs, indexes)
    }
    result / numberOfTrials
  }

  def calcCoeffSumAveragedNoRepetitions(paramSet: CoeffSumParameters, numberOfTrials: Int): Double = {
    var result = 0.0
    for (i <- 1 to numberOfTrials) {
      var dataLength = paramSet.coefficients.length
      val coeffs = paramSet.coefficients
      val indexes = sampleAlpaCIndecesOnly(paramSet.alpha, paramSet.c, paramSet.numberOfReservoirs, dataLength)
      result += calcCoeffSumNoRepetitions(coeffs, indexes)
    }
    result / numberOfTrials
  }

  def calcCoeffSum(coeffs: List[Double], indexes: List[Int]): Double = {
    var result = 0.0
    for (i <- indexes) {
      result += coeffs(i)
    }
    result
  }

  def calcCoeffSumNoRepetitions(coeffs: List[Double], indexes: List[Int]): Double = {
    val repetions = ListBuffer[Int]()
    var result = 0.0
    for (i <- indexes) {
      if(!repetions.contains(i)){
        result += coeffs(i)
        repetions.addOne(i)
      }
    }
    result
  }

  def sampleAlpaCIndecesOnly(alpha: Double, c: Double, numberOfReservoirs: Int, dataLength: Int): List[Int] = {
    val result = collection.mutable.ListBuffer[Int]()

    var i = 0

    var reservoirs = (0 until numberOfReservoirs).map(_ => (0))
    for (_ <- 0 until dataLength) {
      Random.nextInt(numberOfReservoirs)
      reservoirs = reservoirs.map(res => {
        var out = res
        if (Random.nextDouble() < Math.min(1, c * Math.pow(i + 1, -alpha))) {
          out = i
        }
        out
      })
      i += 1
    }

    reservoirs.sorted.foreach(x => result.addOne(x))

    //println(name + " sampled with: s" + numberOfReservoirs + " a" + alpha + " c" + c)
    //println(dataLength/2.0,numberOfReservoirs,result.sum.toDouble / result.length.toDouble)
    result.toList
  }

}
