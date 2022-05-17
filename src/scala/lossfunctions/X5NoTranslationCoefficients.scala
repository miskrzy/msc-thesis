package scala.lossfunctions

class X5NoTranslationCoefficients extends Coefficients {

  override def getCoefficientsType: String = "coeff_ax^5"

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new X5NoTranslationCoefficientsIterator(datasetLength)
  }

  private class X5NoTranslationCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val a = 6.0 / datasetLength / datasetLength / datasetLength / (datasetLength - 1) / (datasetLength - 1) / (datasetLength - 1)
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a * i * i * i * i * i
      i += 1
      res
    }
  }

}
