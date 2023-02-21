import board
import copy
import sys
sys.setrecursionlimit(1000000000)

def expecitmax(node, is_max):
    if (node.left == None and node.right == None and node.up == None and node.down == None):
        return node.value

    if (is_max):
        return max(expecitmax(node.left, False), expecitmax(node.right, False), expecitmax(node.up, False), expecitmax(node.down, False))
    else:
        return (expecitmax(node.left, True) + expecitmax(node.right, True) + expecitmax(node.up, True) + expecitmax(node.down, True)) / 4

def makeTree(b, layers):
    moveList = [copy.deepcopy(b)]
    moves = 0
    for x in range(layers):
        moves += 4**x
    for x in range(moves):
        if moveList[x] == 0:
            for x in range(4):
                moveList.append(0)
        else:
            temp = moveList[x]
            b = copy.deepcopy(temp)
            possibleMoves = moveList[x].validMoves()
            if (1,0) in possibleMoves: #Down
                b.makeMove((1,0))
                moveList.append(b)
                b = b.undoMove()
            else:
                moveList.append(0)

            if (-1,0) in possibleMoves: #Up
                b.makeMove((-1,0))
                moveList.append(b)
                b = b.undoMove()
            else:
                moveList.append(0)

            if (0,1) in possibleMoves: #Right
                b.makeMove((0,1))
                moveList.append(b)
                b = b.undoMove()
            else:
                moveList.append(0)

            if (0,-1) in possibleMoves: #Left
                b.makeMove((0,-1))
                moveList.append(b)
                b = b.undoMove()
            else:
                moveList.append(0)
    
    averageList = []
    for x in range(layers + 1):
        averageList.append([])
        max = 0
        min = 0
        for y in range(x + 1):
            max += 4**y
        for y in range(x):
            min += 4**y
        for y in range(min, max):
            if (moveList[y] == 0):
                averageList[x].append(0)
            else:
                averageList[x].append(moveList[y].getPoints())
    
    for x in range(len(averageList) - 1):
        x += 1
        layer = layers - x
        if layer != 0:
            for y in range(len(averageList[layer])):
                avg = 0
                for z in range(4):
                    avg += averageList[layer + 1][0]
                    del averageList[layer + 1][0]
                avg = avg / 4
                averageList[layer][y] = avg
    #print(averageList)

    maxValue = 0
    for x in range(len(averageList[1])):
        if x == 0:
            maxValue = averageList[1][x]
        if maxValue < averageList[1][x]:
            maxValue = averageList[1][x]
    maxIndex = averageList[1].index(maxValue)

    if (maxIndex == 0):
        bestMove = (1,0)
    elif (maxIndex == 1):
        bestMove = (-1,0)
    elif (maxIndex == 2):
        bestMove = (0,1)
    else:
        bestMove = (0,-1)

    return bestMove
        
            

    #Down, Up, Right, Left

if __name__ == '__main__':
    #b.printBoard()
    #b.makeMove((1,0))
    #b.printBoard()
    #print(b.getPoints())
    #print(makeTree(b, 5))
    print("DONT TOUCH ME!")
    b = board.board([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]], None, 0)
    b.newNum(2)
    while(len(b.validMoves()) != 0):
        b.makeMove(makeTree(b, 9)) #The 9 is how many moves ahead it will look aka here it will look 9 moves ahead
        b.printBoard()
    print("Done")

    #for x in range(25):
    #    b = board.board([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]], None, 0)
    #    b.newNum(2)
    #    while(len(b.validMoves()) != 0):
    #        b.makeMove(makeTree(b, 3))
    #    b.printBoard()
    #print("Done")