#include "../include/lib/unp.h" 

int udp_client(const char *host, const char *serv, SA** saptr, socklen_t *lenp){
    int sockfd, n;
    struct addrinfo hints, *res, *ressave;
    bzero(&hints, sizeof(struct addrinfo));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_DGRAM;
    
    if((n = getaddrinfo(host, serv, &hints, &res)) != 0){
        err_quit("udp client error for %s, %s:%s",host, serv, gai_strerror(0));
    };
    
    ressave = res;
    
    do{
        sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);
        if(sockfd >= 0){
            break;
        };
    }while((res = res->ai_next) != NULL);

    if(res == NULL){
        err_sys("udp client error for %s, %s", host, serv);
    };
    
    *saptr = Malloc(res->ai_addrlen);
    memcpy(*saptr, res->ai_addr,res->ai_addrlen);
    *lenp = res->ai_addrlen;
    freeaddrinfo(ressave);
    return (sockfd);
};
