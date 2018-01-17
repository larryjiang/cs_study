#include "../include/apue.h"
#include <setjmp.h>

#define TOK_ADD 5
jmp_buf jmpbuffer;

void do_line(char *);
void cmd_add();
int get_token();

int main(int argc, char *argv[]){
    char line[MAXLINE];
    
    if(setjmp(jmpbuffer) < 0){
        printf("error");
    }
    
    while(fgets(line, MAXLINE, stdin) != NULL){
        do_line(line);

    }
    exit(0);

}

char *tok_ptr;

void do_line(char *ptr){
    int cmd;

    tok_ptr = ptr;
    while((cmd = get_token()) > 0){
        switch(cmd){
            case TOK_ADD:
                cmd_add();
                break;
        }
    }
}

int  get_token(){
    while(tok_ptr && *tok_ptr == ' '){
        tok_ptr++;
    }
    
    int i = 0;
    char * oldptr = tok_ptr;
    while(tok_ptr && *tok_ptr != ' '){
        i++;
        tok_ptr++;
    }
    
    
    if(tok_ptr){
        *tok_ptr ='\0';
        tok_ptr++; 
    }
    
    if(i ==1 && *oldptr == '+'){
        return 5;
    }
    
    int ret = atoi(oldptr); 
    return ret;
}

void cmd_add(){
    int o1 = get_token();
    int o2 = get_token();
    if(o1 == 0 || o2 == 0){
        printf("when atoi gives 0, we jump out of current stack");
        longjmp(jmpbuffer, 1);
    }
    printf("\ncommand add yields %d \n", o1 + o2);    
}
