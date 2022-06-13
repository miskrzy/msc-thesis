package scala.sampling

object SamplingMain {
  def main(args: Array[String]): Unit = {
    sampleStockData()
    sampleWeatherData()
  }

  def sampleStockData(): Unit ={
    val names = List(
      "AMZN",
      "bitcoin",
      "GE",
      "MSFT"
    )
    val samples = List(
      100,
      1000
    )
    val alphasWithCs = Map(
      /*0.9 -> List(1.0,1.5,2.0,3.0,5.0,10.0),
      0.95 -> List(1.0,1.5,2.0,3.0,5.0,10.0),*/
      1.15 -> List(1.5,2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0)
      /*1.25 -> List(2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0,40.0),
      1.5 -> List(5.0,7.0,10.0,20.0,40.0,70.0,100.0,150.0,200.0,400.0),
      1.75 -> List(20.0,40.0,100.0,250.0,500.0,1000.0,2000.0)*/
    )
    for(name <- names){
      for(sample<-samples){
        for((alpha,cs) <-alphasWithCs){
          for(c<-cs){
            SamplingAlgorithms.sampleAlpaC(name,alpha,c,sample)
          }
        }
      }
    }
    /*val alphas = List(
      //2
      /*0.65,
      0.75,
      0.85*/
      /*0.5,*/
      /*1.0*/

    )
    val cs = List(

      //100.0,200.0,400.0,1000.0,2000.0,3000.0,4000.0
      /*0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8*/
      /*0.05,0.1,0.15,0.2*//*,0.25,0.5,0.75,0.33,0.66*/
      /*1.0,2.0,3.0,5.0,10.0*/
    )*/

    /*for(name <- names){
      for(sample<-samples){
        for(alpha <-alphas){
          for(c<-cs){
            SamplingAlgorithms.sampleAlpaC(name,alpha,c,sample)
          }
        }
      }
    }*/

  }

  def sampleWeatherData(): Unit ={
    val names = List(
      "denpasar",
      "minneapolis"
    )
    val samples = List(
      1000,
      20000
    )
    val alphasWithCs = Map(
      /*0.9 -> List(1.0,1.5,2.0,3.0,5.0,10.0),
      0.95 -> List(1.0,1.5,2.0,3.0,5.0,10.0),*/
      1.15 -> List(1.5,2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0)
      /*1.25 -> List(2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0,40.0),
      1.5 -> List(5.0,7.0,10.0,20.0,40.0,70.0,100.0,150.0,200.0,400.0),
      1.75 -> List(20.0,40.0,100.0,250.0,500.0,1000.0,2000.0)*/
    )
    for(name <- names){
      for(sample<-samples){
        for((alpha,cs) <-alphasWithCs){
          for(c<-cs){
            SamplingAlgorithms.sampleAlpaC(name,alpha,c,sample)
          }
        }
      }
    }
    /*val alphas = List(
      2
      /*0.85
      0.75
      0.65,
      0.5,
      1.0*/

    )
    val cs = List(
      100,200,400,1000,2000,3000,4000
      /*0.6,0.7,0.8*/
      /*0.4,0.5,0.6,0.7,0.8*/
      /*0.05,0.1,0.15,0.2,0.25,0.5,0.75,0.33,0.66*/
      /*1.0,2.0,3.0,5.0,10.0*/
    )*/

    /*for(name <- names){
      for(sample<-samples){
        for(alpha <-alphas){
          for(c<-cs){
            SamplingAlgorithms.sampleAlpaC(name,alpha,c,sample)
          }
        }
      }
    }*/

  }
}
