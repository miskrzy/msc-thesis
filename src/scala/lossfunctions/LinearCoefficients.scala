package scala.lossfunctions

//f(x) = ax+b
class LinearCoefficients(firstElemFraction: Double = 1.0) extends Coefficients {

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new LinearCoefficientsIterator(datasetLength)
  }

  override def getCoefficientsType: String = "coeff_ax+b_initfrac_" + firstElemFraction

  private class LinearCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val b = firstElemFraction / datasetLength
    val a = ((2.0 - firstElemFraction) / datasetLength - b) / (datasetLength - 1)
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a * i + b
      i += 1
      res
    }
  }
}
