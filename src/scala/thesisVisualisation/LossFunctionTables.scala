package scala.thesisVisualisation

import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer
import scala.readibling.ReadiblingLogic.sourcePathLoss
import scala.readibling.ReadiblingMapping

object LossFunctionTables {

  val lossReadPath = "data/readibles/lossFunctionResults/"
  val lossReadEnd = "_loss.tsv"
  val lossWritePath = "data/thesisVisualisation/lossFunctions/"
  val lossWriteEnd = "_loss.tsv"

  val datasetSeq = Seq("Amazon","Microsoft","General Electric","Bitcoin","Denpasar","Minneapolis")


  def calcAllLosses(): Unit = {
    val fileNames = new File(lossReadPath).listFiles.map(_.getName).toList

    val allResult = new ListBuffer[LossParams]()

    for (fileName <- fileNames; if fileName != "README.md") {
      val reader = io.Source.fromFile(lossReadPath + fileName)
      val lines = reader.getLines()
      lines.next()
      for (line <- lines) {
        val elements = line.split("\t")
        val saveName = fileName
        val name = elements(0)
        val samples = elements(1).toInt
        val alpha = elements(2).toDouble
        val c = elements(3).toDouble
        val lossType = elements(4)
        val coeffType = elements(5)
        val result = elements(6).toDouble
        if (!lossType.contains("log")) {
          allResult.addOne(LossParams(
            name,
            samples,
            alpha,
            c,
            lossType,
            coeffType,
            result,
            saveName
          )
          )
        }
      }
      reader.close()
    }

    val groupedByCoeffs = allResult.groupBy(x => x.coeffType)

    val mapCoeffNamesToUnreadible = ReadiblingMapping.lossFunctionCoefficientsNameMap.map(_.swap)
      .map(x => (x._1, x._2.replace(".", ",").replace("*", "times")))


    for ((coeff, paramList) <- groupedByCoeffs) {
      val writer = new PrintWriter(new File(lossWritePath + mapCoeffNamesToUnreadible(coeff) + lossWriteEnd))

      val groupedByName = paramList.groupBy(params => params.name)

      val groupedByNameSorted = groupedByName.map(pair => (pair._1, pair._2.sortWith((x1, x2) => (x1.result < x2.result))))

      val groupedByNameSortedTop3Taken = groupedByNameSorted.map(pair => (pair._1, pair._2.take(3)))

      writer.println(
        "dataset\t" +
          "samples\t" +
          "$\\alpha$\t" +
          "c\t" +
          "loss func. type\t" +
          "loss func. coeff.\t" +
          "result"
      )

      for(dataset<-datasetSeq){
        try {
          groupedByNameSortedTop3Taken(dataset).foreach(writer.println)
        }catch{
          case _: Throwable => None
        }
      }



      writer.close()
    }


  }
}
