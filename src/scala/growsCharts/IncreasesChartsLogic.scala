package scala.growsCharts

import java.io.{File, PrintWriter}

object IncreasesChartsLogic {

  val extractedPath = "data/extracted/"
  val extractedEnding = "_ext.csv"

  val increasesChartsPath = "data/increasesCharts/"
  val increasesChartsEnding = "_increases.csv"
  val increasesChartsEndingAbs = "_increases_abs.csv"

  def calcOneDataset(name:String): Unit ={
    val reader = io.Source.fromFile(extractedPath + name + extractedEnding)
    val lines = reader.getLines()
    val writer = new PrintWriter(new File(increasesChartsPath + name + increasesChartsEnding))

    var previous = lines.next().toDouble
    var i = 0
    for(line <- lines){
      val current = line.toDouble
      writer.println(previous + "," + (current-previous))

      previous = current
      i+=1
    }
    reader.close()
    writer.close()
  }

  def calcBulk(names: List[String]): Unit ={
    names.foreach(calcOneDataset)
  }

  def calcOneDatasetAbs(name:String): Unit ={
    val reader = io.Source.fromFile(extractedPath + name + extractedEnding)
    val lines = reader.getLines()
    val writer = new PrintWriter(new File(increasesChartsPath + name + increasesChartsEndingAbs))

    var previous = lines.next().toDouble
    var i = 0
    for(line <- lines){
      val current = line.toDouble
      writer.println(previous + "," + math.abs(current-previous))

      previous = current
      i+=1
    }
    reader.close()
    writer.close()
  }

  def calcBulkAbs(names: List[String]): Unit ={
    names.foreach(calcOneDatasetAbs)
  }
}
