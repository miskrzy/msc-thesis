package scala.readibling

import java.io.{File, PrintWriter}

object ReadiblingLogic {

  val sourcePathLoss = "data/lossFunctionResults/"
  val sourcePathSums = "data/sampledCoefficientsSums/"
  val sourcePathMatching = "data/matchingFirstNorm/"

  val destPathReadLoss = "data/readibles/lossFunctionResults/"
  val destPathReadSums = "data/readibles/sampledCoefficientsSums/"
  val destPathReadMatching = "data/readibles/matchingFirstNorm/"

  val endingLoss = ""
  val endingSums = ""
  val endingMatching = ""


  def testings(): Unit = {

    val fileNames = new File(sourcePathLoss).listFiles.map(_.getName).toList

    //fileNames.foreach(println)


    val writer = new PrintWriter(new File(destPathReadLoss + "test.tsv"))

    val ralpha = ReadiblingMapping.lossFunctionCoefficientsNameMap("alpha")

    writer.println(ralpha + "\tc\td\te\tf")
    writer.println(ralpha + "\tc\td\te\tf")
    writer.println(ralpha + "\tc\td\te\tf")
    writer.println(ralpha + "\tc\td\te\tf")


    writer.close()

  }


  def readiblingLossFunctions(): Unit = {

    val fileNames = new File(sourcePathLoss).listFiles.map(_.getName).toList

    for (fileName <- fileNames; if fileName !="README.md") {
      val reader = io.Source.fromFile(sourcePathLoss + fileName)
      val lines = reader.getLines()
      val writer = new PrintWriter(new File(destPathReadLoss + fileName.replace("csv","tsv")))

      val firstLine = lines.next().split(",")
      writer.println(
        "dataset\t" +
        "number of samples\t" +
        "$\\alpha$\t" +
        "c\t" +
        "loss function type\t" +
        "loss function coefficients\t" +
        "result"
      )
      for(line <- lines){
        val lineList = line.split(",")
        writer.print(ReadiblingMapping.namesMap(fileName.split("_").head) + "\t")
        writer.print(lineList(0) + "\t")
        writer.print(lineList(1) + "\t")
        writer.print(lineList(2) + "\t")
        writer.print(ReadiblingMapping.lossFunctionType(lineList(3)) + "\t")
        writer.print(ReadiblingMapping.lossFunctionCoefficientsNameMap(lineList(4)) + "\t")
        writer.println(lineList(5))
      }
      reader.close()
      writer.close()
    }


  }

  def readiblingCoeffSum(): Unit = {

    val fileNames = new File(sourcePathSums).listFiles.map(_.getName).toList

    for (fileName <- fileNames; if fileName !="README.md") {
      val reader = io.Source.fromFile(sourcePathSums + fileName)
      val lines = reader.getLines()
      val writer = new PrintWriter(new File(destPathReadSums + fileName.replace("csv","tsv")))

      val firstLine = lines.next().split(",")
      writer.println(
        "loss function coefficients\t" +
          "number of samples\t" +
          "$\\alpha$\t" +
          "c\t" +
          "sum of coefficients\t" +
          "sum excluding repetitions\t" +
          "sum of last n coefficients"
      )
      for(line <- lines){
        val lineList = line.split(",")
        writer.print(ReadiblingMapping.lossFunctionCoefficientsNameMap(
          fileName
            .replace("_SampledCoeffSums.csv","")
            .replace(",",".")) + "\t")
        writer.print(lineList(0) + "\t")
        writer.print(lineList(1) + "\t")
        writer.print(lineList(2) + "\t")
        writer.print(lineList(3) + "\t")
        writer.print(lineList(4) + "\t")
        writer.println(lineList(5))
      }
      reader.close()
      writer.close()
    }


  }

  def readiblingMatching(): Unit = {

    val fileNames = new File(sourcePathMatching).listFiles.map(_.getName).toList

    for (fileName <- fileNames; if fileName !="README.md") {
      val reader = io.Source.fromFile(sourcePathMatching + fileName)
      val lines = reader.getLines()
      val writer = new PrintWriter(new File(destPathReadMatching + fileName.replace("csv","tsv")))

      val firstLine = lines.next().split(",")
      writer.println(
        "dataset\t" +
          "$\\alpha$\t" +
          "c\t" +
          "loss function coefficients\t" +
          "first norm"
      )
      for(line <- lines){
        val lineList = line.split(",")
        writer.print(ReadiblingMapping.namesMap(lineList(0)) + "\t")
        writer.print(lineList(1) + "\t")
        writer.print(lineList(2) + "\t")
        writer.print(ReadiblingMapping.lossFunctionCoefficientsNameMap(lineList(3)) + "\t")
        writer.println(lineList(4))
      }
      reader.close()
      writer.close()
    }


  }

}
