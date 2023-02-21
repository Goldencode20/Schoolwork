#include <stdio.h>
#include <stdlib.h>

int main() {
    //Set up 2d array
    int row, col; 
    printf("Enter the number of rows: ");
	scanf("%d", &row);
    printf("\nEnter the number of collumns: ");
    scanf("%d", &col);
    printf("\nEnter the elements of the array: ");
    int*arr = (int*)calloc(row * col, sizeof(int));

    //Take in user numbers
    printf("\n");
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            scanf("%d", &arr[(i * col) + j]);
            //printf("%d ", arr[i * j]);
        }
    }

    //process the array
    int* arr2 =  (int*)calloc(row * col, sizeof(int));

    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            arr2[(i * col) + j] = arr[(i * col) + j] * arr[(i * col) + j];
        }
    }

    //print the arrays
    printf("Original Array: \n");
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++){
            printf("%d ", arr[(i * col) + j]);
        }
        printf("\n");
    }

    printf("Processed Array: \n");
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++){
            printf("%d ", arr2[(i * col) + j]);
        }
        printf("\n");
    }

    free(arr);
    free(arr2);
}