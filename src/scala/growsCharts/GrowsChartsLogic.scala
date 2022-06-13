package scala.growsCharts

import java.io.{File, PrintWriter}

object GrowsChartsLogic {

  val extractedPath = "data/extracted/"
  val extractedEnding = "_ext.csv"

  val growsChartsPath = "data/growsCharts/"
  val growsChartsEnding = "_grows.csv"
  val growsChartsEndingAbs = "_grows_abs.csv"

  def calcOneDataset(name:String): Unit ={
    val reader = io.Source.fromFile(extractedPath + name + extractedEnding)
    val lines = reader.getLines()
    val writer = new PrintWriter(new File(growsChartsPath + name + growsChartsEnding))

    var previous = lines.next().toDouble
    var i = 0
    for(line <- lines){
      val current = line.toDouble
      writer.println(i + "," + (current-previous))

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
    val writer = new PrintWriter(new File(growsChartsPath + name + growsChartsEndingAbs))

    var previous = lines.next().toDouble
    var i = 0
    for(line <- lines){
      val current = line.toDouble
      writer.println(i + "," + math.abs(current-previous))

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
