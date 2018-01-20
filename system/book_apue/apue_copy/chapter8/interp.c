#include "../include/apue.h"

int main(int argc, char * argv[]){
    TELL_WAIT();
    pid_t pid;   
    if((pid = fork()) < 0){
        err_sys("fork error");
    }else if(pid == 0){
        printf("child doing its job and sleep 1 seconds\n");
        sleep(1);

        TELL_PARENT(getppid());
        WAIT_PARENT();
        
        printf("finish waiting parentnt\n");
        exit(0);
    }
    printf("parent doing its job and sleep 3 seconds\n"); 
    TELL_PARENT(pid); 
    WAIT_CHILD();

    printf("finish waiting child\n");
    exit(0);
}
