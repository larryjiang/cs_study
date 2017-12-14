#include "../include/apue.h"
#include <sys/wait.h>

static void sig_int(int);


int main(void){
    char buf[MAXLINE];
    pid_t pid;
    int status;

    
    printf("current pid is: %ld \n", (long)getpid());    
    if(signal(SIGINT, sig_int) == SIG_ERR){
       err_sys("register signal handler error");
    }; 
    
    printf("%% ");
   
    while(fgets(buf, MAXLINE, stdin) != NULL){
        int l = strlen(buf);
        if(buf[l-1] == '\n'){
            buf[l-1] = 0;
        };
        

        if((pid = fork()) < 0){
            err_sys("error creating process!");
        }else if(pid == 0){
            execlp(buf,buf,(char *)0);
            err_ret("could not execute %s \n", buf);
            exit(127);
        }

        if((pid = waitpid(pid, &status, 0)) < 0){
            err_sys("wait pid error");
        }

        printf("%% ");
    }
    
    exit(0);
};


void sig_int(int signal){
    printf("Interrupt happened \n %% ");
};
