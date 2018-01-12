#include "../include/apue.h"
#include <pwd.h>
#include <stddef.h>
#include <string.h>

struct passwd * getpwnam(const char *name){
    struct passwd *ptr;
    setpwent();
    while((ptr = getpwent()) != NULL){
        if(strcmp(name, ptr->pw_name) == 0){
            break;
        }
    }
    endpwent();
    return (ptr);

}


int main(int argc, char * argv[]){
    if(argc != 2){
        printf("usage: please provide one and only one name");
        err_quit("usage: one parameter! ");
    }
    
    struct passwd * ptr = getpwnam(argv[1]);
    
    //printf("%s \n", ptr->pw_class);
    printf("%s \n", ptr->pw_dir);
    printf("%s \n", ptr->pw_shell);
}
