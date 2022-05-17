package scala.lossfunctions

trait Coefficients {

  def getCoeffIterator(datasetLength: Int): Iterator[Double]

  def getCoefficientsType: String
}
