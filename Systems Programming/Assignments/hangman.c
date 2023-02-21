#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int SIZE = 50;
/*
Creed Jones
Summary:
This is a simple program to help with diverites and integrals taking input from the user.
What I still need to do:
Write a function that is the main game
Find out who goes first and return that value
A fucntion that tells if the game is over or not.
*/

int compareArray (char a1[], char a2[]) {
    for(int i = 0; i <= SIZE; i++) {
        if (a1[i] != a2[i]) {
            return 0;
        }
    }
    return 1;
}

//Have the players guess their word
int wordGuess (char word[], char playerName[]) { //Update incorrect guesses, tell incorrect guess, and lastly end at the right time
    char guessSoFar[SIZE]; 
    int temp = 0;
    int incorrecctGuesses = 0; 
    int letterCount = 0;
    int hasLetters = 0;
    while(1 == 1){
        if(*(word + temp) != '\0') {
            letterCount++;
        } else {
            break;
        }
        temp++;
    }
    incorrecctGuesses = 0;

    do { //This might be working
        hasLetters = 1;
        char guess;
        int correct = 0;
        printf("\n%s, enter a letter: ", playerName);
        scanf(" %c", &guess);
        for(int i = 0; i < letterCount; i++) {
            if(*(word + i) == guess || *(word + i) == guess - 32 || *(word + i) == guess + 32) {
                guessSoFar[i] = *(word+i);
                correct = 1;
            }
        }
        if (correct == 0) {
            incorrecctGuesses++;
        }
        for (int i = 0; i < letterCount; i++) {
                if (*(word + i) == guessSoFar[i]) {
                    printf("%c ", guessSoFar[i]);
                } else {
                    printf("_ ");
                    hasLetters = 0;
                }
        }
        printf("\nIncorrect Guesses : %d", incorrecctGuesses);
    } while (!(hasLetters == 1 || incorrecctGuesses >= 6));
    if (incorrecctGuesses == 6) {
        return 0;
    } else {
        return 1;
    }
}

//Have the players pick their word
void runGame(char p1Name[], char p2Name[], int first) { 
    int win1 = 0;
    int win2 = 0;
    int turns = 0;
    do {
        if(first == 1) {
            printf("\n%s, please enter your secret word: ", p2Name);
        } else {
            printf("\n%s, please enter your secret word: ", p1Name);
        }
        char word[SIZE];
        scanf("%s", &word);
        for(int i = 0; i < 400; i++) {
            printf("\n");
        }
        if(first == 1) {
            win1 += wordGuess(word, p1Name);
            first = 2;
        } else {
            win2 += wordGuess(word, p2Name);
            first = 1;
        }
        turns++;
    } while ((win1 == win2 || (win1 == 0 && win2 == 0)) || (turns % 2 != 0)); //flip turns needs to work!!!
    
    printf("\n%s: %d", p1Name, win1);
    printf("\n%s: %d", p2Name, win2);
    if(win1 > win2) {
        printf("\n%s WINS!", p1Name);
    } else {
        printf("\n%s WINS!", p2Name);
    }
}

int main () {
    //Set up players
    char player1Name[SIZE];
    char player2Name[SIZE];
    printf("Enter player 1 name: ");
    scanf("%s", player1Name);
    printf("\nEnter player 2 name: ");
    scanf("%s", player2Name);
    //Flip the coin and pick who goes first
    srand(time(NULL)); 
    char coin[10];
    int first = 0;
    int coinFlip = rand() % 2;
    if (coinFlip == 0) {
        coin[0] = 'H';
        coin[1] = 'e';
        coin[2] = 'a';
        coin[3] = 'd';
        coin[4] = 's';
        first = 1;
    } else {
        coin[0] = 'T';
        coin[1] = 'a';
        coin[2] = 'i';
        coin[3] = 'l';
        coin[4] = 's';
        first = 2;
    }
    printf("Coin toss to decide who goes first. . . and its ");
    for (int i = 0; i < 6; i++) {
        if(coin[i] != '\0' || coin[i] != ' ') {
            printf("%c", coin[i]);
        }
    }
    printf(". ");
    if (first == 1) {
        printf("%s goes first.", player1Name);
    } else {
        printf("%s goes first.", player2Name);
    }
    runGame(player1Name, player2Name, first);
}