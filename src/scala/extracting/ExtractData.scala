package scala.extracting

import java.io.{File, PrintWriter}

object ExtractData {

  val valuesToSkipList = List("NaN")

  val rawDataPath = "data/raw/"
  val extractedDataPath = "data/extracted/"
  val extracted_file_ending = "_ext.csv"

  def extractColFromFile(sourceFileName: String, colName: String, separator: String, sinkFileName: String, conditionFunc: String => String = a => a.toDouble.toString, firstRowToSave: Int = 0, takeEveryNth: Int = 1): Unit = {
    val reader = io.Source.fromFile(rawDataPath + sourceFileName)
    val fileIterator = reader.getLines()

    val writer = new PrintWriter(new File(extractedDataPath + sinkFileName + extracted_file_ending))

    val fileColNames = fileIterator.next().split(separator)
    val colIndex = fileColNames.indexOf(colName)

    var readValue = "0.0"
    var rowCnt = 0
    var filter = takeEveryNth
    for (line <- fileIterator) {
      filter -= 1
      rowCnt += 1
      if(filter == 0){
        try {
          val tempValue = conditionFunc(line.split(separator)(colIndex))
          readValue = if(valuesToSkipList.contains(tempValue)){
            readValue
          }else{
            tempValue
          }
        } catch {
          case _: Throwable =>
        }
        finally {
          if (rowCnt > firstRowToSave)
            writer.println(readValue)
        }
        filter = takeEveryNth
      }

    }

    writer.close()
    reader.close()
    println(sinkFileName + " extracted")
  }

}
