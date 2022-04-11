import pandas as pd
import matplotlib.pyplot as plt

source_dir = "..\\..\\data\\sampledAndInterpolated\\"
plot_save_dir = "..\\..\\data\\plots\\sampledAndInterpolated\\"
ending_file = "_int.csv"
ending_plot = "_int_plot"


def plot_from_csv(name, unit):
    df = pd.read_csv(source_dir + name + ending_file, names=[name + " " + unit])
    df.plot()
    plt.savefig(plot_save_dir + name.replace('.', ',') + ending_plot)


if __name__ == '__main__':
    plot_from_csv("AMZN_s1000_a1.0_c1.0", "stock value")
    plot_from_csv("GE_s1000_a1.0_c1.0", "stock value")
    plot_from_csv("MSFT_s1000_a1.0_c1.0", "stock value")
    plot_from_csv("bitcoin_s1000_a1.0_c1.0", "stock value")
    plot_from_csv("denpasar_s1000_a1.0_c1.0", "temperature")
    plot_from_csv("minneapolis_s1000_a1.0_c1.0", "temperature")
