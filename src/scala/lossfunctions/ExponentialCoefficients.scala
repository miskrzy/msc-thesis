package scala.lossfunctions

//f(x) = c * d^(size - i)
//steepness should be between 1 and 10
class ExponentialCoefficients(steepness: Double) extends Coefficients {

  override def getCoefficientsType: String = "c*d^(n-i)_steep__" + steepness

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new ExponentialCoefficientsIterator(datasetLength)
  }

  private class ExponentialCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val d = 1.0 + 10.0 / 5.0 / datasetLength.toDouble * steepness
    val conditioning_factor = 1.0 / (1.0 + steepness / datasetLength.toDouble)
    val c = math.log(d) / (1/d - math.pow(d, -datasetLength)) * conditioning_factor

    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = c * math.pow(d,i - datasetLength)
      i += 1
      res
    }
  }
}
