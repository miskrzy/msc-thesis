import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\autocorellations\\"
plot_save_dir = "..\\..\\data\\plots\\autocorelations\\"
ending_file = "_autocor.csv"
ending_plot = "_autocor_plot"


def plot_from_csv(name, xlabel, ylabel):
    df = pd.read_csv(source_dir + name + ending_file, names=[name])
    df.plot(legend=False)
    plt.title(name + " autocorrelation")
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.savefig(plot_save_dir + name + ending_plot)


if __name__ == '__main__':
    plot_from_csv("AMZN", "delay", "autocorrelation")
    plot_from_csv("GE", "delay", "autocorrelation")
    plot_from_csv("MSFT", "delay", "autocorrelation")
    plot_from_csv("bitcoin", "delay", "autocorrelation")
    plot_from_csv("minneapolis", "delay", "autocorrelation")
    plot_from_csv("denpasar", "delay", "autocorrelation")
