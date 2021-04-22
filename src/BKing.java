import java.util.ArrayList;

public class BKing extends Piece {
    public boolean moved;
    public boolean in_check;
    public int checked;

    public BKing() {
        super("black", 'k', Integer.MAX_VALUE);
        moved = false;
        in_check = false;
        checked = 0;
    }

    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
}
