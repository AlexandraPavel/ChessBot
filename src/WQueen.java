import java.util.ArrayList;

public class WQueen extends Piece {
    public WQueen() {
        super("white", 'q', 9);
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }
    public IndexPair force_move(Square[][] board, int x, int y, IndexPair move) {
        IndexPair indices = new IndexPair();
        int modify_x, modify_y, start_x, start_y;
        start_x = x;
        start_y = y;
        modify_x = move.x - x;
        modify_y = move.y - y;
        if (modify_x > 0)
            modify_x = 1;
        else if (modify_x == 0)
            modify_x = 0;
        else
            modify_x = -1;

        if (modify_y >= 0)
            modify_y = 1;
        else if (modify_y == 0)
            modify_y = 0;
        else
            modify_y = -1;
        while (valid(x, y) && board[x][y].piece.colour.equals("default") && board[x][y].piece.type == 'x') {
            x += modify_x;
            y += modify_y;
        }
        if (valid(x, y) && board[x][y].piece.colour.equals("black") && x == move.x && y == move.y) {
            indices.x = start_x;
            indices.y = start_y;
            // vedem
        }
        return indices;
    }

    public IndexPair verify_best_take(Square[][] board, int x, int y, int best_score, int modify_x, int modify_y) {
        IndexPair indices = new IndexPair();
        while (valid(x, y) && board[x][y].piece.colour.equals("default") && board[x][y].piece.type == 'x') {
            x += modify_x;
            y += modify_y;
        }
        if (valid(x, y) && board[x][y].piece.colour.equals("black") && board[x][y].piece.type != 'x' && best_score < board[x][y].piece.score) {
            indices.x = x;
            indices.y = y;
        }
        return indices;
    }

    public IndexPair get_best_take(Square[][] board, int x, int y) {
        IndexPair indices;
        IndexPair aux =  new IndexPair();
        int best_score = 0;
        indices = verify_best_take(board, x - 1, y, best_score, -1, 0);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x - 1, y - 1, best_score, -1, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x - 1, y + 1, best_score, -1, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x, y + 1, best_score, 0, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x, y - 1, best_score, 0, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x + 1, y - 1, best_score, 1, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x + 1, y, best_score, 1, 0);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        indices = verify_best_take(board, x + 1, y + 1, best_score, 1, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
            best_score = board[indices.x][indices.y].piece.score;
        }
        if (best_score == 0) {
            aux.x = -1;
            aux.y = -1;
        }
        return aux;
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
        if (verify_move(board, x - 1, y )) {
            indices.x = x - 1;
            indices.y = y;
        } else if (verify_move(board, x - 1, y + 1)) {
            indices.x = x - 1;
            indices.y = y + 1;
        } else if (verify_move(board, x - 1, y - 1)) {
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (verify_move(board, x , y - 1)) {
            indices.x = x;
            indices.y = y - 1;
        } else if (verify_move(board, x, y + 1)) {
            indices.x = x;
            indices.y = y + 1;
        } else if (verify_move(board, x + 1, y - 1)) {
            indices.x = x + 1;
            indices.y = y - 1;
        } else if (verify_move(board, x + 1, y)) {
            indices.x = x + 1;
            indices.y = y;
        } else if (verify_move(board, x + 1, y + 1)) {
            indices.x = x + 1;
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
