#include <stdlib.h>
#include <pthread.h>
#define NHASH 29
#define HASH(id) (((unsigned long)id)%NHASH)


struct mylock  * fh[NHASH];
pthread_mutex_t hashlock = PTHREAD_MUTEX_INITIALIZER;

struct mylock {
    int f_count;
    pthread_mutex_t f_lock;
    int f_id;
    struct mylock *f_next;
};

struct mylock * mylock_alloc(int id){
    struct mylock *fp;
    int idx;
    
    if((fp = malloc(sizeof (struct mylock))) != NULL){
        fp->f_count = 1;
        fp->f_id = id;
        if(pthread_mutex_init(&fp->f_lock,NULL) != 0){
            free(fp);
            return (NULL);
        };
        idx = HASH(id);
        pthread_mutex_lock(&hashlock);
        fp->f_next = fh[idx];
        fh[idx] = fp;
        pthread_mutex_lock(&fp->f_lock);
        pthread_mutex_unlock(&hashlock);
        pthread_mutex_unlock(&fp->f_lock);
        
    };
    
    return (fp);
};


void mylock_hold(struct mylock *fp){
    pthread_mutex_lock(&fp->f_lock);
    fp->f_count++;
    pthread_mutex_unlock(&fp->f_lock);
};

struct mylock * mylock_find(int id){
    struct mylock *fp;
    pthread_mutex_lock(&hashlock);
    for(fp = fh[HASH(id)]; fp != NULL; fp = fp->f_next){
        if(fp->f_id == id){
            mylock_hold(fp);
        };
    };

    pthread_mutex_unlock(&hashlock);
    return (fp);

};

void  mylock_release(struct mylock * fp){
    struct mylock *tfp;
    int idx;
    pthread_mutex_lock(&fp->f_lock);
    if(fp->f_count == 1){
         pthread_mutex_unlock(&fp->f_lock);
         pthread_mutex_lock(&hashlock);
         pthread_mutex_lock(&fp->f_lock);
         
    };
};


