#include "../include/apue.h"
#include <errno.h> 

void make_temp(char *template);


int main(int argc, char * argv[]){
    char good_template[] = "/tmp/dirXXXXXX";
    char *bad_template = "/tmp/dirXXXXXX";
    
    printf("trying to create first tmp file...\n");
    make_temp(good_template);
    printf("trying to create second tmp file...\n");
    make_temp(bad_template);
    exit(0);
}
 
void make_temp(char *template){
    int fd;
    struct stat sbuf;
    
    if((fd = mkstemp(template)) < 0){
        err_sys("can't create tmp file");
    };
    
    printf("tmp name = %s\n", template);
    close(fd);

    if(stat(template, &sbuf) < 0 ){
        if(errno == ENOENT){
            printf("file doesn't exist \n");
        }else{
            err_sys("stat failed");
        }
    }else{
        printf("file exists");
        unlink(template);
    };
}
