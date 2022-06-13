import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\extracted\\"
plot_save_dir = "..\\..\\data\\plots\\extracted\\"
ending_file_extracted = "_ext.csv"
ending_plot_extracted = "_ext_plot"


def plot_from_csv(name, unit, xlabel, ylabel):
    df = pd.read_csv(source_dir + name + ending_file_extracted)
    df.plot(legend=False)
    plt.title(name + " " + unit)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.savefig(plot_save_dir + name + ending_plot_extracted)


if __name__ == '__main__':
    plot_from_csv("AMZN", "stock value", "time [days]", "price [USD]")
    plot_from_csv("GE", "stock value", "time [days]", "price [USD]")
    plot_from_csv("MSFT", "stock value", "time [days]", "price [USD]")
    plot_from_csv("bitcoin", "stock value", "time [hours]", "price [USD]")
    plot_from_csv("denpasar", "temperature", "time [hours]", "temperature [$^\circ$C]")
    plot_from_csv("minneapolis", "temperature", "time [hours]", "temperature [$^\circ$C]")
