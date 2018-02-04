#include "../include/apue.h"
#include <pwd.h>

static void my_alarm(int signo){
    struct passwd *rootptr;
    signal(SIGALRM,my_alarm);
    printf("in signal handler \n");
    if((rootptr = getpwnam("root")) == NULL){
        err_sys("getpwnam(root) error");
    }
    alarm(1);
    printf("after alarm");
};

int main(void){
    struct passwd *ptr;
    setbuf(stdout,NULL);
    signal(SIGALRM, my_alarm);
    
    alarm(1);
    for(;;){
        if((ptr = getpwnam("yan")) == NULL){
            err_sys("getpwnam error");
        };
//        printf("I am looping");
        if(strcmp(ptr->pw_name, "yan") != 0){
            printf("return value corrupted!, pw_name = %s\n", ptr->pw_name);
        };
    };

}
