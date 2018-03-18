#include "../include/apue.h"
#include <netdb.h>
#include <errno.h>
#include <syslog.h>
#include <sys/socket.h>


#define BUFLEN 128
#define QLEN 10


#ifndef HOST_NAME_MAX
#define HOST_NAME_MAX 256
#endif
extern int serv_accept(int sockfd, uid_t *uidptr);
extern  int serv_listen(const char *);
void serve(int sockfd){
    int clfd;
    FILE *fp;
    char buf[BUFLEN];
    uid_t uidptr;    
 
    set_cloexec(sockfd);
    for(;;){
        if((clfd = serv_accept(sockfd,&uidptr)) < 0){
           syslog(LOG_ERR,"ruptimed: serv_accept error : %d", errno); 
           exit(1);
        };
        set_cloexec(clfd);
        if((fp = popen("/usr/bin/uptime","r")) == NULL){
            sprintf(buf, "error: %d\n", errno);
            send(clfd, buf, strlen(buf),0);
        }else{
            while(fgets(buf, BUFLEN, fp) != NULL){
                send(clfd, buf, strlen(buf),0);
            };
            pclose(fp);

        };
        close(clfd);
    };

};



int main(int argc, char *argv[]){
    int fd;
    if(argc != 2){
        err_sys("usage: this pathname\n");
        exit(1);
    };
    
    daemonize("udsserver");

    if((fd = serv_listen(argv[1])) < 0){
        err_sys("server binding error\n");
    };
         
    serve(fd); 
    exit(0);

};
