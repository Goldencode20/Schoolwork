#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
Creed Jones
Summary:
Simple Higher Lower game with up to 2 players and 3 difficulties
Need to do still:
I need to get a random seed every time.
*/

int getNumberOfPlayers () {
    printf("Welcome to Number Guessing Game by Creed Jones \n");
    int num, boolean;
    boolean = 1;
    do {
        printf("Please enter the number of players (1 or 2): ");
        scanf("%d", &num);
        if(num == 1 || num == 2) {
            boolean = 0;
        }
    } while (boolean == 1);
    return num;
}

int getDifficulty () {
    printf("\nPlease choose an option: \n\n Press 0 for instructions \n\n Press 1 for Easy difficulty (upto 10) \n\n Press 2 for Medium difficulty (upto 100) \n\n Press 3 for Hard difficulty (upto 1000) \n\n");
    int num, boolean;
    boolean = 1;
    do {
        printf("Please choose from 0, 1, 2, 3: ");
        scanf("%d", &num);
        if (num == 0) {
            printf("Choose a number then I will tell you if you are too high or two low!\n");
        }
        if(num == 1 || num == 2 || num == 3) {
            boolean = 0;
        }
    } while (boolean == 1);
    return num;
}

void player1game (int difficulty) {
    srand(time(NULL));
    int guessNum, playerNum, boolean, tries, top;
    boolean = 1;
    tries = 1;
    if(difficulty == 1) {
        printf("I am thinking of a number between 0 and 10\n"); //You haven't thought of it yet!
        guessNum = rand() % 11;
        top = 10;
    } else if (difficulty == 2) {
        printf("I am thinking of a number between 0 and 100\n"); 
        guessNum = rand() % 101;
        top = 100;
    } else if (difficulty == 3) {
        printf("I am thinking of a number between 0 and 1000\n"); 
        guessNum = rand() % 1001;
        top = 1000;
    } else {
        printf("HOW DID YOU GET HERE???"); //This should also be impossible to reach
        boolean = 0;
    }
    do {
        printf("\nPlease guess a number between 0 and %d: ", top);
        scanf("%d", &playerNum);
        if (playerNum == guessNum) {
            printf("Congrats you did it in %d tries", tries);
            boolean = 0;
        } else if (playerNum > guessNum) {
            printf("Your guess is too high");
        } else {
            printf("Your guess is too low");
        }
        tries++;
    } while (boolean == 1);
}

void player2game (int difficulty) {
    srand(time(NULL));
    int guessNum1, guessNum2, playerNum, booleanP1, booleanP2, triesP1, triesP2, top, playerTurn;
    booleanP1 = 1;
    booleanP2 = 1;
    triesP1 = 1;
    triesP2 = 1;
    if(difficulty == 1) {
        printf("I am thinking of a number between 0 and 10 for player 1\n"); 
        printf("I am thinking of a diffrent number between 0 and 10 for player 2\n"); 
        guessNum1 = rand() % 11;
        guessNum2 = rand() % 11;
        top = 10;
    } else if (difficulty == 2) {
        printf("I am thinking of a number between 0 and 100\n");
        printf("I am thinking of a diffrent number between 0 and 100 for player 2\n"); 
        guessNum1 = rand() % 101;
        guessNum2 = rand() % 101;
        top = 100;
    } else if (difficulty == 3) {
        printf("I am thinking of a number between 0 and 1000\n"); 
        printf("I am thinking of a diffrent number between 0 and 1000 for player 2\n"); 
        guessNum1 = rand() % 1001;
        guessNum2 = rand() % 1001;
        top = 1000;
    } else {
        printf("HOW DID YOU GET HERE???"); //This should also be impossible to reach
        booleanP1 = 0;
        booleanP2 = 0;
    }
    do {
        if(booleanP1 != 0){
            printf("\nPlayer 1 please guess a number between 0 and %d: ", top);
            scanf("%d", &playerNum);
            if (playerNum == guessNum1) {
                printf("Congrats you did it in %d tries", triesP1);
                booleanP1 = 0;
            } else if (playerNum > guessNum1) {
                printf("Your guess is too high");
            } else {
                printf("Your guess is too low");
            }
            triesP1++;
        }
        if (booleanP2 != 0) {
            printf("\nPlayer 2 please guess a number between 0 and %d: ", top);
            scanf("%d", &playerNum);
            if (playerNum == guessNum2) {
                printf("Congrats you did it in %d tries", triesP2);
                booleanP2 = 0;
            } else if (playerNum > guessNum2) {
                printf("Your guess is too high");
            } else {
                printf("Your guess is too low");
            }
            triesP2++;
        }
    } while (booleanP1 == 1 || booleanP2 == 1);
    if (triesP1 > triesP2) {
        printf("\nPlayer 2 WINS!!!");
    } else if (triesP2 > triesP1) {
        printf("\nPlayer 1 WINS!!!");
    } else {
        printf("\nIt is a tie!");
    }
}

void playGame (int numPlayers, int difficulty) {
    if (numPlayers == 1){
        player1game(difficulty);
    } else if (numPlayers == 2) {
        player2game(difficulty);
    } else {
        printf("HOW DID YOU GET HERE???"); //this should actually be impossible to reach 
    }
}

int main () {
    int numPlayers = getNumberOfPlayers();
    int difficulty = getDifficulty();
    playGame(numPlayers, difficulty);
}