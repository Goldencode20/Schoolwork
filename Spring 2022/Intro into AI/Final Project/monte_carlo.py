import board
import random, copy
import sys
import time
from datetime import timedelta

ITERATIONS = 10
TRIALS = 10
EVAL_METHOD = 0
MOVES = [(0, 1), (0, -1), (1, 0), (-1, 0)]

#runs a game picking random moves from those that are valid
def random_run(game):
    #creates a copy of the current board
    game_copy = copy.deepcopy(game)
    #assumes if there are valid moves the game is not over
    while len(game_copy.validMoves()) != 0:
        #selects a random move and makes it
        move = random.choice(game_copy.validMoves())
        game_copy.makeMove(move)
    #returns the sum of all elements and max number achieved
    return game_copy.get_sum(), game_copy.max_num()

def monte_carlo_iter(game):
    total_score = [0, 0, 0, 0]

    #for each possible move on the current board runs multiple random games using random_run to see what kind of 
    #scores are produced for each potential move
    #creates a new copy of the valid moves based on the current board
    valid_moves = copy.deepcopy(game.validMoves())
    #print(game.validMoves())
    #looks at all 4 potential moves and determines if that are in the valid moves, if not, skips calculations
    for move in MOVES:
        print(valid_moves)
        if move in valid_moves:
            game_copy = copy.deepcopy(game)
            game_copy.makeMove(move)
        else:
            continue

        #runs ITERATIONS number of games using the random_run and keeps track of the sum or largest element numbers in each run in a list
        #depending on what method you tell it track
        for i in range(ITERATIONS):
            output = random_run(game_copy)
            
            #eval method 0: Best total sum
            if EVAL_METHOD == 0:
                total_score[MOVES.index(move)] += output[0]

            #eval method 1: Largest tile number
            elif EVAL_METHOD == 1:
                total_score[MOVES.index(move)] += output[1]
    #gets the index number of the best score, then tells the program to select that move
    best_move = total_score.index(max(total_score))
    game.makeMove(MOVES[best_move])
    #printing for troubleshooting purposes
    game.printBoard()

#creates a new board and plays the game until there are no longer valid moves to make
def monte_carlo_run():
    #creates a blank board
    game = board.board([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]], None, 0)
    #adds the first elements to the board
    game.newNum(2)
    i = 0
    game.printBoard()
    #plays the game until there's no longer a valid move to make
    while len(game.validMoves()) != 0:
        print("Iteration: ", i)
        monte_carlo_iter(game)
        i += 1

    #prints max values to the terminal and returns the max element value and sum of all element values
    print("Max Square Value: {}".format(game.max_num()))
    print("Total Square Sum: {}".format(game.get_sum()))
    return game.max_num(), game.get_sum()

#initiates the program and prints the metrics
def main():
       
    max_val_results = [0] * TRIALS
    total_sum_results = [0] * TRIALS
    
    start_time = time.time()
    for i in range(TRIALS):
        max_val_results[i], total_sum_results[i] = monte_carlo_run()
    end_time = time.time()
        
    total_sum_avg = sum(total_sum_results) / TRIALS
    max_val_avg = sum(max_val_results) / TRIALS

    f = open("monte_carlo_" + str(ITERATIONS) + "_" + str(TRIALS) + "_" + str(EVAL_METHOD) + ".txt", "w")
    f.write("avg max val: " + str(max_val_avg) + "\n") 
    f.write("avg total sum: " + str(total_sum_avg) + "\n")
    f.write("max vals: " + str(max_val_results) + "\n") 
    f.write("total sums: " + str(total_sum_results) + "\n")
    f.close()

    print("total sum avg: " + str(total_sum_avg))
    print("max val avg: " + str(max_val_avg))
    print()
    print("time taken: ", str(timedelta(seconds=(end_time - start_time))))



if __name__ == '__main__':
    main()