#! /bin/sh 
gcc -o client client.c csopenv2.o  ../lib/error.o  cliconnect.o recv_fd.o

gcc -o servermainv2 servermainv2.c loop.o ../lib/error.o ../lib/daemonize.o  handle_request.o serv_listen.o cli_args.o send_err.o buf_args.o send_fd clientmgr.o  serv_accept.o ../lib/errorlog.o

