package scala.lossfunctions

class AutocorrelationBasedCoefficients(name: String, coeffFileNumber: Int) extends Coefficients {
  val savedCoeffsPath = "data/autocorCoeffs/"
  val savedCoeffsEnding = "_" + coeffFileNumber.toString + "_autocorCoeff.csv"

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new AutocorrelationBasedCoefficientsIterator()
  }

  override def getCoefficientsType: String = "autocor_" + name + coeffFileNumber.toString

  private class AutocorrelationBasedCoefficientsIterator() extends Iterator[Double] {

    val innerIterator = io.Source.fromFile(savedCoeffsPath + name + savedCoeffsEnding).getLines()

    override def hasNext: Boolean = innerIterator.hasNext

    override def next(): Double = innerIterator.next().toDouble
  }
}
