package scala.lossfunctions

class RelativeErrorLossFunction(coeffs: Coefficients, power: Double = 1.0) extends LossFunction(coeffs) {
  override def calculateLossSpecific(fileExtractedIterator: Iterator[String], fileInterpolatedIterator: Iterator[String], coeffs: Iterator[Double]): Double = {
    var result = 0.0

    while (coeffs.hasNext) {
      val interpolatedValue = fileInterpolatedIterator.next().split(',').last.toDouble
      var extractedValue = fileExtractedIterator.next().toDouble
      val coeff = coeffs.next()

      //some adjustment to deal with 0.0 in denominator
      if(extractedValue == 0.0)extractedValue = 0.01

      val tempRes = coeff * Math.pow(Math.abs((extractedValue - interpolatedValue) / extractedValue), power)

      //if(tempRes == Double.NaN || tempRes > 1000 || tempRes < 0) println("intval:" + interpolatedValue + " extval:" + extractedValue + " coeff:" + coeff)

      result += tempRes
    }
    result
  }

  override def getLossFunctionType: String = "rel_err_pow_" + power.toString
}
