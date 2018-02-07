#include "../include/apue.h"
#include <pthread.h>
#include "mylock.h"
/**
*Using no argument to demonstrate race condition, using one to demonstrate synchronization
*/
int global = 0;
void * thr_fn1(void *arg){
   struct mylock * lock = (struct mylock *)arg; 
   mylock_hold(lock);
   pthread_mutex_lock(&(lock->f_lock));
   for(int i = 0; i < 10000000; i++){
        global++;
   };   
   pthread_mutex_unlock(&(lock->f_lock));
   mylock_release(lock); 
   return NULL;
};


void * thr_fn2(void *arg){
   struct mylock * lock = (struct mylock *)arg; 
   mylock_hold(lock);
   for(int i = 0; i < 10000000; i++){
        global++;
   };   
   mylock_release(lock); 
   return NULL;
};

void * (*thr_fn)(void *);
int main(int argc, char *argv[]){
    int err;
    if(argc == 2){
        thr_fn = thr_fn1;
    }else{
        thr_fn = thr_fn2;
    }
    pthread_t tid1, tid2;
    void *tret;
    struct mylock * lock = mylock_alloc(1);
    err = pthread_create(&tid1, NULL,thr_fn, (void *) lock);
    if(err != 0){
        err_exit(err, "can not create thread 1");
    }

    err = pthread_create(&tid2, NULL, thr_fn, (void *) lock);
    if(err != 0){
        err_exit(err, "can not create thread 22");
    }
    
    err = pthread_join(tid1,&tret);
    if(err != 0){
        err_exit(err,"can not join with thread 1");
    };
    
    err = pthread_join(tid2, &tret); 
    if(err != 0){
        err_exit(err,"can not join with thread 2");
    };
    printf("expected is %d\n", 2000000);
    printf("Final value of the global is: %d\n", global); 
    exit(0); 
     
    








};
