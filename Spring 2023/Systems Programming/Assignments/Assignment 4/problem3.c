#include <stdio.h>


/*
Creed Jones
Summary:
Pointer Function calculator
*/

int add(int num1, int num2) {
    return num1 + num2;
}

int subtract(int num1, int num2) {
    return num1 - num2;
}

int performOperation(int (*funPtr)(), int num1, int num2) {
    int returMe = funPtr(num1, num2);
    return returMe;
}

int main() {
    int result1 = performOperation(&add, 5, 3); 
    int result2 = performOperation(&subtract, 5, 3); 
    printf("Result 1: %d\n", result1); 
    printf("Result 2: %d\n", result2); 
}