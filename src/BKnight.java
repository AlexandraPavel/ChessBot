import java.util.ArrayList;

public class BKnight extends Piece {
    public BKnight() {
        super("black", 'n', 3);
    }

	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		return new IndexPair();
	}


    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
}
