package scala.lossfunctions

case class LossFunctionParameters(
                                 numberOfReservoirs: Int,
                                 alpha: Double,
                                 c: Double,
                                 lossFunction: LossFunction
                                 ){
  override def toString: String = {
    "s" + this.numberOfReservoirs +
      "_a" + this.alpha +
      "_c" + this.c +
      "_" + this.lossFunction.getLossFunctionType +
      "_" + this.lossFunction.getCoefficientsType
  }
}
