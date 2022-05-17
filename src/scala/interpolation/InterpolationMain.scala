package scala.interpolation

object InterpolationMain {
  def main(args: Array[String]): Unit = {
    //version for all files that are not already interpolated
    //for interpolation re-do delete _int files first
    InterpolationAlgorithms.linearInterpolationBulk()


    //version for specific files
/*
    val sampledFilesToInterpolate = List(
      "AMZN_s1000_a1.0_c1.0_sampled.csv"
    )
    InterpolationAlgorithms.linearInterpolationBulk(sampledFilesToInterpolate)
*/
  }

}
