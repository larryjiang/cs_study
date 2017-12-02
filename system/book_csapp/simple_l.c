long int gval1 = 567;
long int gval2 = 763;


long int simple_l(long int *xp, long int y){
    long int t = *xp + y;
    *xp = t;
    return t;

};

long int call_simple_l(){
    long int z = simple_l(&gval1, 12L);
    return z + gval2;
}



