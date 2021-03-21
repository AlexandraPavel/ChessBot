JFLAGS = -g
JC = javac
# result=java ChessBoard 
build:
	javac *.java

default: classes

run: ChessBoard.java
	java ChessBoard

play:
	xboard -debug -fcp "make run"
	
clean:
	$(RM) *.class
	#rm *.txt
	rm *.debug

