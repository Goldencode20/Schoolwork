#include <stdio.h>
#include <stdbool.h>
#include <math.h>

/*
Creed Jones
Summary:
This is a simple program to help with diverites and integrals taking input from the user.
Bugs:
Not sure how to deal with the exact decimal places will get that fixed
*/

int main () {
    bool run = true;
    while (run) { //We want this to run while until the user says stop
        int a, b, c;
        int div_or_int;

	printf("Enter a, b, and c:");
	scanf("%d %d %d", &a, &b, &c);

        printf("\nThe equation you entered was %dx^2 + %dx + %d", a, b, c);

        printf("\n(1) Derivative");
        printf("\n(2) Integral\n");
	printf("Select Option: ");
        scanf("%d", &div_or_int);

        switch (div_or_int) {
            int x;

	        case 1: ;
		        int div;
                a *= 2;
                printf("The derivative is %dx + %d", a, b);
                printf("\nEnter x: ");
                scanf("%i", &x);
                div = (a * x) + b;
                printf("The derivative at x = %d is %d", x, div);
                break;

            case 2: ;
                int integral;
                a /= 3;
                b /= 2;
                printf("The integral is %dx^3 + %dx^2 + %dx", a, b, c);
                printf("\nEnter x: ");
                scanf("%d", &x);
                integral = (a * pow(x, 3)) + (b * pow(x, 2)) + (c * x);
                printf("The integral at x = %d is %d", x, integral);
                break;

            default:
                printf("Invalid input"); //Needs a default case
		break;
        }

        char temp;
        printf("\nDo you want to repeat? y/n :");
        scanf(" %c", &temp);
        if (temp != 121) { //I trust the grader to enter the right one
            run = false;
	    printf("\nGood bye!");
        }
    }
}