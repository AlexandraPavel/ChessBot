import java.util.ArrayList;

public class WPawn extends Piece {
    public WPawn() {
        super("white", 'p', 1);
    }

	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		return new IndexPair();
	}

    public IndexPair move_forward(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        if (x == 6 && board[x - 1][y].piece.type == 'x' && board[x - 2][y].piece.type == 'x') {
            indices.x = x - 2;
            indices.y = y;
        } else if (board[x - 1][y].piece.type == 'x') {
            indices.x = x - 1;
            indices.y = y;
        } else {
            indices.x = -1;
            indices.y = -1;
        }
        return indices;
    }

    public IndexPair calculate_move(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        if (x == 0) {
            indices.x = -1;
            indices.y = -1;
            return indices;
        }
        if (y == 0) {
            if (board[x - 1][y + 1].piece.type != 'x') {
                indices.x = x - 1;
                indices.y = y + 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (y == 7) {
            if (board[x - 1][y - 1].piece.type != 'x') {
                indices.x = x - 1;
                indices.y = y - 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (board[x - 1][y - 1].piece.type != 'x' && board[x - 1][y + 1].piece.type != 'x') {
            indices.x = x - 1;
            if (board[x - 1][y + 1].piece.score > board[x - 1][y - 1].piece.score) {
                indices.y = y + 1;
            } else {
                indices.y = y - 1;
            }
        } else if (board[x - 1][y - 1].piece.type != 'x') {
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (board[x - 1][y + 1].piece.type != 'x') {
            indices.x = x - 1;
            indices.y = y + 1;
        } else {
            indices = move_forward(board, x, y);
        }
        return indices;
    }

    public IndexPair move(Square[][] board, int x, int y) {
        IndexPair indices;
        indices = calculate_move(board, x, y);
        if (indices.x == -1) {
            System.out.println("resign");
        } else {
            if (indices.x != 0) {
                board[indices.x][indices.y] = board[x][y];
            } else {
                board[indices.x][indices.y] = new Square(indices.x, indices.y, new WQueen());
            }
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.y + 97) + (8 - indices.x));
        }
        System.out.flush();
        return indices;
    }
}
