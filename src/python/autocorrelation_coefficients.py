import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\autocorCoeffs\\"
plot_save_dir = "..\\..\\data\\plots\\autocorCoeffs\\"
ending_file = "_autocorCoeff.csv"
ending_plot = "_autocorCoeff_plot"


def plot_from_csv(name,number):
    df = pd.read_csv(source_dir + name + "_" + str(number) + ending_file, names=[name + str(number)])
    df.plot()
    plt.title("autocorrelation-based coefficients")
    plt.savefig(plot_save_dir + name + str(number) + ending_plot)


if __name__ == '__main__':
    plot_from_csv("AMZN",3500)
    plot_from_csv("GE",4500)
    plot_from_csv("GE",9000)
    plot_from_csv("MSFT",2500)
    plot_from_csv("MSFT",5000)
    plot_from_csv("bitcoin",7500)
    plot_from_csv("bitcoin",13000)
    plot_from_csv("minneapolis",2000)
    plot_from_csv("minneapolis",4000)
    plot_from_csv("denpasar",6000)