JFLAGS = -g
JC = javac
# result=java ChessBoard 
.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $*.java
CLASSES = \
	./src/ChessBoard.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	cd ./src/ && \
	java ChessBoard

play:
	xboard -debug -fcp "make run" -scp "fairymax"  -secondInitString "new\nrandom\nsd 2\n" -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false 
	
clean:
	$(RM) *.class


