#include "../include/apue.h"
#include <sys/wait.h>


int main(int argc, char *argv[]){
    pid_t  pid;
    if((pid = fork()) < 0){
        err_sys("fork error");
    }else if(pid == 0){
        if((pid = fork()) < 0){
            err_sys("fork error");
        }else if( pid > 0){
            printf("first child, process id is: %ld \n", (long)getppid());
            exit(0);
        }
        //note in linux 3.2, the result will be upstart demean
        //which is a replacement for System-V init
        sleep(2);
        printf("second child, parent pid = %ld \n", (long)getppid());
        exit(0);
    }
    
    if(waitpid(pid, NULL, 0) != pid){
        err_sys("waitpid error");
    } 
    
    exit(0); 

}
