package scala.statistics

object StatisticsMain {
  def main(args: Array[String]): Unit = {
    //calcStandardDeviationsEtc()
    //createCoeffsFromStdDev()
    //calculateAutoCorrelation()
    createCoeffsFromAutocorrelation()
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

  def createCoeffsFromAutocorrelation(): Unit ={
    val params = Map(
      "AMZN" -> List(3500),
      "MSFT" -> List(2500, 5000),
      "bitcoin" -> List(7500,13000),
      "GE" -> List(4500, 9000),
      "minneapolis" -> List(2000, 4000),
      "denpasar" -> List(6000)
    )
    CreateCoeffsFromAutocorrelation.writeCoefficientsFromAutocorOnDifferentCuts(params)
  }
}
