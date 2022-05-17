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

    plt.plot(xs, ys, label=("ax^2"))
    plt.legend()

def plotX5noTranslationCoefficients(size):
    a = 6/(size-1)/(size-1)/(size-1)/size/size/size

    xs = [i for i in range(0, size)]
    ys = [a * i * i * i * i * i for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("ax^5"))
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

    plt.plot(xs, ys, label=("ax+b_initfrac_" + str(initialFraction)))
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

    plt.plot(xs, ys, label=("a(x-b)^3+c_initfrac_" + str(initialFraction)))
    plt.legend()

def plotHiperbolicCoefficients(size):
    a = 1.0 / math.log((size + size / 10.0)/(size / 10.0))

    xs = [i for i in range(0, size)]
    ys = [a / (i + size / 10.0) for i in xs]
    sums = 0
    for i in ys:
        sums += i
    print(sums)

    plt.plot(xs, ys, label=("-ax+b/x"))
    plt.legend()


if __name__ == '__main__':
    size = 100000
    plotLinearCoefficients(0.0, size)
    plotLinearCoefficients(0.5, size)
    plotLinearCoefficients(1.0, size)
    plotX3Coefficients(0.0, size)
    plotX3Coefficients(0.5, size)
    plotX3Coefficients(1.0, size)
    plotX2noTranslationCoefficients(size)
    plotX5noTranslationCoefficients(size)
    plotHiperbolicCoefficients(size)
    plt.savefig(plot_save_dir + "loss_func_coeffs_size_" + str(size) + ending_plot)
    plt.show()
