#include "../include/lib/unp.h"
extern void str_cli_correct(FILE*,int);
int main(int argc, char ** argv){
    int sockfd; 
    struct sockaddr_in servaddr;
    
    if(argc != 2){
        err_quit("usage: echocli <IPaddress>");
    };
    
    sockfd = Socket(AF_INET,SOCK_STREAM,0);
    bzero(&servaddr, sizeof(servaddr));
    
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(SERV_PORT); 
    Inet_pton(AF_INET, argv[1], &servaddr.sin_addr);
    
    Connect(sockfd,(SA*)&servaddr, sizeof(servaddr));
    str_cli_correct(stdin,sockfd);
    exit(0);
};
