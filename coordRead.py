
# takes in three lists, one with clefts, one with notes, and one with bars, returns strings 
# giveOutput takes in a pair, [barNumber, [notes in bar]] returns list of 4 lists, each in
# format [note, bar, beat]




lineDif = 4
cleftList = []
barList = []
noteList = []






def sortBarList(barList):
	return sorted(barList, key = lambda x: x[0]*(x[1])**3)
barList = sortBarList(barList)
numLines = len(cleftList)
# numBars = len(barList)
cleftIndex = 0
barIndex = 0
i = 0
currLine = [i, cleftList[i][1]]

def inLine(yPos, yLine):
	if abs(yLine - yPos) < lineDif:
		return true
	return false
# icrement cleftIndex
# list of bar locations on a line, including the cleft at the beginning 
def barsInLine(currLine):
	barsInLineLst = [cleftList[cleftIndex][0]] + [x[0] for x in barList if inLine(x[1], currLine[1])]
	return barsInLineLst

# returns a list of notes between two x-locations
def notesInBar(loc1, loc2, notes):
	return [x for x in notes if (x[0] > loc1 and x[0] < loc2) and inLine(x[1], currLine[1])]

# takes in barsInLine, outputs a semifinal list for one line
def writeSemiFinalList(barsInLine):
	k = 0
	j = 1
	# m = 0
	global barIndex
	semiFinalList = []
	while j < len(barsInLine):
		semiFinalList += [barIndex, notesInBar(barsInLIne[k], barsInLine[j], noteList)]
		barIndex += 1
		k += 1
		j += 1
		# if len(semiFinalList[m][1]) < 4:
			# special handling for blank notes
		# m += 1
	return semiFinalList


# takes in an element of semiFianlList, returns a list of 4 final outputs
def giveOutput(semiFinalListEle):
	finalList = []
	index = 0
	while index < len(semiFinalListEle):
		finalList += [semiFinalListEle[1][index], semiFianlList[0], index]
		index += 1
	return finalList

# creates a list of finalLists for one line
def giveOutputForLine(semiFinalList):
	index = 0
	finalFinalList = []
	while index < len(semiFinalList):
		finalFinalList += giveOutput(semiFinalList[index])
		index += 1
	return finalFinalList

# increments currLine
def nextLine():
	global i
	global currLine
	i += 1
	currLine = [i, cleftList[i][1]]

# brings it all together
def maine():
	finalFinalFinalList = []
	index = 0
	while index < numLines:
		finalFinalFinalList += giveOutputForLine(giveOutput(writeSemiFinalList(barsInLine(currLine))))
		nextLine()
		index += 1
	return finalFinalFinalList

# change finalFInalFInalList into proper format for txt writing
# def reFormat(finalFinalFinalList):

