import java.util.Random;
public class WPawn extends Piece {
    public boolean en_passant;
    public WPawn() {
        super("white", 'p', 1);
        en_passant = false;
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }

    public IndexPair force_move(Square[][] board, int x, int y, IndexPair move) {
        IndexPair indices = new IndexPair();
        int start_x, start_y;
        start_x = x;
        start_y = y;
        if (valid(x - 1, y - 1) && x - 1 == move.x && y - 1 == move.y && 
            board[x - 1][y - 1].piece.colour.compareTo("black") == 0) {
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (valid(x - 1, y + 1) && x - 1 == move.x && y + 1 == move.y &&
            board[x - 1][y + 1].piece.colour.compareTo("black") == 0) {
            indices.x = x - 1;
            indices.y = y + 1;
        } else if (valid(x - 1, y) && x - 1 == move.x && y == move.y && board[x - 1][y].piece.type == 'x') {
            indices.x = x - 1;
            indices.y = y;
        }
        if (indices.x != -1 && indices.y != -1) {
            board[move.x][move.y] = board[start_x][start_y];
            board[start_x][start_y] = new Square(start_x, start_y);
            System.out.println("move " + (char) (start_y + 97) + (8 - start_x) +
                    (char) (move.y + 97) + (8 - move.x));
            System.out.flush();
        }
        return indices;
    }

    public IndexPair move_forward(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        en_passant = false;
        if (x == 6 && board[x - 1][y].piece.type == 'x' && board[x - 2][y].piece.type == 'x') {
            indices.x = x - 2;
            indices.y = y;
            en_passant = true;
        } else if (board[x - 1][y].piece.type == 'x') {
            indices.x = x - 1;
            indices.y = y;
        } else {
            indices.x = -1;
            indices.y = -1;
        }
        return indices;
    }

    public boolean check_enpassant(Square square) {
        if(square.piece instanceof BPawn) {
            BPawn pawn = (BPawn) square.piece;
            if(pawn.en_passant) {
                return true;
            }
        }
        return false;
    }

    public IndexPair calculate_move(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        if (x == 0) {
            indices.x = -1;
            indices.y = -1;
            return indices;
        }
        if (y == 0) {
            if (board[x - 1][y + 1].piece.colour.compareTo("black") == 0) {
                indices.x = x - 1;
                indices.y = y + 1;
            } else if (check_enpassant(board[x][y + 1])) {
                board[x][y + 1] = new Square(x, y + 1);
                indices.x = x - 1;
                indices.y = y + 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (y == 7) {
            if (board[x - 1][y - 1].piece.colour.compareTo("black") == 0) {
                indices.x = x - 1;
                indices.y = y - 1;
            } else if (check_enpassant(board[x][y - 1])) {
                board[x][y - 1] = new Square(x, y - 1);
                indices.x = x - 1;
                indices.y = y - 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (board[x - 1][y - 1].piece.colour.compareTo("black") == 0 && board[x - 1][y + 1].piece.colour.compareTo("black") == 0) {
            indices.x = x - 1;
            if (board[x - 1][y + 1].piece.score > board[x - 1][y - 1].piece.score) {
                indices.y = y + 1;
            } else {
                indices.y = y - 1;
            }
        } else if (board[x - 1][y - 1].piece.colour.compareTo("black") == 0 ) {
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (board[x - 1][y + 1].piece.colour.compareTo("black") == 0) {
            indices.x = x - 1;
            indices.y = y + 1;
        } else if (check_enpassant(board[x][y - 1])) {
            board[x][y - 1] = new Square(x, y - 1);
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (check_enpassant(board[x][y + 1])) {
            board[x][y + 1] = new Square(x, y + 1);
            indices.x = x - 1;
            indices.y = y + 1;
        } else {
            indices = move_forward(board, x, y);
        }
        return indices;
    }

    Piece random_Promotion () {
        Random rand = new Random();
        int nr = rand.nextInt(4);
        if ( nr == 0 ) {
            return new WQueen();
        }
        if (nr == 1) {
            return new WRook();
        }
        if (nr == 2) {
            return new WKnight();
        }
        return new WBishop();
	}

    public IndexPair move(Square[][] board, int x, int y) {
        IndexPair indices;
        indices = calculate_move(board, x, y);
        if (indices.x != -1) {
            if (indices.x != 0) {
                board[indices.x][indices.y] = board[x][y];
            } else {
                board[indices.x][indices.y] = new Square(indices.x, indices.y, random_Promotion());
            }
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.y + 97) + (8 - indices.x));
        }
        System.out.flush();
        return indices;
    }
}
