#include<stdio.h>



int main() {
    int one, two, three, four, five, six;
    for(int i = 0; i < 60000000; i++) {
        int roll = rand() % 6;
        if (roll == 1){
            one++;
        } else if (roll == 2) {
            two++;
        } else if (roll == 3) {
            three++;
        } else if (roll == 4) {
            four++;
        } else if (roll == 5) {
            five++;
        } else {
            six++;
        }
    }
    printf("1 = %d\n", one);
    printf("2 = %d\n", two);
    printf("3 = %d\n", three);
    printf("4 = %d\n", four);
    printf("5 = %d\n", five);
    printf("6 = %d\n", six);
}