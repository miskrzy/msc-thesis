package scala.samplingToCoeffMatch

import scala.collection.mutable.ListBuffer
import scala.lossfunctions.{AutocorrelationBasedCoefficients, ExponentialCoefficients, LinearCoefficients, MinusAxPlusBOverXCoefficients, RollingStdDevBasedCoefficients, X2NoTranslationCoefficients, X3Coefficients, X5NoTranslationCoefficients}

object MatchMain {
  def main(args: Array[String]): Unit = {

    val alphaC = Map(0.5 -> List(0.1, 0.2, 0.5, 0.05, 0.15, 0.25, 0.33, 0.66, 0.75),
      0.65 -> List(0.4, 0.5, 0.6, 0.7, 0.8),
      0.75 -> List(0.4, 0.5, 0.6, 0.7, 0.8),
      0.85 -> List(0.6, 0.7, 0.8),
      0.9 -> List(1, 1.5, 2, 3, 5, 10),
      0.95 -> List(1, 1.5, 2, 3, 5, 10),
      1.0 -> List(1.0, 2.0, 3.0, 5.0, 10.0),
      2.0 -> List(100.0, 200.0, 400.0, 1000.0, 2000.0, 3000.0, 4000.0),
      1.25 -> List(2.0, 2.5, 3.0, 5.0, 7.0, 10.0, 15.0, 20.0, 30.0, 40.0),
      1.5 -> List(5.0, 7.0, 10.0, 20.0, 40.0, 70.0, 100.0, 150.0, 200.0, 400.0),
      1.75 -> List(20.0, 40.0, 100.0, 250.0, 500.0, 1000.0, 2000.0)
    )

    val params = new ListBuffer[(Double, Double)]()
    for ((alpha, cs) <- alphaC) {
      for (c <- cs) {
        params.addOne((alpha, c))
      }
    }

    val coefficientsNeutral = List(
      new LinearCoefficients(0.0),
      new LinearCoefficients(0.5),
      new LinearCoefficients(1.0),
      new X3Coefficients(0.0),
      new X3Coefficients(0.5),
      new X2NoTranslationCoefficients(),
      new X5NoTranslationCoefficients(),
      new MinusAxPlusBOverXCoefficients(),
      new ExponentialCoefficients(1),
      new ExponentialCoefficients(2),
      new ExponentialCoefficients(3),
      new ExponentialCoefficients(4),
      new ExponentialCoefficients(7),
      new ExponentialCoefficients(10)
    )
    val namesAndSpecCoeffs = List(
      ("AMZN", List(
        new AutocorrelationBasedCoefficients("AMZN", 3500),
        new RollingStdDevBasedCoefficients("AMZN")
      )),
      ("bitcoin", List(
        new AutocorrelationBasedCoefficients("bitcoin", 7500),
        new AutocorrelationBasedCoefficients("bitcoin", 13000),
        new RollingStdDevBasedCoefficients("bitcoin")
      )),
      ("GE", List(
        new AutocorrelationBasedCoefficients("GE", 4500),
        new AutocorrelationBasedCoefficients("GE", 9000),
        new RollingStdDevBasedCoefficients("GE")
      )),
      ("MSFT", List(
        new AutocorrelationBasedCoefficients("MSFT", 2500),
        new AutocorrelationBasedCoefficients("MSFT", 5000),
        new RollingStdDevBasedCoefficients("MSFT")
      )),
      ("minneapolis", List(
        new AutocorrelationBasedCoefficients("minneapolis", 2000),
        new AutocorrelationBasedCoefficients("minneapolis", 4000)
      )),
      ("denpasar", List(
        new AutocorrelationBasedCoefficients("denpasar", 6000)
      ))
    )


    MatchLogic.bulkCalcAll(params.toList, coefficientsNeutral, namesAndSpecCoeffs)


  }

}
