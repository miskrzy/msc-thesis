package scala.lossfunctions

class MinusAxPlusBOverXCoefficients extends Coefficients {
  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = new MinusAxPlusBOverXCoefficientsIterator(datasetLength)

  override def getCoefficientsType: String = "minusAXplusBoverX"

  private class MinusAxPlusBOverXCoefficientsIterator(datasetLength: Int) extends Iterator[Double] {

    val a = 1.0 / math.log((datasetLength.toDouble + datasetLength.toDouble / 10.0)/(datasetLength.toDouble / 10.0))
    var i = 0

    override def hasNext: Boolean = i < datasetLength

    override def next(): Double = {
      val res = a / (i + datasetLength.toDouble / 10.0)
      i += 1
      res
    }
  }
}
