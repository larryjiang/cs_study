#include "../include/apue.h"
#define BUFFSIZE 4096

int main(int argc, char * argv[]){
    int n;
    int para = 0;
    char *p;

    int size;
    if(argc == 2){
        para = atoi(argv[1]);
    }else if(argc > 2){
        err_sys("Usage: give one extra parameter at most");
    }
   
    if(para > 0){
        char buf[para];
        p = buf;
        size = para;
    }else{
        char buf[BUFFSIZE];
        p = buf;
        size = BUFFSIZE; 
    }

     while((n = read(STDIN_FILENO, p, size)) > 0){
        if(write(STDOUT_FILENO,p,n) != n){
            err_sys("write error");   
        };

     }

    if(n < 0){
        err_sys("read error");
    }
    
    exit(0); 
}
