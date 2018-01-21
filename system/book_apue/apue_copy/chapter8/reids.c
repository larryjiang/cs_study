#include "../include/apue.h"
int main(int argc, char * argv[]){
    printf("readl uid = %d, effective uid = %d\n",getuid(),geteuid());
    exit(0);
}
