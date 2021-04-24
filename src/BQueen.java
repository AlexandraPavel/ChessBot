import java.util.ArrayList;

public class BQueen extends Piece {
    public BQueen() {
        super("black", 'q', 9);
    }

	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		return new IndexPair();
	}

    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
}
