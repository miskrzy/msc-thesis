package scala.statistics

import scala.lossfunctions.Coefficients

case class CoeffSumParameters(numberOfReservoirs: Int,
                              alpha: Double,
                              c: Double,
                              coefficients: List[Double],
                              coeffType: String) {
  override def toString: String = {
    "s" + this.numberOfReservoirs +
      "_a" + this.alpha +
      "_c" + this.c +
      "_coeffType" + this.coeffType
  }
}
