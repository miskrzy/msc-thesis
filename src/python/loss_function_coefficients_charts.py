import matplotlib.pyplot as plt
import math

plot_save_dir = "..\\..\\data\\plots\\coeffs\\"
ending_plot = "_coeff_plot"

def plotX2noTranslationCoefficients(size):
    a = 3/size/size/(size-1)

    xs = [i for i in range(0, size)]
    ys = [a * i * i for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=a\cdot x^2$"))
    plt.legend()

def plotX5noTranslationCoefficients(size):
    a = 6/(size-1)/(size-1)/(size-1)/size/size/size

    xs = [i for i in range(0, size)]
    ys = [a * i * i * i * i * i for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=a\cdot x^5$"))
    plt.legend()

def plotLinearCoefficients(initialFraction, size):
    b = initialFraction / size
    a = ((2.0 - initialFraction) / size - b) / (size - 1)

    xs = [i for i in range(0, size)]
    ys = [a * i + b for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=ax+b,\, f(0)=" + str(initialFraction) + "$"))
    plt.legend()

def plotX3Coefficients(initialFraction, size):
    b = (size - 1) / 2
    a = 8 * (1 - initialFraction) / size / (size - 1) / (size - 1) / (size - 1)
    c = 1 / size

    xs = [i for i in range(0, size)]
    ys = [a * pow(i - b, 3) + c for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=a(x-b)^3+c,\, f(0)=" + str(initialFraction) + "$"))
    plt.legend()

def plotHiperbolicCoefficients(size):
    a = 1.0 / math.log((size + size / 10.0)/(size / 10.0))

    xs = [i for i in range(0, size)]
    ys = [a / (i + size / 10.0) for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=\\frac{a}{x+b}$"))
    plt.legend()

#c*d^-(n-i)
# steepness should be 1 to 10, bigger is less steep
def plotExponentialCoefficients(steepness, size):

    d = 1.0 + 10.0 / 5.0 / float(size) * float(steepness)
    c = math.log(d) / (1/d - math.pow(d, -size))
    #print(c)

    xs = [i for i in range(0, size)]
    ys = [c * math.pow(d, (i-size)) for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("$f(x)=c(1+d\cdot p)^x,\, p=" + str(steepness)+"$"))
    plt.legend()

def oneChart():
    size = 100000
    # plotLinearCoefficients(0.0, size)
    # plotLinearCoefficients(0.5, size)
    # plotLinearCoefficients(1.0, size)
    # plotX3Coefficients(0.0, size)
    # plotX3Coefficients(0.5, size)
    # plotX3Coefficients(1.0, size)
    plotX2noTranslationCoefficients(size)
    plotX5noTranslationCoefficients(size)
    plotHiperbolicCoefficients(size)
    plt.savefig(plot_save_dir + "loss_func_coeffs_size_" + str(size) + ending_plot)
    plt.show()


def exponentialChart():
    size = 100000
    plotExponentialCoefficients(1, size)
    plotExponentialCoefficients(2, size)
    plotExponentialCoefficients(3, size)
    plotExponentialCoefficients(4, size)
    plotExponentialCoefficients(7, size)
    plotExponentialCoefficients(10, size)
    plt.savefig(plot_save_dir + "loss_func_coeffs_exponential_size_" + str(size) + ending_plot)
    plt.show()



if __name__ == '__main__':
    oneChart()
    #exponentialChart()
