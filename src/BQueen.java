import java.util.ArrayList;

public class BQueen extends Piece {
    public BQueen() {
        super("black", 'q', 9);
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
        x += modify_x;
        y += modify_y;
        while (valid(x, y) &&  board[x][y].piece.type == 'x' &&  x != move.x && y != move.y) {
            x += modify_x;
            y += modify_y;
        }
        if (valid(x, y) && x == move.x && y == move.y) {
            indices.x = start_x;
            indices.y = start_y;
            board[move.x][move.y] = board[start_x][start_y];
            board[start_x][start_y] = new Square(start_x, start_y);
            System.out.println("move " + (char) (start_y + 97) + (8 - start_x) +
                    (char) (move.y + 97) + (8 - move.x));
            System.out.flush();
        }
        return indices;
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }

    public IndexPair verify_best_take(Square[][] board, int x, int y, int best_score, int modify_x, int modify_y) {
        IndexPair indices = new IndexPair();
        indices.x = -1;
        indices.y = -1;
        while (valid(x, y) && board[x][y].piece.colour.equals("default") && board[x][y].piece.type == 'x') {
            x += modify_x;
            y += modify_y;
        }
        if (valid(x, y) && board[x][y].piece.colour.equals("white") && board[x][y].piece.type != 'x' && best_score < board[x][y].piece.score) {
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
    
    public IndexPair verify_move(Square[][] board, int x, int y, int modify_x, int modify_y) {
        IndexPair indices = new IndexPair();
        int aux_x = x;
        int aux_y = y;
        x += modify_x;
        y += modify_y;
        while (valid(x, y) && board[x][y].piece.colour.equals("default") && board[x][y].piece.type == 'x') {
            x += modify_x;
            y += modify_y;
        }
        if (valid(x - modify_x, y - modify_y) && aux_x != x - modify_x && aux_y != y - modify_y) {
            x -= modify_x;
            y -= modify_y;
            indices.x = x;
            indices.y = y;
        } else if (valid(x - modify_x, y - modify_y) && aux_x == x - modify_x && aux_y == y - modify_y && valid(x, y) && board[x][y].piece.type == 'x') {
            indices.x = x;
            indices.y = y;
        }
        return indices;
    }

    public IndexPair calculate_move(Square[][] board, int x, int y) {
        IndexPair indices = get_best_take(board, x, y);
        if (indices.x != -1 && indices.y != -1) {
            return indices;
        }
        IndexPair aux =  new IndexPair();
        indices = verify_move(board, x, y, -1, 0);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, -1, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, -1, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, 0, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, 0, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, 1, -1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, 1, 0);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        indices = verify_move(board, x, y, 1, 1);
        if (indices.x != -1 && indices.y != -1) {
            aux.x = indices.x;
            aux.y = indices.y;
        }
        return aux;
    }

    public IndexPair move(Square[][] board, int x, int y) {
        IndexPair indices;
        indices = calculate_move(board, x, y);
        if (indices.x != -1) {
            board[indices.x][indices.y] = board[x][y];
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.y + 97) + (8 - indices.x));
            System.out.flush();
        }
        System.out.flush();
        return indices;
    }
}
