#include "../include/lib/unp.h"

void str_len(int sockfd){
    ssize_t n;
    char buf[MAXLINE];
    
    again:
        while((n = read(sockfd, buf, MAXLINE)) > 0){
            Written(sockfd,buf,n);
        };
        
        if(n < 0 && errno == EINTR){
            goto again;
        }else if(n < 0){
            err_sys("str_echo: read error");
        };


};
