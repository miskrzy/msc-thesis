package scala.lossfunctions

import java.io.{File, FileWriter, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object CalculateEveryLoss {

  val extractedDataPath = "data/extracted/"
  val interpolatedDataPath = "data/sampledAndInterpolated/"
  val lossFunctionsResultsDataPath = "data/lossFunctionResults/"

  val extracted_file_ending = "_ext.csv"
  val interpolated_file_ending = "_int.csv"
  val loss_functions_file_ending = "_loss.csv"

  def calcAllLossFuncs(parametersList: Map[String, List[LossFunctionParameters]], overwrite: Boolean = true): Unit = {
    //if overwrite is false read the already calculated loss function file and filter out combinations that are already there
    var filteredParameterList = parametersList
    if (!overwrite) {
      filteredParameterList = parametersList.map {
        case (k, v) =>
          val parametersToFilter = getExistingParameterCombinations(k)
          (k, v.filterNot(x => parametersToFilter.contains(x)))
      }
    }

    //running grouped loss function calculations
    for ((name, paramsList) <- filteredParameterList) {

      var appendFile = false
      if(!overwrite) appendFile = true
      val overwriteButNewFile = !Files.exists(Paths.get(lossFunctionsResultsDataPath + name + loss_functions_file_ending))
      val writer = new PrintWriter(new FileWriter(new File(lossFunctionsResultsDataPath + name + loss_functions_file_ending), appendFile))
      if(overwrite || overwriteButNewFile)
        writer.println("samples,alpha,c,lossFuncType,coeffsType,result")

      for (params <- paramsList) {
        val filePathInterpolated = interpolatedDataPath +
          name + "_s" +
          params.numberOfReservoirs + "_a" +
          params.alpha + "_c" + params.c +
          interpolated_file_ending
        val filePathExtracted = extractedDataPath + name + extracted_file_ending
        val result = params.lossFunction.calculateLossGeneral(filePathExtracted, filePathInterpolated)

        writer.println(params.numberOfReservoirs + "," +
          params.alpha + "," +
          params.c + "," +
          params.lossFunction.getLossFunctionType + "," +
          params.lossFunction.getCoefficientsType + "," +
          result)
      }
      writer.close()
      println(name + " - loss functions calculated")
    }
  }

  def getExistingParameterCombinations(datasetName: String): List[LossFunctionParameters] = {

    val lossFRelStr = "rel_err"
    val lossFLogStr = "log_err"
    val coeffXStr = "coeff_ax+b"
    val coeffX3Str = "coeff_a(x-b)^3+c"
    val coeffX2 = "coeff_ax^2"
    val coeffX5 = "coeff_ax^5"
    val coeff1OverX = "coeff_1/x"
    val rollStdDevAMZN = "rollingStdDev_AMZN"
    val rollStdDevMSFT = "rollingStdDev_MSFT"
    val rollStdDevGE = "rollingStdDev_GE"
    val rollStdDevBitcoin = "rollingStdDev_bitcoin"
    val coeffHiperbolic = "minusAXplusBoverX"
    val autocorAMZN3500 = "autocor_AMZN3500"
    val autocorMSFT2500 = "autocor_MSFT2500"
    val autocorMSFT5000 = "autocor_MSFT5000"
    val autocorGE4500 = "autocor_GE4500"
    val autocorGE9000 = "autocor_GE9000"
    val autocorBitcoin7500 = "autocor_bitcoin7500"
    val autocorBitcoin13000 = "autocor_bitcoin13000"
    val autocorDenpasar6000 = "autocor_denpasar6000"
    val autocorMinneapolis2000 = "autocor_minneapolis2000"
    val autocorMinneapolis4000 = "autocor_minneapolis4000"
    val exponentialCoefficients = "c*d^(n-i)_steep"


      if(!Files.exists(Paths.get(lossFunctionsResultsDataPath + datasetName + loss_functions_file_ending))){
      return List[LossFunctionParameters]()
    }
    val fileLossFunctionsReader = io.Source.fromFile(lossFunctionsResultsDataPath + datasetName + loss_functions_file_ending)
    val fileLossFunctionsLines = fileLossFunctionsReader.getLines()
    fileLossFunctionsLines.next()

    val paramList = ListBuffer[LossFunctionParameters]()

    for(line <- fileLossFunctionsLines){
      val params = line.split(',')
      val s = params(0).toInt
      val a = params(1).toDouble
      val c = params(2).toDouble
      val lossFWhole = params(3).split('_')
      val lossF = lossFWhole(0) + "_" + lossFWhole(1)
      val lossFPower = lossFWhole(3).toDouble
      val coeffsWhole = params(4).split('_')
      var coeffs = ""
      try {
        coeffs = coeffsWhole(0) + "_" + coeffsWhole(1)
      }catch {
        case _:Throwable => coeffs = coeffsWhole(0)
      }
      var coeffInitFrac = - 1.0
      try {
        coeffInitFrac = coeffsWhole(3).toDouble
      }catch {
        case _:Throwable =>
      }

      var coeffParam: Coefficients = new LinearCoefficients(coeffInitFrac)
      if(coeffs == coeffX3Str) coeffParam = new X3Coefficients(coeffInitFrac)
      if(coeffs == coeffX2) coeffParam = new X2NoTranslationCoefficients()
      if(coeffs == coeffX5) coeffParam = new X5NoTranslationCoefficients()
      if(coeffs == coeff1OverX) coeffParam = new OverXCoefficients()
      if(coeffs == rollStdDevAMZN) coeffParam = new RollingStdDevBasedCoefficients("AMZN")
      if(coeffs == rollStdDevMSFT) coeffParam = new RollingStdDevBasedCoefficients("MSFT")
      if(coeffs == rollStdDevGE) coeffParam = new RollingStdDevBasedCoefficients("GE")
      if(coeffs == rollStdDevBitcoin) coeffParam = new RollingStdDevBasedCoefficients("bitcoin")
      if(coeffs == coeffHiperbolic) coeffParam = new MinusAxPlusBOverXCoefficients()
      if(coeffs == autocorAMZN3500) coeffParam = new AutocorrelationBasedCoefficients("AMZN", 3500)
      if(coeffs == autocorMSFT2500) coeffParam = new AutocorrelationBasedCoefficients("MSFT", 2500)
      if(coeffs == autocorMSFT5000) coeffParam = new AutocorrelationBasedCoefficients("MSFT", 5000)
      if(coeffs == autocorGE4500) coeffParam = new AutocorrelationBasedCoefficients("GE", 4500)
      if(coeffs == autocorGE9000) coeffParam = new AutocorrelationBasedCoefficients("GE", 9000)
      if(coeffs == autocorBitcoin7500) coeffParam = new AutocorrelationBasedCoefficients("bitcoin", 7500)
      if(coeffs == autocorBitcoin13000) coeffParam = new AutocorrelationBasedCoefficients("bitcoin", 13000)
      if(coeffs == autocorDenpasar6000) coeffParam = new AutocorrelationBasedCoefficients("denpasar", 6000)
      if(coeffs == autocorMinneapolis2000) coeffParam = new AutocorrelationBasedCoefficients("minneapolis", 2000)
      if(coeffs == autocorMinneapolis4000) coeffParam = new AutocorrelationBasedCoefficients("bitcoin", 4000)
      if(coeffs == exponentialCoefficients) coeffParam = new ExponentialCoefficients(coeffInitFrac)

      var lossFParam: LossFunction = new RelativeErrorLossFunction(coeffParam, lossFPower)
      if(lossF == lossFLogStr) lossFParam = new LogarithmicErrorLossFunction(coeffParam, lossFPower)

      paramList.addOne(LossFunctionParameters(s,a,c,lossFParam))
    }
    paramList.toList
  }

  def calcAllCombinationsFromInterpolatedFiles(lossFList: List[LossFunction], overwrite: Boolean = true): Unit = {

    val resMap = mutable.Map[String, ListBuffer[LossFunctionParameters]]()

    val fileNameList = new File(interpolatedDataPath).listFiles.map(_.getName).toList
    for(fileName <- fileNameList){
      if(fileName != "README.md"){
        val params = fileName.split('_')
        val name = params(0)
        val s = params(1).split('s').last.toInt
        val a = params(2).split('a').last.toDouble
        val c = params(3).split('c').last.toDouble

        resMap(name) = resMap.getOrElse(name, new ListBuffer[LossFunctionParameters]())
        for(lossF <- lossFList){
          resMap(name) += LossFunctionParameters(s, a, c, lossF)
        }
      }
    }

    val parameterList = resMap.map{
      case (k,v) => (k,v.toList)
    }.toMap

    calcAllLossFuncs(parameterList, overwrite)
  }

}
