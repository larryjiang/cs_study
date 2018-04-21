#include "../include/lib/unp.h"
#include <time.h>

int main(int argc, char **argv){
    int listenfd, connfd;
    socklen_t len;
  
    char buf[MAXLINE];
    
    time_t ticks;
    struct sockaddr_storage cliaddr;
    struct sockaddr_in my_addr;    
    if(argc != 2){
        err_quit("usage: daytimetcpserv1 <service or port#>");
    };
    
    listenfd = tcp_listen(NULL,argv[1], NULL);
    
    for(;;){
        len = sizeof(cliaddr);
        connfd = Accept(listenfd, (SA *)&cliaddr,&len);
        printf("connection from %s\n", Sock_ntop((SA *)&cliaddr, len));
        bzero(&my_addr, sizeof(my_addr));
        int mylen = sizeof(my_addr);
        char ip[16];
        unsigned int port;
        Getsockname(connfd, (SA *)&my_addr, &mylen);
        Inet_ntop(AF_INET, &my_addr.sin_addr,ip, sizeof(ip));
        port = ntohs(my_addr.sin_port);
        printf("ip: %s:%u",ip, port);
        fflush(stdout);
        ticks = time(NULL);
        snprintf(buf, sizeof(buf), "%s.24s\r\n", ctime(&ticks));
        Write(connfd, buf, strlen(buf));
        Close(connfd);
    };


};
