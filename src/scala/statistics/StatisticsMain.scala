package scala.statistics

import scala.collection.mutable.ListBuffer
import scala.lossfunctions.{AutocorrelationBasedCoefficients, ExponentialCoefficients, LinearCoefficients, MinusAxPlusBOverXCoefficients, RollingStdDevBasedCoefficients, X2NoTranslationCoefficients, X3Coefficients, X5NoTranslationCoefficients}

object StatisticsMain {
  def main(args: Array[String]): Unit = {
    //calcStandardDeviationsEtc()
    //createCoeffsFromStdDev()
    //calculateAutoCorrelation()
    //createCoeffsFromAutocorrelation()
    calcStatTable()
    //calcCoeffSums(10, 10000)
  }

  def calcStatTable(): Unit ={
    val names = List(
      "AMZN","MSFT","GE","bitcoin","denpasar","minneapolis"
    )
    StatisticsCalculations.calcTableStats(names)
  }

  //coeff length relates to length of coefficients that were not constructed from data
  def calcCoeffSums(numberOfTrials: Int, mockedDataLength: Int): Unit = {
    val alphaC = Map(0.5 -> List(0.1, 0.2, 0.5, 0.05, 0.15, 0.25, 0.33, 0.66, 0.75),
      0.65 -> List(0.4, 0.5, 0.6, 0.7, 0.8),
      0.75 -> List(0.4, 0.5, 0.6, 0.7, 0.8),
      0.85 -> List(0.6, 0.7, 0.8),
      0.9 -> List(1,1.5,2,3,5,10),
      0.95 -> List(1,1.5,2,3,5,10),
      1.0 -> List(1.0, 2.0, 3.0, 5.0, 10.0),
      2.0 -> List(100.0, 200.0, 400.0, 1000.0, 2000.0, 3000.0, 4000.0),
      1.25 -> List(2.0, 2.5, 3.0, 5.0, 7.0, 10.0, 15.0, 20.0, 30.0, 40.0),
      1.5 -> List(5.0, 7.0, 10.0, 20.0, 40.0, 70.0, 100.0, 150.0, 200.0, 400.0),
      1.75 -> List(20.0, 40.0, 100.0, 250.0, 500.0, 1000.0, 2000.0)
    )

    val numberOfReservoirs = List(100, 1000)

    val coeffs = List(
      (new LinearCoefficients(0.0).getCoeffIterator(mockedDataLength).toList,new LinearCoefficients(0.0).getCoefficientsType),
      (new LinearCoefficients(0.5).getCoeffIterator(mockedDataLength).toList,new LinearCoefficients(0.5).getCoefficientsType),
      (new LinearCoefficients(1.0).getCoeffIterator(mockedDataLength).toList,new LinearCoefficients(1.0).getCoefficientsType),
      (new X3Coefficients(0.0).getCoeffIterator(mockedDataLength).toList,new X3Coefficients(0.0).getCoefficientsType),
      (new X3Coefficients(0.5).getCoeffIterator(mockedDataLength).toList,new X3Coefficients(0.5).getCoefficientsType),
      (new X2NoTranslationCoefficients().getCoeffIterator(mockedDataLength).toList,new X2NoTranslationCoefficients().getCoefficientsType),
      (new X5NoTranslationCoefficients().getCoeffIterator(mockedDataLength).toList,new X5NoTranslationCoefficients().getCoefficientsType),
      (new MinusAxPlusBOverXCoefficients().getCoeffIterator(mockedDataLength).toList,new MinusAxPlusBOverXCoefficients().getCoefficientsType),
      (new ExponentialCoefficients(1).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(1).getCoefficientsType),
      (new ExponentialCoefficients(2).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(2).getCoefficientsType),
      (new ExponentialCoefficients(3).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(3).getCoefficientsType),
      (new ExponentialCoefficients(4).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(4).getCoefficientsType),
      (new ExponentialCoefficients(7).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(7).getCoefficientsType),
      (new ExponentialCoefficients(10).getCoeffIterator(mockedDataLength).toList,new ExponentialCoefficients(10).getCoefficientsType),
      (new AutocorrelationBasedCoefficients("AMZN", 3500).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("AMZN", 3500).getCoefficientsType),
      (new RollingStdDevBasedCoefficients("AMZN").getCoeffIterator(mockedDataLength).toList,new RollingStdDevBasedCoefficients("AMZN").getCoefficientsType),
      (new AutocorrelationBasedCoefficients("bitcoin", 7500).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("bitcoin", 7500).getCoefficientsType),
      //(new AutocorrelationBasedCoefficients("bitcoin", 13000).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("bitcoin", 13000).getCoefficientsType),
      (new RollingStdDevBasedCoefficients("bitcoin").getCoeffIterator(mockedDataLength).toList,new RollingStdDevBasedCoefficients("bitcoin").getCoefficientsType),
      (new AutocorrelationBasedCoefficients("GE", 4500).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("GE", 4500).getCoefficientsType),
      (new AutocorrelationBasedCoefficients("GE", 9000).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("GE", 9000).getCoefficientsType),
      (new RollingStdDevBasedCoefficients("GE").getCoeffIterator(mockedDataLength).toList,new RollingStdDevBasedCoefficients("GE").getCoefficientsType),
      (new AutocorrelationBasedCoefficients("MSFT", 2500).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("MSFT", 2500).getCoefficientsType),
      (new AutocorrelationBasedCoefficients("MSFT", 5000).getCoeffIterator(mockedDataLength).toList,new AutocorrelationBasedCoefficients("MSFT", 5000).getCoefficientsType),
      (new RollingStdDevBasedCoefficients("MSFT").getCoeffIterator(mockedDataLength).toList,new RollingStdDevBasedCoefficients("MSFT").getCoefficientsType)
    )

    val parameters = new ListBuffer[CoeffSumParameters]()

    for ((alpha, cs) <- alphaC) {
      for (c <- cs) {
        for (samples <- numberOfReservoirs) {
          for ((coeff,coeffType) <- coeffs) {
            val paramSet = CoeffSumParameters(samples, alpha, c, coeff, coeffType)
            parameters.addOne(paramSet)
          }
        }
      }
    }

    val coeffsReservoirs = List(
      (new AutocorrelationBasedCoefficients("minneapolis", 2000).getCoeffIterator(mockedDataLength).toList, 1000, new AutocorrelationBasedCoefficients("minneapolis", 2000).getCoefficientsType),
      //(new AutocorrelationBasedCoefficients("minneapolis", 2000), 20000),
      (new AutocorrelationBasedCoefficients("minneapolis", 4000).getCoeffIterator(mockedDataLength).toList, 1000, new AutocorrelationBasedCoefficients("minneapolis", 4000).getCoefficientsType),
      //(new AutocorrelationBasedCoefficients("minneapolis", 4000), 20000),
      //(new AutocorrelationBasedCoefficients("denpasar", 6000).getCoeffIterator(mockedDataLength).toList, 1000, new AutocorrelationBasedCoefficients("denpasar", 6000).getCoefficientsType)
      //(new AutocorrelationBasedCoefficients("denpasar", 6000), 20000)
    )

    for ((alpha, cs) <- alphaC) {
      for (c <- cs) {
        for ((coeff, samples, coeffType) <- coeffsReservoirs) {
          val paramSet = CoeffSumParameters(samples, alpha, c, coeff,coeffType)
          parameters.addOne(paramSet)
        }
      }
    }

    CoeffSum.calcCoeffSumBulkAndSave(parameters.toList, numberOfTrials)
  }

  def createCoeffsFromStdDev(): Unit = {
    val datasetList = List(
      "AMZN",
      "bitcoin",
      "GE",
      "MSFT",
    )

    CreateCoeffsFromRollStdDev.createCoeffs(datasetList)

  }

  def calcStandardDeviationsEtc(): Unit = {
    val datasetList = List(
      "AMZN",
      "bitcoin",
      //"denpasar",
      "GE",
      "MSFT",
      //"minneapolis"
    )
    //StatisticsCalculations.calcRelStdDev(datasetList)
    //StatisticsCalculations.calcRelVar(datasetList)

    /*for(name <- datasetList){
      StatisticsCalculations.rollingRelStdDevSingle(name,1000, 1000.toString)
    }*/

    for (name <- datasetList) {
      StatisticsCalculations.rollingRelStdDevSingleRelWindowSizes(name, List(0.5, 0.25, 0.125, 0.0625))
    }
  }

  def calculateAutoCorrelation(): Unit = {
    val params = Map(
      "AMZN" -> (1 to 5977 by 10).toList,
      "MSFT" -> (1 to 8803 by 10).toList,
      "bitcoin" -> (1 to 30956 by 100).toList,
      "GE" -> (1 to 14868 by 10).toList,
      "minneapolis" -> (1 to 45251 by 24).toList,
      "denpasar" -> (1 to 264924 by 24).toList
    )
    StatisticsCalculations.writeAutoCorelationForDifferentSetsAndOffsets(params)
  }

  def createCoeffsFromAutocorrelation(): Unit = {
    val params = Map(
      "AMZN" -> List(3500),
      "MSFT" -> List(2500, 5000),
      "bitcoin" -> List(7500, 13000),
      "GE" -> List(4500, 9000),
      "minneapolis" -> List(2000, 4000),
      "denpasar" -> List(6000)
    )
    CreateCoeffsFromAutocorrelation.writeCoefficientsFromAutocorOnDifferentCuts(params)
  }
}
