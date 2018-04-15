#include "../include/lib/unp.h"
void udpg_cli(FILE *fp, int sockfd, SA*pservaddr, socklen_t servlen){
    int n;
    char sendline[MAXLINE], recvline[MAXLINE+1]; 
    while(Fgets(sendline, MAXLINE, fp) != NULL){
        Sendto(sockfd, sendline, strlen(sendline), 0, pservaddr, servlen);
        n = Recvfrom(sockfd, recvline, MAXLINE, 0, NULL,NULL);
        recvline[n] = 0;
        Fputs(recvline, stdout);
    };

};
