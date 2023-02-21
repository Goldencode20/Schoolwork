import random
import copy

class board:

    def __init__(self, state, lastState, points):
        self.state = state
        self.lastState = lastState
        self.points = points

    def newNum(self, times):
        for loop in range(times):
            spots = []
            for row in range(len(self.state)):
                for col in range(len(self.state)):
                    if self.state[row][col] == 0:
                        spots.append((row, col))
            if len(spots) != 0:
                spot = random.choice(spots)
                randNum = random.randint(0,9)
                if randNum == 0:
                    self.state[spot[0]][spot[1]] = 4
                else:
                    self.state[spot[0]][spot[1]] = 2

    def makeMove(self, move): #Takes a tuple in
        #Set the last state
        self.lastState = board(self.makeCopy(self.state), self.makeCopy(self.lastState), self.getPoints())
        #self.lastState = board(copy.deepcopy(self.state), copy.deepcopy(self.lastState), copy.deepcopy(self.points))
        #We need to move the get all spots up here but in order to do that we need in the while loop update all of the spots
        
        Change = True
        if move == (0,0):
            Change = False
        while(Change):
            Change = False
            
            #Get all the spots that are not empty and set them in the order to move them all
            spots = []
            if move == (1,0) or move == (-1,0):
                for row in range(len(self.state)):
                    for col in range(len(self.state)):
                        if self.state[row][col] != 0:
                            spots.append((row, col, self.state[row][col]))
                if move == (1,0):
                    spots = reversed(spots) # you have to reverse the list so the numbers go down in the correct order
            else:
                for row in range(len(self.state)):
                    for col in range(len(self.state)):
                        if self.state[col][row] != 0:
                            spots.append((col, row, self.state[col][row]))
                if move == (0,1):
                    spots = reversed(spots)  # you have to reverse the list so the numbers go right in the correct order

            #For every spot move
            for ele in spots:
                row = ele[0] + move[0]
                col = ele[1] + move[1]
                if row < 4 and col < 4 and row > -1 and col > -1:
                    if ele[2] == self.state[row][col] or self.state[row][col] == 0:
                        if ele[2] == self.state[row][col]:
                            self.points += ele[2] * 2
                        self.state[row][col] = self.state[row][col] + ele[2]
                        self.state[row - move[0]][col - move[1]] = 0
                        Change = True
        self.newNum(1)
        #self.printBoard()
        #self.lastState.printBoard()

    def undoMove(self):
        if (self.lastState == None):
            return self
        else:
            return self.lastState

    def printBoard(self):
        print(" ")
        print(*self.state, sep = "\n")

    def validMoves(self):
        moves = [[(0, 1),0,0], [(0, -1),0,0], [(1, 0),0,0], [(-1, 0),0,0]]
        for rows in range(len(self.state)):
            for col in range(len(self.state)): #add one if a move if possible
                if (col + 1 < 4):
                    if ((self.state[rows][col] == self.state[rows][col + 1] and self.state[rows][col] != 0) or (self.state[rows][col + 1] == 0 and self.state[rows][col] != 0)):
                        moves[0][1] = moves[0][1] + 1
                if (col - 1 > -1):
                    if ((self.state[rows][col] == self.state[rows][col - 1] and self.state[rows][col] != 0) or (self.state[rows][col - 1] == 0 and self.state[rows][col] != 0)):
                        moves[1][1] = moves[1][1] + 1
                if (col + 1 < 4):
                    if ((self.state[col][rows] == self.state[col + 1][rows] and self.state[col][rows] != 0) or (self.state[col + 1][rows] == 0 and self.state[col][rows] != 0)):
                        moves[2][1] = moves[2][1] + 1
                if (col - 1 > -1):
                    if ((self.state[col][rows] == self.state[col - 1][rows] and self.state[col][rows] != 0) or (self.state[col - 1][rows] == 0 and self.state[col][rows] != 0)):
                        moves[3][1] = moves[3][1] + 1    
            for x in range(4): 
                if moves[x][1] != 0:
                    moves[x][2] = 1
                moves[x][1] = 0
        
        returnMe = []
        for x in moves:
            if (x[2] == 1):
                returnMe.append(x[0])
        return returnMe
    
    def getPoints(self):
        #returnMe = self.points
        #return returnMe
        return self.points
        
    def makeCopy(self, b):
        tempBoard = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
        if b != None:
            for x in range(4):
                for y in range(4):
                    tempBoard[x][y] = self.state[x][y]
        return tempBoard

#if __name__ == '__main__':
    #b = board([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]], None, 0)
    #b.newNum(2)
    #b.printBoard()
    #b.makeMove((1,0))
    #b.printBoard()
    #print(b.validMoves())
    #print(b.getPoints())
    #(1,0) is down
    #(-1, 0) is up
    #(0,1) is right