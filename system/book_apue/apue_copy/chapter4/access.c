#include "../include/apue.h"
#include <fcntl.h>
int main(int argc, char * argv[]){
    if(argc != 2){
        err_quit("usage: a.out <pathname>");
    }
    
    if(access(argv[1], R_OK)){
        err_ret("access error for %s", argv[1]);
    }else{
        printf("read access OK\n");
    }
    printf("Real uid: %d\n",getuid());
    printf("Effective uid: %d\n", geteuid());
    char data[128];
    int fd = open(argv[1], O_RDONLY);
    if(fd < 0){
        err_ret("open error for %s", argv[1]);
    }else{
        printf("open for reading OK\n");
    }
    
        
    int c = read(fd, data, 127);
    data[127] = 0;
    printf("%d \n", c);
    printf("%s\n", data);
    exit(0);
}


