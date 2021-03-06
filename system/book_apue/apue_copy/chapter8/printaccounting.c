#include "../include/apue.h"
#include <sys/acct.h>


#if defined(LINUX)
#define acct acct_v3
#endif



#if defined(HAS_AC_STAT)
#define FMT "%-*.*s e = %6ld, chars = %7ld, stat = %3u: %c %c %c %c\n"
#else
#define FMT "%-*.*s e = %6ld, chars = %7ld, %c %c %c %c\n"
#endif

#if !defined(HAS_ACORE)
#define ACORE 0
#endif

#if !defined(HAS_AXSIG)
#define HAS_AXSIG 0
#endif

#if !defined(BSD)
static unsigned long compt2ulong(comp_t comptime){
    unsigned long val;
    int exp;
    
    val = comptime & 0x1fff;
    exp = (comptime >> 13) & 7;
    while(exp--){
        val *= 8;
    }
    
    return (val);
}
#endif

int main(int argc, char * argv[]){
    struct acct acdata;
    FILE *fp;
   
    if(argc != 2){
        err_quit("usage:  pracct filename");
    } 
    
    if((fp = fopen(argv[1], "r"))  == NULL){
        err_sys("can't open %s", argv[1]);
    }
    
    while(fread(&acdata, sizeof(acdata), 1, fp) == 1){
        //some error happened here and I don't know why, maybe system specific.
        printf(FMT, (int)sizeof(acdata.ac_comm) > 17 ? 16 :  (int)sizeof(acdata.ac_comm)  ,(int)sizeof(acdata.ac_comm) > 17 ?16 :  (int)sizeof(acdata.ac_comm) ,acdata.ac_comm,compt2ulong(acdata.ac_etime), compt2ulong(acdata.ac_io),
#if defined(HAS_AC_STAT)
        (unsigned char) acdata.ac_stat,
#endif
        acdata.ac_flag & ACORE ? 'D' : ' ',
        acdata.ac_flag & AXSIG ? 'X' : ' ',
        acdata.ac_flag & AFORK ? 'F' : ' ',
        acdata.ac_flag & ASU ? 'S' : ' ');
    }
    
    if(ferror(fp)){
        err_sys("read error");
    }

    exit(0);
}
