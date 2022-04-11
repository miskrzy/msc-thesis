package scala.interpolation

object InterpolationMain {
  def main(args: Array[String]): Unit = {
    InterpolationAlgorithms.linearInterpolationBulk()

/*
    val sampledFilesToInterpolate = List(
      "AMZN_s1000_a1.0_c1.0_sampled.csv"
    )
    InterpolationAlgorithms.linearInterpolationBulk(sampledFilesToInterpolate)
*/
  }

}
