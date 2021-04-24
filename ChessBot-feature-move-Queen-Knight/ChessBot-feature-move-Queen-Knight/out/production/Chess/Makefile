JFLAGS = -g
JC = javac
# result=java ChessBoard 
build:
	javac *.java

default: classes

run: Test.java
	java Test

play:
	xboard -debug -fcp "make run"
	
clean:
	$(RM) *.class
	#rm *.txt
	rm *.debug