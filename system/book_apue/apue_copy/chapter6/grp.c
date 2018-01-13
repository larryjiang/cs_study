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
    
    char ** m = grp->gr_mem;
    
   //I did not understand the pointer well so I was not able to write this correctly from
   //the first place. 
    while(*m){
        printf("member name is %s \n", *m);
        printf("value of m is %p \n", m);
        m++;
    }
    

}
