#include <stdio.h>


/*
Creed Jones
Summary:
Pointer calculator
*/

int add(int* num1, int* num2) {
    *num1 = *num1 + *num2;
}

int multiply(int* num1, int* num2) {
    *num1 = *num1 * *num2;
}

int subtract(int* num1, int* num2) {
    *num1 =  *num1 - *num2;
}

int divide(int* num1, int* num2) {
    *num1 =  *num1 / *num2;
}

int main() {
    char op;
    int num1, num2;
    while (1 == 1) {
        printf("\nEnter the operation you want to perform('+' for Addition,'-' for Subtraction,'*' for Multiplication, '/' for Division, Q to quit): " );
        scanf(" %c", &op);
        if (op == 'Q') {
            break;
        }
        printf("Enter the operands for this operation: ");
        scanf(" %d, %d", &num1, &num2);
        printf("%d, %d", num1, num2);

        int* num1Ptr = &num1;
        int* num2Ptr = &num2;

        if (op == '+') {
            add(num1Ptr, num2Ptr);
            printf("\n%d", num1);
        } else if (op == '-') {
            subtract(num1Ptr, num2Ptr);
            printf("\n%d", num1);
        } else if (op == '/') {
            divide(num1Ptr, num2Ptr);
            printf("\n%d", num1);
        } else {
            multiply(num1Ptr, num2Ptr);
            printf("\n%d", num1);
        }
    }
    printf("Goodbye!");
}