DEPS=fargeskrift.h

.PHONY: all clean install

.SUFFIXES:


all: fargedemo fargetest oppg3

fargedemo: fargedemo.c fargeskrift.c fargeskrift.h
	gcc -O2 -o fargedemo fargedemo.c fargeskrift.c

fargetest: fargetest.c fargeskrift.c fargeskrift.h
	gcc -O2 -o fargetest fargetest.c fargeskrift.c

oppg3: oppg3.c fargeskrift.c fargeskrift.h
	clang oppg3 oppg3.c fargeskrift.c

clean:
	rm -f *.o fargedemo fargetest

install: fargedemo fargetest
	cp fargedemo fargetest /usr/local/bin/


