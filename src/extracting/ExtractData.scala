package extracting

import java.io.{File, PrintWriter}

object ExtractData {

  val rawDataPath = "data/raw/"
  val extractedDataPath = "data/extracted/"

  def extractColFromFile(sourceFileName: String, colName: String, separator: String, sinkFileName: String): Unit = {
    val reader = io.Source.fromFile(rawDataPath + sourceFileName)
    val fileIterator = reader.getLines()

    val writer = new PrintWriter(new File(extractedDataPath + sinkFileName))

    val fileColNames = fileIterator.next().split(separator)
    val colIndex = fileColNames.indexOf(colName)

    //looping file
    for(line <- fileIterator) {
      val value = line.split(separator)(colIndex)
      writer.println(value)
    }

    writer.close()
    reader.close()
  }

}
