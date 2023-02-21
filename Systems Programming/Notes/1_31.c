#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 20 //This is to have final values

void count() {
    srand(time(NULL));
    int c[SIZE];
    int one, two, three, four, five;
    for(int i = 0; i < SIZE; i++) {
        c[i] = rand() % 5;
        printf("\n%d", c[i]);
    }
    for(int i = 0; i < SIZE; i++) {
        switch (c[i]) {
            case 0:
                one++;
                break;
            case 1: 
                two++;
                break;
            case 2:
                three++;
                break;
            case 3:
                four++;
                break;
            case 4:
                five++;
                break;
        }
    }
    printf("\n 1 = %d\n 2 = %d\n 3 = %d\n 4 = %d\n 5 = %d", one, two, three, four, five);
}

int main () {
    srand(time(NULL));
    int c[SIZE]; //type name[elements]
    //use a for loop to print the array
    /*
    for(int i = 0; i < SIZE; i++) {
        c[i] = rand() % 50;
        printf("\n%d", c[i]);
    }
    int sum = 0; 
    for(int i = 0; i < SIZE; i++) {
        sum += c[i];
    }
    printf("\n%d", sum);
    */
   count();
}