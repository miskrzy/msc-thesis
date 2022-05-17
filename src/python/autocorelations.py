import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\autocorellations\\"
plot_save_dir = "..\\..\\data\\plots\\autocorelations\\"
ending_file = "_autocor.csv"
ending_plot = "_autocor_plot"


def plot_from_csv(name):
    df = pd.read_csv(source_dir + name + ending_file, names=[name])
    df.plot()
    plt.title("autocorrelation")
    plt.savefig(plot_save_dir + name + ending_plot)


if __name__ == '__main__':
    plot_from_csv("AMZN")
    plot_from_csv("GE")
    plot_from_csv("MSFT")
    plot_from_csv("bitcoin")
    plot_from_csv("minneapolis")
    plot_from_csv("denpasar")
