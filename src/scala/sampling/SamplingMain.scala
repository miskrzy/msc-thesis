package scala.sampling

object SamplingMain {
  def main(args: Array[String]): Unit = {
    SamplingAlgorithms.sampleAlpaC("AMZN",1,1,1000)
    SamplingAlgorithms.sampleAlpaC("bitcoin",1,1,1000)
    SamplingAlgorithms.sampleAlpaC("denpasar",1,1,1000)
    SamplingAlgorithms.sampleAlpaC("GE",1,1,1000)
    SamplingAlgorithms.sampleAlpaC("minneapolis",1,1,1000)
    SamplingAlgorithms.sampleAlpaC("MSFT",1,1,1000)
  }
}
