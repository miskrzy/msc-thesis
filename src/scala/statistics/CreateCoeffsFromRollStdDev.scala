package scala.statistics

import java.io.{File, PrintWriter}

object CreateCoeffsFromRollStdDev {

  val extractedPath = "data/extracted/"
  val rollingStdDevPath = "data/statistics/"
  val savedCoeffsPath = "data/rollStdDevCoeffs/"

  val extractedEnding = "_ext.csv"
  val rollingStdDevEnding = "_rollStdDev.csv"
  val savedCoeffsEnding = "_rollStdDevCoeff.csv"

  def createCoeffs(names: List[String]): Unit = {
    for(name <- names){
      interpolatedRollingStdDev(name)
    }

  }

  def interpolatedRollingStdDev(name: String): Unit = {
    val extractedReader = scala.io.Source.fromFile(extractedPath + name + extractedEnding)
    var extLength = 0
    for (line <- extractedReader.getLines()) {
      extLength += 1
    }
    extractedReader.close()


    val frac = 0.5
    val reader = io.Source.fromFile(rollingStdDevPath + name + "_" + frac.toString + rollingStdDevEnding)
    var result = scala.collection.mutable.Buffer.empty[Double]
    val sourceLines = reader.getLines()
    var firstElem = 0.0
    var secondElem = sourceLines.next().toDouble
    while (sourceLines.hasNext) {
      firstElem = secondElem
      secondElem = sourceLines.next().toDouble
      result.addOne(firstElem)
      result.addOne((firstElem + secondElem) / 2.0)
    }
    result.addOne(secondElem)
    result = result.dropRight(1)
    if (extLength % 2 != 0) result = result.drop(1)
    var seqResult = result.toSeq

    var sum = 0.0
    for (i <- seqResult) {
      sum += i
    }
    seqResult = seqResult.map(i => i / sum)


    val writer = new PrintWriter(new File(savedCoeffsPath + name + "_0.5" + savedCoeffsEnding))

    for (point <- seqResult) {
      writer.println(point)
    }
    writer.close()
    reader.close()
  }


}
