package scala.lossfunctions

abstract class LossFunction(coeffs: Coefficients) {

  def calculateLossGeneral(filePathExtracted: String, filePathInterpolated: String): Double = {

    var datasetLength = 0
    val fileExtractedReaderTemp = io.Source.fromFile(filePathExtracted)
    for (i <- fileExtractedReaderTemp.getLines()) {
      datasetLength += 1
    }

    val fileExtractedReader = io.Source.fromFile(filePathExtracted)
    val fileInterpolatedReader = io.Source.fromFile(filePathInterpolated)
    val fileExtractedIterator = fileExtractedReader.getLines()
    val fileInterpolatedIterator = fileInterpolatedReader.getLines()

    val result = calculateLossSpecific(fileExtractedIterator, fileInterpolatedIterator, coeffs.getCoeffIterator(datasetLength))


    fileExtractedReader.close()
    fileInterpolatedReader.close()
    result
  }

  def calculateLossSpecific(fileExtractedIterator: Iterator[String], fileInterpolatedIterator: Iterator[String], coeffs: Iterator[Double]): Double

  def getLossFunctionType: String

  def getCoefficientsType: String = coeffs.getCoefficientsType

  override def equals(obj: Any): Boolean = {
    obj match {
      case otherLossF: LossFunction => this.getLossFunctionType == otherLossF.getLossFunctionType && this.getCoefficientsType == otherLossF.getCoefficientsType
      case obj => super.equals(obj)
    }
  }
}
