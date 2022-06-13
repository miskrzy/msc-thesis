import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\increasesCharts\\"
plot_save_dir = "..\\..\\data\\plots\\increasesCharts\\"
ending_file = "_increases.csv"
ending_plot = "_increases_plot"
ending_file_abs = "_increases_abs.csv"
ending_plot_abs = "_increases_abs_plot"

index = 1


def plot_from_csv(name, desc, xlabel, ylabel):
    global index
    plt.figure(index)
    df = pd.read_csv(source_dir + name + ending_file, names=[name + " value", "increments"])
    plt.title(name + " " + desc)
    #df.plot(legend=False)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.scatter(df[name + " value"], df["increments"], s=1)
    plt.savefig(plot_save_dir + name.replace('.', ',') + ending_plot)
    index += 1


def plot_from_csv_abs(name, desc, xlabel, ylabel):
    global index
    plt.figure(index)
    df = pd.read_csv(source_dir + name + ending_file_abs, names=[name + " value", "absolute increments"])
    plt.title(name + " " + desc)
    #df.plot(legend=False)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.scatter(df[name + " value"], df["absolute increments"], s=1)
    plt.savefig(plot_save_dir + name.replace('.', ',') + ending_plot_abs)

    index += 1


if __name__ == '__main__':
    plot_from_csv("AMZN", "increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv("GE", "increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv("MSFT", "increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv("bitcoin", "increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv("denpasar", "increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv("minneapolis", "increments in relation to value", "value [USD]", "increments [USD]")

    plot_from_csv_abs("AMZN", "absolute increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv_abs("GE", "absolute increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv_abs("MSFT", "absolute increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv_abs("bitcoin", "absolute increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv_abs("denpasar", "absolute increments in relation to value", "value [USD]", "increments [USD]")
    plot_from_csv_abs("minneapolis", "absolute increments in relation to value", "value [USD]", "increments [USD]")
