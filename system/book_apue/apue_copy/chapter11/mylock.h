struct mylock {
   int f_count;
   pthread_mutex_t f_lock;
   int f_id;
};

struct mylock * mylock_alloc(int);
void mylock_hold(struct mylock *);
void mylock_release(struct mylock *);
