import matplotlib.pyplot as plt

file = open('../Sequence.txt', 'r')
lines = file.readlines()
for i in range(0, len(lines)):
    lines[i] = lines[i][1:-3].split(", ")
indices = [int(x[0]) for x in lines]
values = [float(x[1]) for x in lines]
differences = []
for i in range(0, len(values) - 1):
    differences.append(values[i] - values[i+1])

def sequence():
    fig = plt.figure()
    ax = fig.add_subplot()
    ax.text(50, 0.9, "Use the zoom tool to closely examine the sequence's behavior", fontsize=10)
    plt.title('Sequence Behavior as n Varies')
    plt.xlabel('n')
    plt.ylabel('Sequence Value')
    ax.plot(indices[1:1000], values[1:1000])
    plt.show()

def difference():
    fig = plt.figure()
    ax = fig.add_subplot()
    ax.text(50, 0.25, "Use the zoom tool to closely examine the differences' behavior", fontsize=10)
    plt.title('Difference Behavior as n Varies')
    plt.xlabel('n')
    plt.ylabel('Difference Value')
    ax.plot(indices[1:1000], differences[1:1000])
    plt.show()

sequence()
difference()