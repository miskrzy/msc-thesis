package scala.extracting

import java.io.{File, PrintWriter}

object ExtractData {

  val rawDataPath = "data/raw/"
  val extractedDataPath = "data/extracted/"
  val extracted_file_ending = "_ext.csv"

  def extractColFromFile(sourceFileName: String, colName: String, separator: String, sinkFileName: String, conditionFunc: String => String = a => a.toDouble.toString, firstRowToSave: Int = 0): Unit = {
    val reader = io.Source.fromFile(rawDataPath + sourceFileName)
    val fileIterator = reader.getLines()

    val writer = new PrintWriter(new File(extractedDataPath + sinkFileName + extracted_file_ending))

    val fileColNames = fileIterator.next().split(separator)
    val colIndex = fileColNames.indexOf(colName)

    var readValue = "0.0"
    var rowCnt = 0
    for (line <- fileIterator) {
      rowCnt += 1
      try {
        readValue = conditionFunc(line.split(separator)(colIndex))
      } catch {
        case _: Throwable =>
      }
      finally {
        if (rowCnt > firstRowToSave)
          writer.println(readValue)
      }

    }

    writer.close()
    reader.close()
  }

}
