#include <stdio.h>

void ex1 () {
    int i = 10;
    char c = 'f';
    double d = 10.10;

    int *iPtr;
    char *cPtr;
    double *dPtr;

    iPtr = &i;
    cPtr = &c;
    dPtr = &d;

    printf("\nValue of iPtr is %d", iPtr);
    printf("\nValue of cPtr is %c", cPtr);
    printf("\nValue of dPtr is %d", dPtr);

    printf("\nValue of *iPtr is %d", *iPtr);
    printf("\nValue of *cPtr is %c", *cPtr);
    printf("\nValue of *dPtr is %d", *dPtr);

    printf("\nSize of i is %d", sizeof(i));
    printf("\nSize of i is %d", sizeof(c));
    printf("\nSize of i is %d", sizeof(d));

    printf("\nSize of iPtr is %d", sizeof(iPtr));
    printf("\nSize of iPtr is %d", sizeof(cPtr));
    printf("\nSize of iPtr is %d", sizeof(dPtr));
}

void ex2() {
    int i1 = 1;
    int i2 = 2;

    int *i1Ptr, *i2Ptr;

    i1Ptr = &i1;
    i2Ptr = &i2;

    
}

void main () {
    int y =5;

    int *yPtr;

    yPtr = &y;

    printf("\nValue of y is %d", y);

    printf("\nValue of yPtr is %d", yPtr);

    printf("\nValue of *yPtr is %d", *yPtr);

    *yPtr = 15;

    printf("\nValue of y is %d", y);

    //ex1();

    ex2();
}