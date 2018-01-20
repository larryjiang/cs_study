#include "../include/apue.h"
#include <sys/wait.h>
static void characteratime(char *);
int main(int argc, char *argv[]){
    pid_t pid;
    TELL_WAIT(); 
    int status;
    if((pid = fork()) < 0){
        err_sys("fork error");
    }else if(pid == 0){
        WAIT_PARENT(); 
        characteratime("qwertyuiopasdfghjklzxcvbnm\n");
    }else{
        characteratime("QWERTYUIOPASDFGHJKLZXCVBNM\n");
        TELL_CHILD(pid);
     }    
    
    // make sure all the printing is done before next shell prompt
    wait(&status); 
    exit(0);
}


static void characteratime(char *str){
    char *ptr;
    int c;
    
    
    setbuf(stdout, NULL);
    for(ptr = str; (c = *ptr); ptr++){
        //cause more switches between different processes
        sleep(0);
        putc(c, stdout);
    };
}
