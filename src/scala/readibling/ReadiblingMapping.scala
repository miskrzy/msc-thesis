package scala.readibling

object ReadiblingMapping {

  val lossFunctionCoefficientsNameMap = Map(
    "c*d^(n-i)_steep__10.0" -> "$f(x)=c(1+d\\cdot p)^x,p=10.0$",
    "c*d^(n-i)_steep__7.0" -> "$f(x)=c(1+d\\cdot p)^x,p=7.0$",
    "c*d^(n-i)_steep__4.0" -> "$f(x)=c(1+d\\cdot p)^x,p=4.0$",
    "c*d^(n-i)_steep__3.0" -> "$f(x)=c(1+d\\cdot p)^x,p=3.0$",
    "c*d^(n-i)_steep__2.0" -> "$f(x)=c(1+d\\cdot p)^x,p=2.0$",
    "c*d^(n-i)_steep__1.0" -> "$f(x)=c(1+d\\cdot p)^x,p=1.0$",
    "ctimesd^(n-i)_steep__10.0" -> "$f(x)=c(1+d\\cdot p)^x,p=10.0$",
    "ctimesd^(n-i)_steep__7.0" -> "$f(x)=c(1+d\\cdot p)^x,p=7.0$",
    "ctimesd^(n-i)_steep__4.0" -> "$f(x)=c(1+d\\cdot p)^x,p=4.0$",
    "ctimesd^(n-i)_steep__3.0" -> "$f(x)=c(1+d\\cdot p)^x,p=3.0$",
    "ctimesd^(n-i)_steep__2.0" -> "$f(x)=c(1+d\\cdot p)^x,p=2.0$",
    "ctimesd^(n-i)_steep__1.0" -> "$f(x)=c(1+d\\cdot p)^x,p=1.0$",
    "coeff_ax^5" -> "$f(x)=a\\cdot x^5$",
    "coeff_ax^2" -> "$f(x)=a\\cdot x^2$",
    "coeff_ax+b_initfrac_0.0" -> "$f(x)=ax+b,f(0)=0$",
    "coeff_ax+b_initfrac_0.5" -> "$f(x)=ax+b,f(0)=0.5$",
    "coeff_ax+b_initfrac_1.0" -> "$f(x)=ax+b,f(0)=1.0$",
    "coeff_a(x-b)^3+c_initfrac_0.0" -> "$f(x)=a(x-b)^3+c,f(0)=0$",
    "coeff_a(x-b)^3+c_initfrac_0.5" -> "$f(x)=a(x-b)^3+c,f(0)=0.5$",
    "coeff_a(x-b)^3+c_initfrac_1.0" -> "$f(x)=a(x-b)^3+c,f(0)=1.0$",
    "minusAXplusBoverX" -> "$f(x)=\\frac{a}{x+b}$",
    "autocor_bitcoin7500" -> "$BTC\\_autocor\\_7500$",
    "autocor_bitcoin13000" -> "$BTC\\_autocor\\_13000$",
    "rollingStdDev_bitcoin" -> "$BTC\\_movRelStdDev$",
    "autocor_MSFT2500" -> "$MSFT\\_autocor\\_2500$",
    "autocor_MSFT5000" -> "$MSFT\\_autocor\\_5000$",
    "rollingStdDev_MSFT" -> "$MSFT\\_movRelStdDev$",
    "autocor_GE4500" -> "$GE\\_autocor\\_4500$",
    "autocor_GE9000" -> "$GE\\_autocor\\_9000$",
    "rollingStdDev_GE" -> "$GE\\_movRelStdDev$",
    "autocor_AMZN3500" -> "$AMZN\\_autocor\\_3500$",
    "rollingStdDev_AMZN" -> "$AMZN\\_movRelStdDev$",
    "autocor_denpasar6000" -> "$Den\\_autocor\\_6000$",
    "autocor_minneapolis4000" -> "$Minn\\_autocor\\_2000$",
    "autocor_minneapolis2000" -> "$Minn\\_autocor\\_4000$"
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
    "rel_err_pow_1.0" -> "$\\left|\\frac{y_i-y^s_i}{y_i}\\right|$",
    "log_err_pow_1.0" -> "$\\left|\\log{\\frac{y_i}{y^s_i}}\\right|$",
    "rel_err_pow_2.0" -> "$\\left|\\frac{y_i-y^s_i}{y_i}\\right|^2$",
    "log_err_pow_2.0" -> "$\\left|\\log{\\frac{y_i}{y^s_i}}\\right|^2$"
  )
}
