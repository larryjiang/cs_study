#include "../include/apue.h"

#include <sys/wait.h>


char * env_init[] = {"USER=unknown","PATH=/tmp",NULL};


int main(int argc, char * argv[]){
    pid_t pid;
   
    
     
    TELL_WAIT();  
    if((pid = fork()) < 0){
        err_sys("fork error");
    }else if(pid == 0){
        setbuf(stdout,NULL);
        if(execle("/home/yan/codebase/cs_study/system/book_apue/apue_copy/chapter8/echoall", "echo-all","myarg1","MY_ARG2",
            (char *)0,env_init) < 0){
            err_sys("execle error");
        };
    };
    
    if(waitpid(pid,NULL, 0) < 0){
        err_sys("wait error");
    } 
    
       
    if((pid = fork()) < 0){
        err_sys("fork error");
    }else if(pid == 0){
        WAIT_PARENT();
        sleep(1); 
        if(execlp("echoall","echoall","only 1 arg",(char *)0) < 0){
            err_sys("execlp error");
        }
    }
    TELL_CHILD(pid); 
    exit(0);
}

