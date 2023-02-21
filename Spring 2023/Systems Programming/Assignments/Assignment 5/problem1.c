#include <stdio.h>

int compare1 (const int* a, const int*b) {
    //Implemnt compare method here so that it returns 1 if a > b, -1 if a < b and 0 otherwise.
    if (a > b) {
        return 1;
    } else if (b > a) {
        return -1;
    } else {
        return 0;
    }
}

int compare2 (const int* a, const int* b) {
    //Implemnt compare method here so that it returns -1 if a > b, 1 if a < b and 0 otherwise.
    if (a > b) {
        return -1;
    } else if (b > a) {
        return 1;
    } else {
        return 0;
    }
}

void sort(int* arr, int size, int (*cmp)(const int*, const int*)) {
    //implement the sorting algorithm here
    int current, j;
    for (int i = 1; i < size; i++) {
        current = arr[i];
        j = i - 1;
        while (j >= 0 && 1 == cmp(arr[j], current)) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = current; 
    }

}

int main() {
    int arr[] = {3,5,1,4,2};
    int size = sizeof(arr) / sizeof(arr[0]);
    int i;

    printf("Using compare 1: \n");
    sort(&arr, size, compare1);
    for (i = 0; i < size; i++) {
        printf("%d", arr[i]);
    }
    printf("\n");

    printf("Using compare 2: \n");
    sort(&arr, size, compare2);
    for (i = 0; i < size; i++) {
        printf("%d", arr[i]);
    }
    printf("\n");

    return 0;
}