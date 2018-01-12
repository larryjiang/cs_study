#include "../include/apue.h"
#include <grp.h>

struct group * getgrp(const char * name){
    return getgrnam(name);
}

int main(int argc, char * argv[]){
    if(argc != 2){
        err_quit("usage: one parameter! \n");
    }
    
    struct group * grp = getgrp(argv[1]);
    printf("Group id is %d \n", grp->gr_gid);
    
    char ** member = grp->gr_mem;
    
    for(char * m = *member; m < *member + 4; m++){
        printf("member name is %s \n", m);
    }

}
