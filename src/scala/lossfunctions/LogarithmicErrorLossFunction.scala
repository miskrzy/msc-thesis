package scala.lossfunctions

class LogarithmicErrorLossFunction(coeffs: Coefficients, power: Double = 1.0) extends LossFunction(coeffs) {
  override def calculateLossSpecific(fileExtractedIterator: Iterator[String], fileInterpolatedIterator: Iterator[String], coeffs: Iterator[Double]): Double = {
    var result = 0.0

    while (coeffs.hasNext) {
      var interpolatedValue = fileInterpolatedIterator.next().split(',').last.toDouble
      var extractedValue = fileExtractedIterator.next().toDouble
      val coeff = coeffs.next()

      //some adjustment to deal with log(0.0)
      if(extractedValue == 0.0)extractedValue = 0.01
      if(interpolatedValue == 0.0)interpolatedValue = 0.01

      result += coeff * Math.pow(Math.abs(Math.log(Math.abs(extractedValue)) - Math.log(Math.abs(interpolatedValue))),power)
    }
    result
  }

  override def getLossFunctionType: String = "log_err_pow_" + power.toString
}
