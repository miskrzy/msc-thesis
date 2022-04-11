import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\extracted\\"
plot_save_dir = "..\\..\\data\\plots\\extracted\\"
ending_file_extracted = "_ext.csv"
ending_plot_extracted = "_ext_plot"


def plot_from_csv(name, unit):
    df = pd.read_csv(source_dir + name + ending_file_extracted, names=[name + " " + unit])
    df.plot()
    plt.savefig(plot_save_dir + name + ending_plot_extracted)


if __name__ == '__main__':
    plot_from_csv("AMZN", "stock value")
    plot_from_csv("GE", "stock value")
    plot_from_csv("MSFT", "stock value")
    plot_from_csv("bitcoin", "stock value")
    plot_from_csv("denpasar", "temperature")
    plot_from_csv("minneapolis", "temperature")
