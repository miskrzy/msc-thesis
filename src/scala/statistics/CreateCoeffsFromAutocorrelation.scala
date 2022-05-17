package scala.statistics

import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer
import scala.util.Random

object CreateCoeffsFromAutocorrelation {

  val extractedPath = "data/extracted/"
  val autocorrelationPath = "data/autocorellations/"
  val savedCoeffsPath = "data/autocorCoeffs/"

  val extractedEnding = "_ext.csv"
  val autocorrelationEnding = "_autocor.csv"
  val savedCoeffsEnding = "_autocorCoeff.csv"

  val logging = false

  def writeCoefficientsFromAutocorOnDifferentCuts(params: Map[String, List[Int]]): Unit = {
    for ((name, cuts) <- params) {
      for (cut <- cuts) {
        if (logging) println()
        if (logging) println("name: ", name)
        if (logging) println("cut at: ", cut)

        val extPath = extractedPath + name + extractedEnding
        val autoPath = autocorrelationPath + name + autocorrelationEnding
        val coeffPath = savedCoeffsPath + name + "_" + cut.toString + savedCoeffsEnding

        val extLen = getLengthOfFile(extPath)
        if (logging) println("ext length: ", extLen)
        val autoLen = getLengthOfFile(autoPath)

        val listOfAutocorPointsBuffer = new ListBuffer[(Int, Double)]
        val autoReader = io.Source.fromFile(autoPath)
        for (line <- autoReader.getLines()) {
          val partsOfLine = line.split(",")
          val index = partsOfLine(0).toInt
          val value = partsOfLine(1).toDouble
          listOfAutocorPointsBuffer.addOne((index, value))
        }
        autoReader.close()
        val listOfAutocorPoints = listOfAutocorPointsBuffer.toList

        var tempIndex = 0
        var tempNumber = 0
        var foundResultFlag = false
        val listOfAutocorPointsBufferCut = new ListBuffer[Double]
        for (pair <- listOfAutocorPoints) {
          if (pair._1 < cut) {
            listOfAutocorPointsBufferCut.addOne(pair._2)
          }
          if (pair._1 >= cut && !foundResultFlag) {
            foundResultFlag = true
            tempIndex = pair._1
            listOfAutocorPointsBufferCut.addOne(pair._2)
          }
        }
        val firstIndexAfterCut = tempIndex
        if (logging) println("first index of autocor after cut: ", firstIndexAfterCut)
        val numberOfPointsFromAutocor = listOfAutocorPointsBufferCut.length
        if (logging) println("number of points from autocor: ", numberOfPointsFromAutocor)
        val pointsToPutInbetween = math.ceil(extLen.toDouble / (numberOfPointsFromAutocor - 1).toDouble).toInt - 1
        if (logging) println("points to put inbetween: ", pointsToPutInbetween)


        val pointsToWriteBeforeRemoving = new ListBuffer[Double]()
        val autocorPointsiter = listOfAutocorPointsBufferCut.iterator
        var firstPointInInterval = 0.0
        var lastPointInInterval = autocorPointsiter.next()
        for (i <- autocorPointsiter) {
          firstPointInInterval = lastPointInInterval
          lastPointInInterval = i

          val pointsInbetween = interpolate(firstPointInInterval, lastPointInInterval, pointsToPutInbetween)
          pointsToWriteBeforeRemoving.addOne(firstPointInInterval)
          pointsInbetween.foreach(pointsToWriteBeforeRemoving.addOne)
        }
        pointsToWriteBeforeRemoving.addOne(lastPointInInterval)
        if (logging) println("points before removing: ", pointsToWriteBeforeRemoving.length)

        val numberOfPointsToRemove = pointsToWriteBeforeRemoving.length - extLen
        if (logging) println("points to remove: ", numberOfPointsToRemove)
        val pointsToWriteAfterRemoving = new ListBuffer[Double]()
        pointsToWriteBeforeRemoving.foreach(x => pointsToWriteAfterRemoving.addOne(x))

        val random = Random
        for (i <- 0 until numberOfPointsToRemove) {
          pointsToWriteAfterRemoving.remove(random.nextInt(pointsToWriteAfterRemoving.length))
        }
        if (logging) println("points after removing: ", pointsToWriteAfterRemoving.length)

        val resultToWriteUnscaled = pointsToWriteAfterRemoving.toList.reverse.map(math.abs)
        var sum = 0.0
        resultToWriteUnscaled.foreach(x => sum += x)
        val resultToWriteScaled = resultToWriteUnscaled.map(x => x/sum)
        val writer = new PrintWriter(new File(coeffPath))
        resultToWriteScaled.foreach(x => writer.println(x))
        writer.close()

      }
    }
  }

  def interpolate(firstBefore: Double, lastAfter: Double, points: Int): List[Double] = {
    val result = new ListBuffer[Double]()
    val a = (lastAfter - firstBefore) / (points + 1).toDouble
    for (i <- 1 to points) {
      result.addOne(firstBefore + i * a)
    }
    result.toList
  }

  def getLengthOfFile(path: String): Int = {
    val reader = io.Source.fromFile(path)
    var len = 0
    reader.getLines().foreach(_ => len += 1)
    reader.close()
    len
  }

}
