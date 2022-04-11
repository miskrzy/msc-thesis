package scala.interpolation

import java.io.{File, FileOutputStream, PrintWriter}

object InterpolationAlgorithms {

  val extractedDataPath = "data/extracted/"
  val sampledDataPath = "data/sampled/"
  val interpolatedDataPath = "data/sampledAndInterpolated/"

  val extracted_file_ending = "_ext.csv"
  val sampled_file_ending = "_sampled.csv"
  val interpolated_file_ending = "_int.csv"

  def linearInterpolationBulk(fullSampledFilesNames: List[String] = List.empty): Unit = {
    val filesToInterpolate = getAllFilesFilteredOrSelected(fullSampledFilesNames)

    for (file <- filesToInterpolate) {
      linearInterpolationSingleFile(file)
      println("interpolation done for: " + file)
    }

  }

  def linearInterpolationSingleFile(fileName: String): Unit = {

    //get first, last and length
    val readerTemp = io.Source.fromFile(extractedDataPath + fileName.split("_").head + extracted_file_ending)
    var firstFlag = true
    var first = 0.0
    var last = 0.0
    var length = 0
    for (line <- readerTemp.getLines()) {
      if (firstFlag) {
        first = line.toDouble
        firstFlag = false
      }
      last = line.toDouble
      length += 1
    }

    //calculating the interpolation
    val reader = io.Source.fromFile(sampledDataPath + fileName)
    val lines = reader.getLines()
    val writerPath = interpolatedDataPath + fileName.replaceFirst("_sampled", "_int")
    val writerTemp = new PrintWriter(new File(writerPath))
    writerTemp.print("")
    writerTemp.close()
    val writer = new PrintWriter(new FileOutputStream(new File(writerPath), true))

    val firstLineFromSampled = lines.next().split(",")
    val firstElement = firstLineFromSampled.last.toDouble
    val firstIndex = firstLineFromSampled.head.toInt

    createLinSec(first, 0, firstElement, firstIndex, writer)

    var travellingIndex = firstIndex
    var travellingElement = firstElement
    for (line <- lines) {
      val nextLineFromSampled = line.split(",")
      val nextElement = nextLineFromSampled.last.toDouble
      val nextIndex = nextLineFromSampled.head.toInt

      createLinSec(travellingElement, travellingIndex, nextElement, nextIndex, writer)

      travellingIndex = nextIndex
      travellingElement = nextElement
    }
    createLinSec(travellingElement, travellingIndex, last, length-1, writer)
    writer.println((length-1) + "," + last)

    reader.close()
    writer.close()
  }

  def createLinSec(eFirst: Double, iFirst: Int, eLast: Double, iLast: Int, writer: PrintWriter): Unit = {

    val coeff = (eLast - eFirst) / (iLast - iFirst)

    (0 until iLast - iFirst).foreach(i => writer.println(iFirst + i + "," + (eFirst + coeff * i)))
  }

  def getAllFilesFilteredOrSelected(fullSampledFilesNames: List[String]): List[String] = {
    var filesToInterpolate = List.empty[String]
    if (fullSampledFilesNames.isEmpty) {
      val sampledPath = new File(sampledDataPath)
      val filesInSampled = sampledPath.listFiles().map(_.getPath.split("\\\\").last).toList

      val sampledAndInterpolatedPath = new File(interpolatedDataPath)
      val filesInSampledAndInterpolated = sampledAndInterpolatedPath.listFiles().map(_.getPath.split("\\\\").last).toList
      val filesInSampledAndInterpolatedToFilter = filesInSampledAndInterpolated.map(x => x.replaceFirst("_int", "_sampled"))

      filesToInterpolate = filesInSampled.filterNot(x => filesInSampledAndInterpolatedToFilter.contains(x) || x.endsWith(".md"))
    } else {
      filesToInterpolate = fullSampledFilesNames
    }
    filesToInterpolate
  }
}
