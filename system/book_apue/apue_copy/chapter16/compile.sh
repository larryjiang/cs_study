#! bash

gcc -o ruptimev2 ruptimev2.c ../lib/error.o ../lib/daemonize.o initserver.o ../lib/setfd.o

