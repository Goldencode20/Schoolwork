import random
import copy

class board:

    def __init__(self, state, lastState):
        self.state = state
        self.lastState = lastState
        self.points = 0

    def newNum(self, times):
        for loop in range(times):
            spots = []
            for row in range(len(self.state)):
                for col in range(len(self.state)):
                    if self.state[row][col] == 0:
                        spots.append((row, col))
            spot = random.choice(spots)
            randNum = random.randint(0,9)
            if randNum == 0:
                self.state[spot[0]][spot[1]] = 4
            else:
                self.state[spot[0]][spot[1]] = 2

    def makeMove(self, move): #Takes a tuple in
        #Set the last state
        self.lastState = board(copy.deepcopy(self.state), self.lastState)
        #We need to move the get all spots up here but in order to do that we need in the while loop update all of the spots
        
        Change = True
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
                        self.state[row][col] = self.state[row][col] + ele[2]
                        self.state[row - move[0]][col - move[1]] = 0
                        Change = True
        self.newNum(1)

    def undoMove(self):
        if (self.lastState == None):
            return self
        else:
            return self.lastState

    def printBoard(self):
        print(" ")
        print(*self.state, sep = "\n")

    def validMmoves(self):
        moves = [[(0, 1),0], [(0, -1),0], [(1, 0),0], [(-1, 0),0]]
        for rows in range(len(self.state)):
            for col in range(len(self.state)):
                if (col + 1 < 4):
                    if (self.state[rows][col] == self.state[rows][col + 1] or self.state[rows][col + 1] == 0):
                        moves[0][1] = moves[0][1] + 1
                if (col - 1 > -1):
                    if (self.state[rows][col] == self.state[rows][col - 1] or self.state[rows][col - 1] == 0):
                        moves[1][1] = moves[1][1] + 1
                if (col + 1 < 4):
                    if (self.state[col][rows] == self.state[col + 1][rows] or self.state[col + 1][rows] == 0):
                        moves[2][1] = moves[2][1] + 1
                if (col - 1 > -1):
                    if (self.state[col][rows] == self.state[col - 1][rows] or self.state[col - 1][rows] == 0):
                        moves[3][1] = moves[3][1] + 1    
            for x in range(4):
                if moves[x][1] == 0 or moves[x][1] > 4:
                    moves[x][1] = 5
                else:
                    moves[x][1] = 0
        
        returnMe = []
        for x in moves:
            if not(x[1] > 0):
                returnMe.append(x[0])
        return returnMe
        


if __name__ == '__main__':
        b = board([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]], None)
        #b.newNum(2)
        #b.printBoard()
        #b.makeMove((1,0))
        b.printBoard()
        print(b.validMmoves())
    #(1,0) is down
    #(-1, 0) is up
    #(0,1) is right
    #(0, -1) ir left