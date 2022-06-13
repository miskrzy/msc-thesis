import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\growsCharts\\"
plot_save_dir = "..\\..\\data\\plots\\growsCharts\\"
ending_file = "_grows.csv"
ending_plot = "_grows_plot"
ending_file_abs = "_grows_abs.csv"
ending_plot_abs = "_grows_abs_plot"


def plot_from_csv(name, unit, xlabel, ylabel):
    df = pd.read_csv(source_dir + name + ending_file, names=[""])
    df.plot(legend=False)
    plt.title(name + " " + unit)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.savefig(plot_save_dir + name.replace('.', ',') + ending_plot)


def plot_from_csv_abs(name, unit, xlabel, ylabel):
    df = pd.read_csv(source_dir + name + ending_file_abs, names=[""])
    df.plot(legend=False)
    plt.title(name + " " + unit)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.savefig(plot_save_dir + name.replace('.', ',') + ending_plot_abs)


if __name__ == '__main__':
    plot_from_csv("AMZN", "increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv("GE", "increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv("MSFT", "increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv("bitcoin", "increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv("denpasar", "increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv("minneapolis", "increments in relation to time", "time [hours]", "increments [USD]")

    plot_from_csv_abs("AMZN", "absolute increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv_abs("GE", "absolute increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv_abs("MSFT", "absolute increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv_abs("bitcoin", "absolute increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv_abs("denpasar", "absolute increments in relation to time", "time [hours]", "increments [USD]")
    plot_from_csv_abs("minneapolis", "absolute increments in relation to time", "time [hours]", "increments [USD]")
