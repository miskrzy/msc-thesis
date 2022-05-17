package scala.lossfunctions

class RollingStdDevBasedCoefficients(name: String) extends Coefficients {
  val savedCoeffsPath = "data/rollStdDevCoeffs/"
  val savedCoeffsEnding = "_rollStdDevCoeff.csv"

  override def getCoeffIterator(datasetLength: Int): Iterator[Double] = {
    new RollingStdDevBasedCoefficientsIterator()
  }

  override def getCoefficientsType: String = "rollingStdDev_" + name

  private class RollingStdDevBasedCoefficientsIterator() extends Iterator[Double] {

    val innerIterator = io.Source.fromFile(savedCoeffsPath + name + "_0.5" + savedCoeffsEnding).getLines()

    override def hasNext: Boolean = innerIterator.hasNext

    override def next(): Double = innerIterator.next().toDouble
  }
}
