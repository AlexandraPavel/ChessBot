public class Default extends Piece{
    public Default() {
        super();
    }

    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }

    public IndexPair force_move(Square[][] board, int x, int y, IndexPair move) {
        return new IndexPair();
    }
}
