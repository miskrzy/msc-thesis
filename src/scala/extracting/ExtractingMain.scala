package scala.extracting

object ExtractingMain {
  def main(args: Array[String]): Unit = {
    ExtractData.extractColFromFile("AMZN.csv", "Close", ",", "AMZN")
    ExtractData.extractColFromFile("GE.csv", "Close", ",", "GE")
    ExtractData.extractColFromFile("MSFT.csv", "Close", ",", "MSFT")
    ExtractData.extractColFromFile("minneapolis.csv", "Minneapolis", ",", "minneapolis", kelvinToCelsius)
    ExtractData.extractColFromFile("openweatherdata-denpasar-1990-2020.csv", "temp", ",", "denpasar")
    ExtractData.extractColFromFile("bitstampUSD_1-min_data_2012-01-01_to_2021-03-31.csv", "Close", ",", "bitcoin", firstRowToSave = 3000000)
  }

  def kelvinToCelsius(x: String): String = {
    (x.toDouble - 273.15).toString
  }
}
