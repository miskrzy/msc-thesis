package scala.lossfunctions

class X2NoTranslationCoefficients extends Coefficients {

  override def getCoefficientsType: String = "coeff_ax^2"

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new X2NoTranslationCoefficientsIterator(datasetLength)
  }

  private class X2NoTranslationCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val a = 3.0 / datasetLength / datasetLength / (datasetLength - 1)
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a * i * i
      i += 1
      res
    }
  }
}
