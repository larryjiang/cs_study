#include <stdlib.h>
#include <pthread.h>
#include "mylock.h"


struct mylock * mylock_alloc(int id){
    struct mylock *fp;
    if((fp = malloc(sizeof(struct mylock))) != NULL){
       fp->f_count = 1;
       fp->f_id = id;
       if(pthread_mutex_init(&fp->f_lock, NULL) != 0){
            free(fp);                 
            return (NULL);
       }; 
    };
    
    return (fp);

};


void mylock_hold(struct mylock *fp){
    pthread_mutex_lock(&fp->f_lock);
    fp->f_count++;
    pthread_mutex_unlock(&fp->f_lock);
};


void mylock_release(struct mylock *fp){
    pthread_mutex_lock(&fp->f_lock);
    if(--fp->f_count == 0){
        pthread_mutex_unlock(&fp->f_lock);
        pthread_mutex_destroy(&fp->f_lock);
        free(fp);
    }else{
        pthread_mutex_unlock(&fp->f_lock);
    }
};
