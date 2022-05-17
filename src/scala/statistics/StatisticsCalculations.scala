package scala.statistics

import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer

object StatisticsCalculations {

  val extractedDataPath = "data/extracted/"
  val extracted_file_ending = "_ext.csv"

  val autocorellationPath = "data/autocorellations/"
  val autocorellationEnding = "_autocor.csv"

  val statisticsPath = "data/statistics/"
  val stdDevEnding = "_stddev.csv"
  val varianceEnding = "_varia.csv"
  val rollingWindowStdDevEnding = "_rollStdDev.csv"

  def calcRelStdDev(namesList: List[String]): Unit = {
    val resultMap = scala.collection.mutable.Map.empty[String, Double]

    for (name <- namesList) {
      val extractedReader1 = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)

      var length = 0
      var sum = 0.0
      for (i <- extractedReader1.getLines()) {
        length += 1
        sum += i.toDouble
      }
      val average = sum / length

      val extractedReader2 = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)
      var stdDev = 0.0
      for (i <- extractedReader2.getLines()) {
        stdDev += math.pow(i.toDouble - average, 2)
      }
      stdDev /= length.toDouble

      val relativeStdDev = math.pow(stdDev, 0.5) / average

      resultMap(name) = relativeStdDev

      println("relativeStdDev for: " + name + " = " + relativeStdDev)
    }
    writeToFile(resultMap.toMap, statisticsPath + "allDatasets" + stdDevEnding, "relStdDev")
  }

  def calcRelVar(namesList: List[String]): Unit = {
    val resultMap = scala.collection.mutable.Map.empty[String, Double]

    for (name <- namesList) {
      val extractedReader1 = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)

      var length = 0
      var sum = 0.0
      for (i <- extractedReader1.getLines()) {
        length += 1
        sum += i.toDouble
      }
      val average = sum / length

      val extractedReader2 = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)
      var stdDev = 0.0
      for (i <- extractedReader2.getLines()) {
        stdDev += math.pow(i.toDouble - average, 2)
      }
      stdDev /= length.toDouble

      val variance = stdDev

      val relativeVariance = variance / average

      resultMap(name) = relativeVariance

      println("relativeVarince for: " + name + " = " + relativeVariance)
    }
    writeToFile(resultMap.toMap, statisticsPath + "allDatasets" + varianceEnding, "relVariance")
  }

  def writeToFile(resultMap: Map[String, Double], path: String, resColName: String): Unit = {

    val writer = new PrintWriter(new File(path))

    writer.println("name," + resColName)

    for ((k, v) <- resultMap) {
      writer.println(k + "," + v)
    }

    writer.close()

  }

  def rollingRelStdDevSingle(name: String, windowSize: Int, nameAddon: String): Unit = {
    val reader = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)
    val lines = reader.getLines()

    val window = new ListBuffer[Double].empty
    val result = new ListBuffer[Double].empty

    for (_ <- 1 to windowSize) {
      window.addOne(lines.next().toDouble)
    }

    def calcRelStdDev(dataset: List[Double]): Double = {
      var average = 0.0
      for (i <- dataset) {
        average += i
      }
      average /= dataset.length.toDouble

      var stdDev = 0.0
      for (i <- dataset) {
        stdDev += math.pow(i - average, 2)
      }
      stdDev = math.pow(stdDev / dataset.length, 0.5)
      stdDev / average
    }

    for(i <- lines){
      result.addOne(calcRelStdDev(window.toList))
      window.remove(0)
      window.addOne(i.toDouble)
    }
    result.addOne(calcRelStdDev(window.toList))
    reader.close()

    val writer = new PrintWriter(new File(statisticsPath + name + nameAddon + rollingWindowStdDevEnding))
    for(i <- result){
      writer.println(i)
    }
    writer.close()

    println("rolling std dev done for: " + name + " with: " + nameAddon)
  }

  def rollingRelStdDevSingleRelWindowSizes(name: String, windowSizeFractions:List[Double]): Unit = {
    val reader = io.Source.fromFile(extractedDataPath + name + extracted_file_ending)
    var length = 0
    for(line <- reader.getLines())
      length += 1

    val windowSizes = windowSizeFractions.map(f => (length.toDouble * f).toInt)
    val windFracIter = windowSizeFractions.iterator

    for(windowSize <- windowSizes){
      rollingRelStdDevSingle(name, windowSize, "_" + windFracIter.next.toString)
    }
    println("all rolling std dev with fractions done for: " + name)

  }

  //first value is covariance, second is correlation
  def calcAutoCovarianceCorelation(name: String, offset: Int): (Double, Double) ={

    val fullPath = extractedDataPath + name + extracted_file_ending

    //file length
    val readerLength = io.Source.fromFile(fullPath)
    var wholeFileLength = 0
    for(i<-readerLength.getLines()){
      wholeFileLength += 1
    }
    readerLength.close()

    // offset at the end average
    val readerNoOffset = io.Source.fromFile(fullPath)
    var noOffsetAverage = 0.0
    var j = 0
    val linesNoOffset = readerNoOffset.getLines()
    while(j<wholeFileLength-offset){
      noOffsetAverage += linesNoOffset.next().toDouble
      j+=1
    }
    readerNoOffset.close()
    noOffsetAverage /= (wholeFileLength.toDouble - offset)
    //println(noOffsetAverage)

    //offset at the beginning average
    val readerWithOffset = io.Source.fromFile(fullPath)
    var withOffsetAverage = 0.0
    j = 0
    val linesWithOffset = readerWithOffset.getLines()
    while(j<offset){
      linesWithOffset.next()
      j+=1
    }
    for(i<-linesWithOffset){
      withOffsetAverage += i.toDouble
    }
    readerWithOffset.close()
    withOffsetAverage /= (wholeFileLength.toDouble - offset)
    //println(withOffsetAverage)

    //offset at the end val - expected, stddev
    val noOffsetList = new ListBuffer[Double]()
    var noOffsetStdDev = 0.0
    val readerNoOffsetCalc = io.Source.fromFile(fullPath)
    j = 0
    val linesNoOffsetCalc = readerNoOffsetCalc.getLines()
    var elem = 0.0
    while(j<wholeFileLength-offset){
      elem = linesNoOffsetCalc.next().toDouble
      noOffsetList.addOne(elem - noOffsetAverage)
      noOffsetStdDev += math.pow(elem - noOffsetAverage, 2)
      j+=1
    }
    readerNoOffsetCalc.close()
    noOffsetStdDev = math.sqrt(noOffsetStdDev / noOffsetList.length.toDouble)
    //println(noOffsetStdDev)
    //println(noOffsetList.length)

    //offset at the beginning val - expected, stddev
    val withOffsetList = new ListBuffer[Double]()
    var withOffsetStdDev = 0.0
    val readerWithOffsetCalc = io.Source.fromFile(fullPath)
    j = 0
    val linesWithOffsetCalc = readerWithOffsetCalc.getLines()
    while(j<offset){
      linesWithOffsetCalc.next()
      j+=1
    }
    for(i<-linesWithOffsetCalc){
      withOffsetList.addOne(i.toDouble - withOffsetAverage)
      withOffsetStdDev += math.pow(i.toDouble - withOffsetAverage, 2)
    }
    readerWithOffsetCalc.close()
    withOffsetStdDev = math.sqrt(withOffsetStdDev / withOffsetList.length.toDouble)
    //println(withOffsetStdDev)
    //println(withOffsetList.length)

    //calc auto covariance
    var autoCovariance = 0.0
    for((a,b) <- (withOffsetList zip noOffsetList)){
      autoCovariance += a*b
    }
    //minus 1 for unbiased estimator (whatever it means)
    autoCovariance /= (withOffsetList.length.toDouble - 1)
    (autoCovariance, autoCovariance / noOffsetStdDev / withOffsetStdDev)
  }

  def writeAutoCorelationForDifferentSetsAndOffsets(paramset: Map[String,List[Int]]): Unit ={
    for((name, offsets) <- paramset){
      val writer = new PrintWriter(new File(autocorellationPath + name + autocorellationEnding))
      for(offset <- offsets){
        writer.println(offset + "," + calcAutoCovarianceCorelation(name, offset)._2)
      }
      writer.close()
      println("autocorelation done for " + name)

    }
  }
}
