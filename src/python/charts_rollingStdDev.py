import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\statistics\\"
plot_save_dir = "..\\..\\data\\plots\\statistics\\"
ending_file_source = "_rollStdDev.csv"
ending_plot = "_rollStdDev_plot"


def plot_from_csv(name):
    df = pd.read_csv(source_dir + name + ending_file_source, names=[name + " rolling std dev"])
    df.plot()
    plt.savefig(plot_save_dir + name.replace(".", ",") + ending_plot)


if __name__ == '__main__':

    names = ["AMZN", "GE", "MSFT", "bitcoin"]
    window_sizes = [0.5, 0.25, 0.125, 0.0625]
    for name in names:
        for size in window_sizes:
            plot_from_csv(name + "_" + str(size))
