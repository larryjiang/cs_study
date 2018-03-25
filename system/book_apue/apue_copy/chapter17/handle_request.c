#include "opend.h"
#include <fcntl.h>

void handle_request(char *buf, int nread, int fd, uid_t uid){
    int newfd;
    if(buf[nread - 1] != 0){
        log_sys("inside buf error nread - 1! = 0");
        snprintf(errmsg,MAXLINE - 1, "request not null terminated: %*.*s\n",nread,nread,buf);
        send_err(fd,-1,errmsg);
        return;
    };
    
    log_msg("request: %s, from uid %d", buf, uid);
    if(buf_args(buf,cli_args) < 0){
        log_sys("insided buf_args error");
        send_err(fd,-1,errmsg);
        log_msg(errmsg);
        return;
    };
    
    if((newfd = open(pathname,oflag)) < 0){
        snprintf(errmsg,MAXLINE - 1,"can not open %s: %s\n",pathname,strerror(errno));
        send_err(fd,-1,errmsg);
        log_msg(errmsg);
        return;
    };
    
    if(send_fd(fd,newfd) < 0){
        err_sys("send_fd error");
    };
    log_msg("sent fd %d over fd %d for %s", newfd, fd, pathname);
    
    close(newfd);
};
