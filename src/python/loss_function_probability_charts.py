import matplotlib.pyplot as plt

plot_save_dir = "..\\..\\data\\plots\\probability_distribution\\"
ending_plot = "_dist_plot"


def probSingle(i, alpha, c):
    return min(1, c * pow(i + 1, -alpha))


def probWhole(i, alpha, c, size):
    prob = probSingle(i, alpha, c)
    for j in range(i + 1, size):
        prob *= (1 - probSingle(j, alpha, c))
    return prob


def plotProbabilityDistribution(alphas, size=1000):
    x = [i for i in range(size)]

    for alpha in alphas.keys():
        for c in alphas[alpha]:
            y = [probWhole(i, alpha, c, size) for i in x]
            print(sum(y))

            plt.plot(x, y, label=f"c= {c}")
        plt.title(f"alpha = {alpha}")
        plt.legend()
        plt.savefig(
            f"{plot_save_dir}probability_distribution_size_{size}_alpha_{str(alpha).replace('.', ',')}{ending_plot}")


if __name__ == '__main__':
    alphas = {1.15: [1.5,2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0]}
    #alphas = {0.9: [0.8,0.9,1.0,1.5,2.0,2.5,3.0,5.0,10.0]}
    #alphas = {0.95: [0.8,0.9,1.0,1.5,2.0,2.5,3.0,5.0,10.0]}
    #alphas = {1.25: [2.0,2.5,3.0,5.0,7.0,10.0,15.0,20.0,30.0,40.0]}
    #alphas = {1.5: [5.0,7.0,10.0,20.0,40.0,70.0,100.0,150.0,200.0,400.0]}
    #alphas = {1.75: [20.0,40.0,100.0,250.0,500.0,1000.0,2000.0]}
    #alphas = {1.5: [0.8,0.9,1.0,1.5,2.0,2.5,3.0,5.0,10.0]}
    #alphas = {1.75: [0.8,0.9,1.0,1.5,2.0,2.5,3.0,5.0,10.0]}
    #alphas = {2: [100.0, 200.0,400.0,600.0,1000.0,2000.0,3000.0]}
    #alphas = {0.65: [0.4,0.5,0.6,0.7,0.8]}
    #alphas = {0: [0.001,0.01]}
    #alphas = {0.75: [0.4,0.5,0.6,0.7,0.8]}
    #alphas = {0.85: [0.4,0.5,0.6,0.7,0.8]}
    #alphas = {0.5: [0.05, 0.1, 0.15, 0.2]}
    #alphas = {0.5: [0.5, 0.25, 0.75, 0.33, 0.66]}
    #alphas = {1.0: [1.0, 2.0, 3.0, 5.0, 10.0]}
    plotProbabilityDistribution(alphas)
