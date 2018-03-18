#include "../include/apue.h"
#include <sys/socket.h>


#define CONTROLLEN CMSG_LEN(sizeof(int));

static struct cmsghdr *cmptr = NULL;

int send_fd(int fd, int fd_to_send){
    struct iovec iov[1];










};
