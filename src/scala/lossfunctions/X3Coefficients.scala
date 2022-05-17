package scala.lossfunctions

//f(x) = a(x-b)^3+c
class X3Coefficients(firstElemFraction: Double = 1.0) extends Coefficients {

  override def getCoefficientsType: String = "coeff_a(x-b)^3+c_initfrac_" + firstElemFraction

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new X3CoefficientsIterator(datasetLength)
  }

  private class X3CoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val b = (datasetLength - 1.0) / 2.0
    val a = 8.0 * (1.0 - firstElemFraction) / datasetLength / (datasetLength - 1) / (datasetLength - 1) / (datasetLength - 1)
    val c = 1.0 / datasetLength
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a * (i - b) * (i - b) * (i - b) + c
      i += 1
      res
    }
  }
}
