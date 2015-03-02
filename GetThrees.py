def getSetsOfThree(nestedList):
	"""

	>>> zero = [0, 0, 0]
	>>> one = [1, 1, 1]
	>>> two = [2, 2, 2]
	>>> zero2 = [0, 1, 2]
	>>> [[zero, one, two], [zero2]]
	[[[0, 0, 0], [1, 1, 1], [2, 2, 2]], [[0, 1, 2]]]
	>>> getSetsOfThree([[zero, one, two], [zero2]])
	[[0, 0, 0], [1, 1, 1], [2, 2, 2], [0, 1, 2]]

	"""

	toReturn = [];
	for bar in nestedList:
		for note in bar:
			toReturn.append(note)
	return toReturn;