#include <stdio.h>

void swapPtr(int **Ptr1, int **Ptr2) {
    int* tempPtr = *Ptr2;
    *Ptr2 = *Ptr1;
    *Ptr1 = *tempPtr;
}

void sumOfArray(int *arrayPtr) {
    int sum = 0;
    sum += *arrayPtr;
    arrayPtr++;
}

void exersise2() {
    int twoD[5][5]= { {1, 2, 3, 4, 5}, {2, 3, 4, 5, 6}, {3, 4, 5, 6, 7}, {4, 5, 6, 7, 8}, {5, 6, 7, 8, 9}};


}

int main() {

}