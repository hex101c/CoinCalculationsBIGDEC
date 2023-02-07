import matplotlib.pyplot as plt
import numpy as np

factorial_cache = []

def factorial(n):
    if n == 1 or n == 0: 
        factorial_cache.append(1)
        return 1
    else: 
        r = factorial(n-1)*n
        factorial_cache.append(r)
        return r

def choose(n, k):
    return factorial(n)/factorial(k)/factorial(n-k)

def RiemannFunction(n):
    file = open('../Sequence.txt', 'r')
    lines = file.readlines()
    file.close()
    for i in range(0, len(lines)):
        lines[i] = lines[i][1:-3].split(", ")
    lines = lines[0:n]
    indices = [int(x[0]) for x in lines]
    values = np.array([float(x[1]) for x in lines])
    args = np.array(np.argsort(values))
    values = np.array(np.sort(values))
    array = np.stack((args, values), axis=1)
    increment = 1/(2**n-1)
    edges = []
    for i in range(2**n):
        edges.append(i*increment)
    heights = []
    for i in range(len(array)):
        for j in range(int(choose(n, int(array[i][0])))):
            heights.append(array[i][1])
    fig = plt.figure()
    ax = fig.add_subplot()
    ax.stairs(heights, edges)
    plt.title(f"Riemann's Function at index {n}")
    plt.xlabel('x')
    plt.ylabel('Function Value')
    plt.show()

RiemannFunction(5)
