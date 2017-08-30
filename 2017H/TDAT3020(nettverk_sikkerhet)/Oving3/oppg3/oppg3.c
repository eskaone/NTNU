#include <stdio.h>
#include "fargeskrift.h"

const int max = 7;

//Demonstrerer ANSI-farger
void oppg3() {
	
int i, j, space;
    for(i=5; i>=1; --i) {
        for(space=0; space < 5-i; ++space)
            printf("  ");

        for(j=i; j <= 2*i-1; ++j)
            farge_printf(i, 0, "* ");

        for(j=0; j < i-1; ++j)
            farge_printf(i, 0, "* ");

        printf("\n");
    }
}

int main(int argc, char *argv[]) {
	oppg3();
}
