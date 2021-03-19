JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $*.java
CLASSES = \
	./src/ChessBoard.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	cd ./out/production/Chess/ && \
	java ChessBoard

clean:
	$(RM) *.class
