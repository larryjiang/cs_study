#include "../include/lib/unp.h"


void str_cli_select(FILE *fp, int sockfd){
    int maxfdp1;
    fd_set rset;

    char sendline[MAXLINE], recvline[MAXLINE]; 
    
    FD_ZERO(&rset);
    for(;;){
        FD_SET(fileno(fp), &rset);
        FD_SET(sockfd, &rset);
        maxfdp1 = max(fileno(fp), sockfd) + 1;
        Select(maxfdp1,&rset,NULL,NULL,NULL);
        
        if(FD_ISSET(sockfd, &rset)){
            if(Readline(sockfd,recvline,MAXLINE) == 0){
                err_quit("str_cli: server terminated prematurely");
            };
            Fputs(recvline,stdout);
        };
        
        if(FD_ISSET(fileno(fp),&rset)){
            if(Fgets(sendline,MAXLINE,fp) == NULL){
                return;
            };
            Writen(sockfd,sendline,strlen(sendline));
        };

    };


};
