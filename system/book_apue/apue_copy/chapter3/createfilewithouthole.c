#include "../include/apue.h"
#include <fcntl.h>

char buf1[] = "abcdefghi";
char buf2[] = "ABCDEFGHI";


int main(int argc, char * argv[]){
    int fd;
    
    if((fd = creat("file.withouthole", FILE_MODE)) < 0){
        err_sys("creat error");
    }
    
    if(write(fd, buf1, 10) != 10){
        err_sys("buf1 write error");
    };
    
    char buf3 [16384];
    for(int i = 0; i < 16384; i++){
        buf3[i] = 'a';
    };     
    if(write(fd, buf3,16384) != 16384){
        err_sys("did not write enough byte!");
    };    
    
    if(write(fd, buf2, 10) != 10){
        err_sys("buf2 write error");
    };
    
    exit(0);
}
