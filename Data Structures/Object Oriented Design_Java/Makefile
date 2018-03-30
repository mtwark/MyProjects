JAVASRC    = ChessBoard.java Chesspiece.java Queen.java King.java Knight.java Rook.java Pawn.java Bishop.java LinkedList.java Node.java Board.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessBoard
CLASSES    = ChessBoard.class Chesspiece.class Queen.class King.class Knight.class Rook.class Pawn.class Bishop.class LinkedList.class Node.class Board.class
JARFILE    = ChessMoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)