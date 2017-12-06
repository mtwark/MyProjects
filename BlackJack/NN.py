import numpy as np
from matplotlib import pyplot as plt


class NN:
    def __init__(self, data):
        self.learning_rate = 0.0008
        self.data = data
        self.w = []
        for i in range(2):
            self.w.append(np.random.randn())
        self.b = np.random.randn()

    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    def sigmoid_p(self, x):
        return self.sigmoid(x) * (1 - self.sigmoid(x))

    def graph_sig(self, lower, upper, subd):
        x = np.linspace(lower, upper, subd)
        y = self.sigmoid_p(x)
        plt.plot(x, y, c = 'r')
        plt.plot(x, y, c='b')
        plt.show()

    def train(self, iterations):
        x = []
        y = []
        print("Starting w:",self.w)
        for i in range(iterations):
            irand = np.random.randint(len(self.data))
            point = self.data[irand]
            accum = 0
            for j in range(len(point) - 1):
                accum += (point[j] * self.w[j])
            z = accum + self.b
            pred = self.sigmoid(z)


            target = point[-1]
            cost = (pred - target) ** 2


            d_cost = 2 * (pred - target) * self.sigmoid_p(z)
            d_cost_dw = []

            for i in range(len(point) - 1):
                d_cost_dw.append(d_cost * point[i])
                self.w[i] = self.w[i] - self.learning_rate * d_cost_dw[i]
            d_cost_db = d_cost
            self.b = self.b - self.learning_rate * d_cost_db
            x.append(i)
            y.append(cost)
            #avg_cost = costs / i
        print(y[-1])
        plt.plot(y)
        plt.ylabel("Cost")
        plt.xlabel("Iterations")
        plt.show()
        print(self.w)

    #returns close to 1 for yes, close to 0 for no
    def guess(self, point):
        accum = 0
        for i in range(len(point)):
            accum += (point[i] * self.w[i])
        z = accum + self.b
        pred = self.sigmoid(z)
        return pred
