#include <stdio.h>

/*
Creed Jones
Summary:
Pointer tests
*/

int main() {
    double number1 = 7.3, number2; 
    char s1[100] = "Hello!", s2[100]; 

    double* dPtr; 
    *dPtr = number1; 
    printf("%d", dPtr);
    *dPtr = number2;
    printf("\n%d", number2); //Since there is no value it prints the adress
    printf("\n%d", &number1);
    printf("\n%d", dPtr);
    strcpy(s2, s1);
    printf("\n%s", s1 == s2);
    printf("\n%d", strlen(s1));
    double* ddPtr = dPtr;
    printf("\n%f", *ddPtr);
    number1 = *ddPtr;
    *ddPtr = number2;
    dPtr = ddPtr;
}