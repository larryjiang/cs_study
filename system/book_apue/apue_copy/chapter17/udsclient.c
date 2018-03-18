#include "../include/apue.h"
#include <netdb.h>
#include <errno.h>
#include <sys/socket.h>


#define BUFLEN 128
#define TIMEOUT 30
extern int cli_conn(const char *name);

void print_uptime(int sockfd, struct addrinfo *aip){
    int n;
    char buf[BUFLEN];
    
    buf[0] = 0;
    if(sendto(sockfd, buf, 1, 0, aip->ai_addr, aip->ai_addrlen) < 0){
        err_sys("sendto error");
    };
    alarm(TIMEOUT);
    if((n = recvfrom(sockfd, buf, BUFLEN, 0, NULL, NULL)) < 0){
        if(errno != EINTR){
            alarm(0);
        };
        err_sys("recv error");
       
    };
    alarm(0);
    write(STDOUT_FILENO, buf, n);
};



int main(int argc, char * argv[]){
   int fd;
   char buf[BUFLEN]; 
   if(argc != 2){
        err_sys("usage: command arg1");
        exit(0);
   };          
   
   if((fd = cli_conn(argv[1])) < 0){
        err_sys("connect error");
   }; 
    
   send(fd,buf,1,0);
   
   recv(fd,buf,BUFLEN,0);
   write(STDOUT_FILENO,buf,strlen(buf));
};
