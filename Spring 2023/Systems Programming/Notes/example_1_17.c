#include <stdio.h>

int main ()
{

	char c;
	printf("insert letter grade");
	scanf("%c", &c);

	switch (c) {
		case ('A'):
			printf("A = 4");
			break;
		case ('B'):
			printf("B = 3");
			break;
		case ('C'):
			printf("C = 2");
			break;
		case ('D'):
			printf("D = 1");
			break;
		case ('F'):
			printf("F = 0");
			break;
		default:
			printf("Invalid input");
			break;
	}
	/*
	char c;
	printf("Insert a letter\n");
	scanf("%c", &c);
	
	if((c >= 65 && c <= 90) || (c >= 97 && c >= 122)) {
		if (c == 65 || c == 69 || c == 73 || c == 79 || c == 85 || c == 97 || c == 101 || c == 105 || c == 111 || c == 117) {
			printf ("%c is a vowel.", &c);
		} else {
			printf ("%c is a consonant.", &c);
		{
	} else {
		printf("invalid input");
	}



	char c;
	scanf("%c", &c):
	#we are using %c because this is a char

	if (c >= 'a' && c <= 'z') {
		printf("its a lower case character");	
	}

	char charA = 'a';
	char charA + 1;
	char charZ = 'z';

	int x = charA;
	int z = charZ;

	printf(:the value of charA is %c\n", c);
	printf("The value of x is %d\n', x);
	printf("the vale of z is %d\n", z);


	int month;
	printf ("Enter the month in mm format: ");
	scanf (%i", &month);
	if (month == 1) {
		printf("Jan")
	} else if (month == 2){
		printf("Feb")
	} else if (month == 3) {
		printf("Mar")
	} else if (month == 4) {
		printf("Apr")
	} else if (month == 5) {
		printf("May")
	} else if (month == 6) {
		printf("Jun")
	} else if (month == 7) {
		printf("Jul")
	} else if (month == 8) {
		printf("Aug")
	} else if (month == 9) {
		printf("Sep")
	} else if (month == 10) {
		printf("Nov")
	} else if (month == 11) {
		printf("Oct")
	} else if (month == 12) {
		printf("Dec")
	} else {
		printf("Invalid");
	}
	*/