#include "../include/apue.h"

Sigfunc * signal(int signo, Sigfunc *func){
    struct sigaction act, oact;
    
    act.sa_handler  = func;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    if(signo == SIGALRM){
        #ifdef SA_INTERRUPT
            act.sa_flags |= SA_INTERRUPT,
        #endif
        
    }else{
        act.sa_flags |= SA_RESTART;
    }
    
    if(sigaction(signo, &act, &oact)){
        return (SIG_ERR);
     }
    
    return (oact.sa_handler);


}