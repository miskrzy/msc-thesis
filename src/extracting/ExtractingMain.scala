package extracting

object ExtractingMain {
  def main(args: Array[String]): Unit = {
    ExtractData.extractColFromFile("AMZN.csv", "Close", ",", "AMZN_ext.csv")
  }
}
