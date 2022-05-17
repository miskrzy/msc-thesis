package scala.lossfunctions

class OverXCoefficients() extends Coefficients {
  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new OverXCoefficientsCoefficientsIterator(datasetLength)
  }

  private class OverXCoefficientsCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {
    val e = 2.71828


    val a = 1.0 / (math.log(datasetLength + 1 - 1) / math.log(e))
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a / (i + 1).toDouble
      i += 1
      res
    }
  }

  override def getCoefficientsType: String = "coeff_1/x"
}
