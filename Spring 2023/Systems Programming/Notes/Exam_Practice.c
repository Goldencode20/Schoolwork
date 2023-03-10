#include <stdio.h>

//dynamic allocation

void dyal() {
    int rows = 3; int cols = 4;
    int *arr = (int *) malloc(rows * cols * sizeof(int));

    //arr[0][1] = 3; Syntax error
    arr[0] = 3; //Think of it as one array

    //refer to row i and col j? index = i * cols + j
    // { ----, ----, -----}
    //{0123, 4567, 891011}
    //

    //beter for index than plugging in formula into arr[]
    int i = 1, j =2;
    int index = i * cols + j;
    arr[index] = 5; //Five is a place holder for the item at that index



    int **arr2 = (int **) malloc(rows * sizeof(int*));  //set up an array that can point
    //arr2 -> {_, _, _, _} an array of pointers
    for( int i = 0; i < rows; i++) {
        arr2[0] = (int *) malloc( cols * sizeof(int)); // Set up an array at index 0 
    }
    arr2[1][2] = 4; //This now works because of the double mallock setup 
}

//Pointers

void pointers() {
    double dblVar; 
    double *dblPtr = &dblVar; //This points to the location of dblVar
    double* dblPtr1 = &dblVar; //Same as above
    printf("%p", dblPtr); //print the location of dblVar 
    printf("%p", &dblPtr); //print the location of dblPtr
    dblPtr++; //This moves the pointer up 1 byte or 4 bits. Great for looping through an array
    //Array of pointers is helpful to look at the arry without needing the arry.
    //Double Pointer is good when you want to presever or retain a change in the memory allocation or assignment outside a funtion call
}

//Char array

void charAr() {
    char sentence[10]  = {'h', 'e', 'l' , 'l', 'o', '\0'};

    char sentence1[10] = "hello";

    //The two above are the same
//temp

    int ar[10] = {1,2,3,4,5,6};
}

int question6(int num1, int num2) {
    if (num1/10 == num2/10 || num1/10 == num2 % 10) {
        return 1;
    } else if (num1 % 10 == num2 % 10 || num1 % 10 == num2/10) {
        return 1;
    }
    return 0;
}

int main() {
    int num = question6(24, 46);
    printf("%d\n", num);
    num = question6(24, 56);
    printf("%d\n", num);
}