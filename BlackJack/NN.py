#NN.py
# Class for a neural network that takes in a set of data that contains an array of inputs, with
# the expected outputs in the final column for training. The NN is then capable of producing
# a guess as to how input data should be categorized, given it's training.

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
        self.cost_over_time = []
        print("Initializing Network...\nStarting Weights:", self.w,"\nStarting Bias:", self.b)

        # These values better show the cost being minimized over time, since random values have a chance at
        # being correct on the first guess, therefore giving a flat line from the start
        #self.w[0] = 3
        #self.w[1] = -3
        #self.b = -3

    # Using sigmoid activation function to get a z value between 0 and 1
    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    # Derivative of sigmoid, used to find out the direction the network needs
    # to be adjusted in to hit minimum cost
    def sigmoid_p(self, x):
        return self.sigmoid(x) * (1 - self.sigmoid(x))

    # This training function iterates a user-given amount of times. Every
    # iteration picks a random row from our input data, and tunes the weights
    # in the NN to minimize the difference between it's guesses and the target
    # value, which is stored in the last column of the row. Tuning is done by
    # adjusting the weights and bias in the direction given by sigmoid', in the
    # amount of a the cost function (difference from guess vs expected), differentiated
    # with respect to each weight and bias, multiplied by a learning rate.
    def train(self, iterations):
        cost_sum = 0
        print("Training Network...")
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

            for k in range(len(point) - 1):
                d_cost_dw.append(d_cost * point[k])
                self.w[k] = self.w[k] - self.learning_rate * d_cost_dw[k]
            d_cost_db = d_cost
            self.b = self.b - self.learning_rate * d_cost_db

            cost_sum += cost
            if i > 0:
                self.cost_over_time.append(cost_sum / i)

        icost = self.cost_over_time[50]
        fcost = self.cost_over_time[-1]
        print("Done!\nFinal Weights:", self.w, "\nFinal Bias:", self.b, "\nReduced cost by", str((icost - fcost) * 100 / icost) + "%")
        print()

    # This function uses matplotlib to plot out the average cost over time,
    # which is gathered during training.
    def plot_cost(self):
        plt.plot(self.cost_over_time)
        plt.ylabel("Cost")
        plt.xlabel("Iterations")
        plt.axis([0, len(self.cost_over_time), 0, 1])
        plt.show()

    #returns close to 1 for yes, close to 0 for no
    def guess(self, point):
        accum = 0
        for i in range(len(point)):
            accum += (point[i] * self.w[i])
        z = accum + self.b
        pred = self.sigmoid(z)
        return pred
