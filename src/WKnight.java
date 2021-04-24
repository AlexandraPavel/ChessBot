import java.util.ArrayList;

public class WKnight extends Piece {
    public WKnight() {
        super("white", 'n', 3);
    }

	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		return new IndexPair();
	}

    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
}
