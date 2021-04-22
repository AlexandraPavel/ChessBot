import java.util.ArrayList;

public class WKnight extends Piece {
    public WKnight() {
        super("white", 'n', 3);
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }

    public boolean verify_best_take(Square[][] board, int x, int y, int best_score) {
        if (valid(x, y) && board[x][y].piece.colour.equals("black") && board[x][y].piece.type != 'x' && best_score < board[x][y].piece.score) {
            return true;
        }
        return false;
    }

    public IndexPair get_best_take(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        int best_score = 0;
        if (verify_best_take(board, x - 2, y - 1, best_score)) {
            indices.x = x - 2;
            indices.y = y - 1;
            best_score = board[x - 2][y -1].piece.score;
        }
        if (verify_best_take(board, x - 2, y + 1, best_score)) {
            indices.x = x - 2;
            indices.y = y + 1;
            best_score = board[x - 2][y + 1].piece.score;
        }
        if (verify_best_take(board, x - 1, y + 2, best_score)) {
            indices.x = x - 1;
            indices.y = y + 2;
            best_score = board[x - 1][y + 2].piece.score;
        }
        if (verify_best_take(board, x + 1, y + 2, best_score)) {
            indices.x = x + 1;
            indices.y = y + 2;
            best_score = board[x + 1][y + 2].piece.score;
        }
        if (verify_best_take(board, x + 2, y + 1, best_score)) {
            indices.x = x + 2;
            indices.y = y + 1;
            best_score = board[x + 2][y + 1].piece.score;
        }
        if (verify_best_take(board, x + 2, y - 1, best_score)) {
            indices.x = x + 2;
            indices.y = y - 1;
            best_score = board[x + 2][y - 1].piece.score;
        }
        if (verify_best_take(board, x + 1, y - 2, best_score)) {
            indices.x = x + 1;
            indices.y = y - 2;
            best_score = board[x + 1][y - 2].piece.score;
        }
        if (verify_best_take(board, x - 1, y - 2, best_score)) {
            indices.x = x - 1;
            indices.y = y - 2;
            best_score = board[x - 1][y - 2].piece.score;
        }
        if (best_score == 0) {
            indices.x = -1;
            indices.y = -1;
        }
        return indices;
    }
    boolean verify_move(Square[][] board, int x, int y) {
        if(valid(x, y) && board[x][y].piece.colour.equals("default") && board[x][y].piece.type == 'x')
            return true;
        return false;
    }

    public IndexPair calculate_move(Square[][] board, int x, int y) {
        IndexPair indices = get_best_take(board, x, y);
        if (indices.x != -1 && indices.y != -1) {
            return indices;
        }
        if (verify_move(board, x - 2, y - 1)) {
            indices.x = x - 2;
            indices.y = y - 1;
        } else if (verify_move(board, x - 2, y + 1)) {
            indices.x = x - 2;
            indices.y = y + 1;
        } else if (verify_move(board, x - 1, y - 2)) {
            indices.x = x - 1;
            indices.y = y - 2;
        } else if (verify_move(board, x - 1, y + 2)) {
            indices.x = x - 1;
            indices.y = y + 2;
        } else if (verify_move(board, x + 1, y + 2)) {
            indices.x = x + 1;
            indices.y = y + 2;
        } else if (verify_move(board, x + 1, y - 2)) {
            indices.x = x + 1;
            indices.y = y - 2;
        } else if (verify_move(board, x + 2, y - 1)) {
            indices.x = x + 2;
            indices.y = y - 1;
        } else if (verify_move(board, x + 2, y + 1)) {
            indices.x = x + 2;
            indices.y = y + 1;
        }
        return indices;
    }

    public IndexPair move(Square[][] board, int x, int y) {
        IndexPair indices;
        indices = calculate_move(board, x, y);
        if (indices.x != -1) {
            board[indices.x][indices.y] = board[x][y];
            board[x][y] = new Square(x, y);
        }
        System.out.flush();
        return indices;
    }
}
