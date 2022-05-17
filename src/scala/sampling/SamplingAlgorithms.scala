package scala.sampling

import java.io.{File, PrintWriter}
import scala.util.Random

object SamplingAlgorithms {

  val extractedDataPath = "data/extracted/"
  val sampledDataPath = "data/sampled/"
  val extracted_file_ending = "_ext.csv"
  val sampled_file_ending = "_sampled.csv"

  //samples data with c*i^(-alpha) algorithm and specified number of reservoirs
  def sampleAlpaC(name: String, alpha: Double, c: Double, numberOfReservoirs: Int): Unit = {
    val parametrizationPath = "_s" + numberOfReservoirs + "_a" + alpha + "_c" + c

    val writer = new PrintWriter(new File(sampledDataPath + name + parametrizationPath + sampled_file_ending))

    val reader = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)
    val fileIterator = reader.getLines()

    var i = 0

    var reservoirs = (0 until numberOfReservoirs).map(_ => (0, 0.0))

    for (line <- fileIterator) {
      reservoirs = reservoirs.map(res => {
        var out = res
        if (Random.nextDouble() < Math.min(1, c * Math.pow(i + 1, -alpha))) {
          out = (i, line.toDouble)
        }
        out
      })
      i += 1
    }
    reservoirs.sorted.foreach(x => writer.println(x._1 + ", " + x._2))

    reader.close()
    writer.close()
    println(name + " sampled with: s" + numberOfReservoirs + " a" + alpha + " c" + c)
  }
}
