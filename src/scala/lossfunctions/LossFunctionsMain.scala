package scala.lossfunctions

import java.io.{File, PrintWriter}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object LossFunctionsMain {
  def main(args: Array[String]): Unit = {

    calcForChosenParameters()

    calcAllIerpolatedFiles(false)

    //killSpecifiedParams()

  }

  def calcAllIerpolatedFiles(overwrite: Boolean = true):Unit={

    val coeffs = List(
      new LinearCoefficients(0.0),
      new LinearCoefficients(0.5),
      new LinearCoefficients(1.0),
      new X3Coefficients(0.0),
      new X3Coefficients(0.5),
      new X2NoTranslationCoefficients,
      new X5NoTranslationCoefficients,
      new MinusAxPlusBOverXCoefficients
    )

    val powers = List(1.0, 2.0)

    val lossFs = ListBuffer[LossFunction]()

    for (coeff <- coeffs) {
      for (power <- powers) {
        lossFs.addOne(new RelativeErrorLossFunction(coeff, power))
        lossFs.addOne(new LogarithmicErrorLossFunction(coeff, power))
      }
    }

    CalculateEveryLoss.calcAllCombinationsFromInterpolatedFiles(lossFs.toList,overwrite = overwrite)
  }

  def calcForChosenParameters(): Unit = {

    val names = List("AMZN", "bitcoin"/*, "denpasar"*/, "GE"/*, "minneapolis"*/, "MSFT")

    val samplesStock = List(100, 1000)
    val samplesWeather = List(1000, 20000)

    val alphaC = Map(0.5 -> List(0.1,0.2,0.5,0.05,0.15,0.25,0.33,0.66,0.75),
      0.65 -> List(0.4,0.5,0.6,0.7,0.8),
      0.75 -> List(0.4,0.5,0.6,0.7,0.8),
      0.85 -> List(0.6,0.7,0.8),
      1.0 -> List(1.0,2.0,3.0,5.0,10.0),
      2.0 -> List(100.0,200.0,400.0,1000.0,2000.0,3000.0,4000.0),

    )


    val nameCoeffStockAC = List(
      "AMZN" -> new AutocorrelationBasedCoefficients("AMZN",3500),
      "bitcoin" -> new AutocorrelationBasedCoefficients("bitcoin",7500),
      "bitcoin" -> new AutocorrelationBasedCoefficients("bitcoin",13000),
      "GE" -> new AutocorrelationBasedCoefficients("GE",4500),
      "GE" -> new AutocorrelationBasedCoefficients("GE",9000),
      "MSFT" -> new AutocorrelationBasedCoefficients("MSFT",2500),
      "MSFT" -> new AutocorrelationBasedCoefficients("MSFT",5000),
    )

    val nameCoeffStockSD = List(
      "AMZN" -> new RollingStdDevBasedCoefficients("AMZN"),
      "bitcoin" -> new RollingStdDevBasedCoefficients("bitcoin"),
      "GE" -> new RollingStdDevBasedCoefficients("GE"),
      "MSFT" -> new RollingStdDevBasedCoefficients("MSFT")
    )

    val nameCoeffWeather = List(
      "minneapolis" -> new AutocorrelationBasedCoefficients("minneapolis",2000),
      "minneapolis" -> new AutocorrelationBasedCoefficients("minneapolis",4000),
      "denpasar" -> new AutocorrelationBasedCoefficients("denpasar",6000)
    )

    val powers = List(1.0, 2.0)

    var lossFParams = ListBuffer[LossFunctionParameters]()
    val parameterMap = mutable.Map[String, List[LossFunctionParameters]]()

    for((name, coeff) <- nameCoeffStockAC){
      for((alpha, cs) <- alphaC){
        for(c <- cs){
          for(sample <- samplesStock){
            for(power <- powers){
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new RelativeErrorLossFunction(coeff, power)))
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new LogarithmicErrorLossFunction(coeff, power)))
            }
          }
        }
      }
      parameterMap.addOne(name, lossFParams.toList)
      lossFParams = ListBuffer[LossFunctionParameters]()
    }

    for((name, coeff) <- nameCoeffStockSD){
      for((alpha, cs) <- alphaC){
        for(c <- cs){
          for(sample <- samplesStock){
            for(power <- powers){
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new RelativeErrorLossFunction(coeff, power)))
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new LogarithmicErrorLossFunction(coeff, power)))
            }
          }
        }
      }
      parameterMap.addOne(name, lossFParams.toList)
      lossFParams = ListBuffer[LossFunctionParameters]()
    }

    for((name, coeff) <- nameCoeffWeather){
      for((alpha, cs) <- alphaC){
        for(c <- cs){
          for(sample <- samplesWeather){
            for(power <- powers){
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new RelativeErrorLossFunction(coeff, power)))
              lossFParams.addOne(LossFunctionParameters(sample, alpha, c, new LogarithmicErrorLossFunction(coeff, power)))
            }
          }
        }
      }
      parameterMap.addOne(name, lossFParams.toList)
      lossFParams = ListBuffer[LossFunctionParameters]()
    }

    CalculateEveryLoss.calcAllLossFuncs(parameterMap.toMap)

  }

  def killSpecifiedParams(): Unit ={
    val dataPath = "data/lossFunctionResults/"
    val dataEnding = "_loss.csv"
    val names = List(
      "AMZN",
      "bitcoin",
      "denpasar",
      "GE",
      "minneapolis",
      "MSFT"
    )
    val specifiedParams = Map(
      0.65->List(0.1,0.2,0.3),
      0.75->List(0.1,0.2,0.3),
      0.85->List(0.1,0.2,0.3,0.4,0.5)
    )

    val specifiedRegEx = ListBuffer[String]()
    for((alpha,cs) <- specifiedParams){
      for(c <-cs){
        specifiedRegEx.addOne("," + alpha.toString + "," + c.toString + ",")
      }
    }


    for(name <- names){
      val changedResult = new ListBuffer[String]()
      val reader = io.Source.fromFile(dataPath + name + dataEnding)
      for(line<- reader.getLines()){
        var copy = true
        for(spec <- specifiedRegEx){
          if(line.contains(spec)) copy = false
        }
        if(copy){
          changedResult.addOne(line)
        }
      }

      reader.close()
      val writer = new PrintWriter(new File(dataPath + name + dataEnding))
      for(line<-changedResult){
        writer.println(line)
      }
      writer.close()
    }

  }
}
