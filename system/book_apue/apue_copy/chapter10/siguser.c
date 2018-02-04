#include "../include/apue.h"

##please note to make this example a fore ground process and send signal from another terminal from linux to make the demo work
static void sig_usr(int);

int main(void){
    setbuf(stdout,NULL);
    int so;
    if(signal(SIGUSR1, sig_usr) == SIG_ERR){
        err_sys("can't catch SIGUSR1");
    }

    if(signal(SIGUSR2, sig_usr) == SIG_ERR){
        err_sys("can't catch SIGUSR2");
    };
    
    for( ; ;){
        sleep(10);;
    };

};


static void sig_usr(int signo){
    //signal(SIGUSR1,sig_usr);
    //signal(SIGUSR2,sig_usr);
    if(signo == SIGUSR1){
        printf("received SIGUSR1 \n");
    }else if(signo == SIGUSR2){
        printf("received SIGUSR2 \n");
    }else{
        err_dump("received signal %d \n", signo);
    };

}
