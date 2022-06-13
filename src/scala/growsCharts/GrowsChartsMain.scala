package scala.growsCharts

object GrowsChartsMain {
  def main(args: Array[String]): Unit = {
    val names = List(
      "AMZN",
      "GE",
      "MSFT",
      "bitcoin",
      "denpasar",
      "minneapolis"
    )
    //GrowsChartsLogic.calcBulk(names)
    //GrowsChartsLogic.calcBulkAbs(names)
    IncreasesChartsLogic.calcBulk(names)
    IncreasesChartsLogic.calcBulkAbs(names)
  }

}
