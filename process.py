import cv2
import numpy as np
from matplotlib import pyplot as plt
import sys
from os import system

if len(list(sys.argv))<2:
    filename = 'twinkle.jpg'
else:
    filename = list(sys.argv)[1]
threshold = 0.8
img = cv2.imread(filename,0)

def coord_list(main, filename, threshold):
    template = cv2.imread(filename,0)
    img = cv2.imread(main,0)
    template = cv2.imread(filename,0)
    w, h = template.shape[::-1]
    
    res = cv2.matchTemplate(img,template,cv2.TM_CCOEFF_NORMED)

    # ax1 = plt.subplot(121)
    # plt.imshow(res,cmap = 'gray')
    # plt.title('Matching Result'), plt.xticks([]), plt.yticks([])

    y_list = (np.where(res>threshold)[0] + h/2).tolist()
    x_list = (np.where(res>threshold)[1] + w/2).tolist()
    return zip(x_list, y_list)

def clone_killer(clone_list, error):
    if len(clone_list)<2:
        return clone_list

    close_enough = lambda i:  ((abs(clone_list[0][0] - clone_list[i][0])) + (abs(clone_list[0][1] - clone_list[i][1]))) < error
    for i in range(1,len(clone_list)):
        if close_enough(i):
            return clone_killer(clone_list[1:], error)
    return [clone_list[0]] + clone_killer(clone_list[1:], error)

clef_list = clone_killer(coord_list(filename, 'clef.jpg', threshold),10)
note_list = clone_killer(coord_list(filename, 'note.jpg', threshold), 10)
bar_list = clone_killer(coord_list(filename, 'bar.jpg', threshold), 10)
end_bar = clone_killer(coord_list(filename, 'end_bar.png', threshold),10)
empty_notes = clone_killer(coord_list(filename, 'empty.jpg', threshold), 10)
empty_notes_crossed = clone_killer(coord_list(filename, 'empty_crossed.jpg', threshold), 10)
bar_list+=end_bar
note_list+= empty_notes + empty_notes_crossed
line_height = 2*(max([whatever[1] for whatever in bar_list]) - max([whatever2[1] for whatever2 in clef_list]))

# list_txt = open('list.txt', 'w')
# list_txt.write(repr([note_list, bar_list, clef_list, line_height]))
# list_txt.close()

plt.imshow(img,cmap = 'gray')

for i in clef_list:
    plt.plot(i[0], i[1], ".r", markersize=20)


for i in note_list:
    plt.plot(i[0], i[1], ".b", markersize=20)

for i in bar_list:
    plt.plot(i[0], i[1], ".g", markersize=20)

print line_height
plt.show()
line_height = 9.35

# os.system("python coord_read.py")
# import os
# os.system("java whatever some_arguments")

# coord_file = open('list.txt', 'r')
# coord = eval(coord_file.read())
# note_list = coord[0]
# bar_list = coord[1]
# clef_list = coord[2]
# line_height = coord[3]

def which_clef(pair):
    result_i, result_dist = 0, 1000
    for i in range(len(clef_list)):
        if abs(pair[1]-clef_list[i][1]) < result_dist:
            result_dist = abs(pair[1]-clef_list[i][1])
            result_i = i
    return result_i

def which_measure(note, bar1, bar2):
    if note [0] < bar1[0]:
        return 1
    else:
        return ((note[0]-bar1[0])//(bar2[0]-bar1[0])) + 2

def close_compare(num1,num2,error=3):
    """
    Can have some little error
    """
    if num1<=num2+error and num1>=num2-error:
        return True
    else:
        return False

def identifyNotes (center, notes_center,line_difference):
    """Take in the center coordinate, one notes_center as list and line_difference
    as int or float.

    >>> center = (1, 3)
    >>> notes_center = (100, 0)
    >>> identifyNotes(center, notes_center, 1)
    0
    """
    difference = notes_center - center
    # difference = (2*(center - notes_center)/line_difference)
    # print center, notes_center, line_difference, difference
    # numbers_list = [0,2,4,5,7,9,11,12,14,16,17,19]
    # offset = 7
    # return numbers_list[offset + difference]

    if close_compare(difference,3*line_difference):
        return 0
    elif close_compare(difference,2.5*line_difference):
        return 2
    elif close_compare(difference,2*line_difference):
        return 4
    elif close_compare(difference,1.5*line_difference):
        return 5
    elif close_compare(difference,line_difference):
        return 7
    elif close_compare(difference,0.5*line_difference):
        return 9
    elif close_compare(difference,0):
        return 11
    elif close_compare((-1)*difference,0.5*line_difference):
        return 12
    elif close_compare((-1)*difference,line_difference):
        return 14
    elif close_compare((-1)*difference,1.5*line_difference):
        return 16
    elif close_compare((-1)*difference,2*line_difference):
        return 17
    elif close_compare((-1)*difference,2.5*line_difference):
        return 19
    else:
        return "error"


#pair notes according to clefs
grouped_notes = {}
for note in note_list:
    if which_clef(note) in grouped_notes:
        grouped_notes[which_clef(note)].append(note)
    else:
        grouped_notes[which_clef(note)] = [note]

grouped_bars = {}
for bar in bar_list:
    if which_clef(bar) in grouped_bars:
        grouped_bars[which_clef(bar)].append(bar)
    else:
        grouped_bars[which_clef(bar)] = [bar]

#Produce the tuple list for every clef

def note_tuple(note):
    clef = clef_list[which_clef(note)]
    note_bars = sorted(grouped_bars[which_clef(note)], key = lambda x: x[0]) #sort the note's clef's bars
    bar1 = note_bars[0]
    bar2 = note_bars[1]

    measure = which_measure(note, bar1, bar2)
    other_notes = grouped_notes[which_clef(note)]

    #group notes by measure
    notes_in_same_measure = []
    for other_note in other_notes:
        if which_measure(other_note, bar1, bar2) == measure:
            notes_in_same_measure.append(other_note)
    notes_in_same_measure = sorted(notes_in_same_measure, key = lambda x: x[0])

    which_rank = lambda : notes_in_same_measure.index(note) + 1
    return (identifyNotes(clef[1], note[1], line_height), which_measure(note, bar1, bar2) + 4*which_clef(note) -1, which_rank()-1)

def writeNotes(data, file_name):
    """
    Erase the file and map the data on it. (note,bar,#inthebar)
    twinkle = [(0, 0, 0),(0, 0, 1),(7, 0, 2)(7, 0, 3),(9, 1, 0),(9, 1, 1), (7, 1, 2), (100, 1, 3),
               (5,2,0),(5,2,1),(4,2,2),(4,2,3),(2,3,0),(2,3,1),(0,3,2),(100,3,3)]

    """
    with open(file_name+".txt","w") as fh:
        for note in data:
            in_tuple = note_tuple(note)
            fh.write("("+str(in_tuple[0])+" "+str(in_tuple[1])+" "+str(in_tuple[2])+")")
            fh.write("\n")
        fh.close()

writeNotes(note_list, 'notes')
system("java Tabulator")
