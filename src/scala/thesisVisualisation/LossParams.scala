package scala.thesisVisualisation

case class LossParams(
                       name: String,
                       samples: Int,
                       alpha: Double,
                       c: Double,
                       lossType: String,
                       coeffType: String,
                       result: Double,
                       saveName: String
                     ) {
  override def toString: String =
    name + "\t" +
      samples + "\t" +
      alpha + "\t" +
      c + "\t" +
      lossType + "\t" +
      coeffType + "\t" +
      result
}
