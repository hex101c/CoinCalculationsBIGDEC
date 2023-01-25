file = open('Sequence.txt', 'r')
lines = file.readlines()
for i in range(0, len(lines)):
    lines[i] = lines[i][1:-3].split(", ")
indices = [int(x[0]) for x in lines]
values = [float(x[1]) for x in lines]
diffplus = []
for i in range(1, len(values) - 1):
    diffplus.append(values[i+1] - values[i])
diffminus = []
for i in range(1, len(values) - 1):
    diffminus.append(values[i] - values[i-1])

def limit_with_error():
    maxus = []
    minus = []
    array = []
    limit = 0
    error = 0
    for i in range(1, len(values)-1):
        try:
            if (diffplus[i] > 0 and diffminus[i] < 0):
                minus.append(values[i])
                if (len(minus) == 2):
                    limit = minus[0]/4+minus[1]/4+maxus[0]/2
                    error = (minus[1] - minus[0])/2
                    minus.remove(minus[0])
                    array.append([limit, error, i+1])
            if (diffplus[i] < 0 and diffminus[i] > 0):
                maxus.append(values[i])
                if (len(maxus) == 2):
                    limit = maxus[0]/4+maxus[1]/4+minus[0]/2
                    error = (maxus[0] - maxus[1])/2
                    maxus.remove(maxus[0])
                    array.append([limit, error, i+1])
        except:
            pass
    return array

file = open('Limit.txt','w')
file.write("[Limit Estimate, Error, n-value]\n")
for value in limit_with_error():
	file.write(str(value)+"\n")
file.write("How can the error increase? \n Note that this error assumes movement centered about the limit, \n and the behavior of the sequence calms down later on. \n Thus, later errors are more accurate.")
file.close()