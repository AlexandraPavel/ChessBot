import java.util.ArrayList;

public abstract class Piece {
    public String colour;
    public char type;
    public int score;

    public Piece(String colour, char type, int score) {
        this.colour = colour;
        this.type = type;
        this.score = score;
    }

    public Piece() {
        this("default",'x', 0);
    }

    public abstract IndexPair move(Square[][] board, int x, int y);

}
