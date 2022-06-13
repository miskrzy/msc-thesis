package scala.readibling

object ReadiblingMapping {

  val lossFunctionCoefficientsNameMap = Map(
    "c*d^(n-i)_steep__10.0" -> "$f(x)=c(1+d*p)^x,p=10.0$",
    "c*d^(n-i)_steep__7.0" -> "$f(x)=c(1+d*p)^x,p=7.0$",
    "c*d^(n-i)_steep__4.0" -> "$f(x)=c(1+d*p)^x,p=4.0$",
    "c*d^(n-i)_steep__3.0" -> "$f(x)=c(1+d*p)^x,p=3.0$",
    "c*d^(n-i)_steep__2.0" -> "$f(x)=c(1+d*p)^x,p=2.0$",
    "c*d^(n-i)_steep__1.0" -> "$f(x)=c(1+d*p)^x,p=1.0$",
    "ctimesd^(n-i)_steep__10.0" -> "$f(x)=c(1+d*p)^x,p=10.0$",
    "ctimesd^(n-i)_steep__7.0" -> "$f(x)=c(1+d*p)^x,p=7.0$",
    "ctimesd^(n-i)_steep__4.0" -> "$f(x)=c(1+d*p)^x,p=4.0$",
    "ctimesd^(n-i)_steep__3.0" -> "$f(x)=c(1+d*p)^x,p=3.0$",
    "ctimesd^(n-i)_steep__2.0" -> "$f(x)=c(1+d*p)^x,p=2.0$",
    "ctimesd^(n-i)_steep__1.0" -> "$f(x)=c(1+d*p)^x,p=1.0$",
    "coeff_ax^5" -> "$f(x)=x^5$",
    "coeff_ax^2" -> "$f(x)=x^2$",
    "coeff_ax+b_initfrac_0.0" -> "$f(x)=ax+b,f(0)=0$",
    "coeff_ax+b_initfrac_0.5" -> "$f(x)=ax+b,f(0)=0.5$",
    "coeff_ax+b_initfrac_1.0" -> "$f(x)=ax+b,f(0)=1.0$",
    "coeff_a(x-b)^3+c_initfrac_0.0" -> "$f(x)=a(x-b)^3+c,f(0)=0$",
    "coeff_a(x-b)^3+c_initfrac_0.5" -> "$f(x)=a(x-b)^3+c,f(0)=0.5$",
    "coeff_a(x-b)^3+c_initfrac_1.0" -> "$f(x)=a(x-b)^3+c,f(0)=1.0$",
    "minusAXplusBoverX" -> "$f(x)=\\frac{a}{x}$",
    "autocor_bitcoin7500" -> "$Bitcoin\\_autocor\\_maxDelay\\_7500$",
    "autocor_bitcoin13000" -> "$Bitcoin\\_autocor\\_maxDelay\\_13000$",
    "rollingStdDev_bitcoin" -> "$Bitcoin\\_movingStdDev\\_windowsize\\_0.5$",
    "autocor_MSFT2500" -> "$Microsoft\\_autocor\\_maxDelay\\_2500$",
    "autocor_MSFT5000" -> "$Microsoft\\_autocor\\_maxDelay\\_5000$",
    "rollingStdDev_MSFT" -> "$Microsoft\\_movingStdDev\\_windowsize\\_0.5$",
    "autocor_GE4500" -> "$GeneralElectric\\_autocor\\_maxDelay\\_4500$",
    "autocor_GE9000" -> "$GeneralElectric\\_autocor\\_maxDelay\\_9000$",
    "rollingStdDev_GE" -> "$GeneralElectric\\_movingStdDev\\_windowsize\\_0.5$",
    "autocor_AMZN3500" -> "$Amazon\\_autocor\\_maxDelay\\_3000$",
    "rollingStdDev_AMZN" -> "$Amazon\\_movingStdDev\\_windowsize\\_0.5$",
    "autocor_denpasar6000" -> "$Denpasar\\_autocor\\_maxDelay\\_6000$",
    "autocor_minneapolis4000" -> "$Minneapolis\\_autocor\\_maxDelay\\_4000$",
    "autocor_minneapolis2000" -> "$Minneapolis\\_autocor\\_maxDelay\\_2000$"
  )

  val miscMap = Map(
    "samples" -> "number of samples",
    "alpha" -> "$\\alpha$",
    "lossFuncType" -> "loss function type",
    "coeffsType" -> "loss function coefficients",
    "coeffsSum" -> "sum of coefficients",
    "noRepetition" -> "sum excluding repetitions",
    "lastNSum" -> "sum of last n coefficients",
    "firstNormValue" -> "first norm"
  )

  val namesMap = Map(
    "AMZN" -> "Amazon",
    "MSFT" -> "Microsoft",
    "GE" -> "General Electric",
    "bitcoin" -> "Bitcoin",
    "denpasar" -> "Denpasar",
    "minneapolis" -> "Minneapolis"
  )

  val lossFunctionType = Map(
    "log_err_pow_1.0" -> "$|f(i)-f_s(i)|$",
    "rel_err_pow_1.0" -> "$|log(\\frac{f(i)}{f_s(i)})|$",
    "log_err_pow_2.0" -> "$|f(i)-f_s(i)|^2$",
    "rel_err_pow_2.0" -> "$|log(\\frac{f(i)}{f_s(i)})|^2$"
  )
}
