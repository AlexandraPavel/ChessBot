import java.util.ArrayList;

public class BPawn extends Piece {
    public BPawn() {
        super("black", 'p', 1);
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
            board[x - 1][y - 1].piece.colour.compareTo("white") == 0) {
            indices.x = x - 1;
            indices.y = y - 1;
        } else if (valid(x - 1, y + 1) && x - 1 == move.x && y + 1 == move.y &&
            board[x - 1][y + 1].piece.colour.compareTo("white") == 0) {
            indices.x = x - 1;
            indices.y = y + 1;
        } else if (valid(x - 1, y) && x - 1 == move.x && y == move.y) {
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
        if (x == 1 && board[x + 1][y].piece.type == 'x' && board[x + 2][y].piece.type == 'x') {
            indices.x = x + 2;
            indices.y = y;
        } else if (board[x + 1][y].piece.type == 'x') {
            indices.x = x + 1;
            indices.y = y;
        } else {
            indices.x = -1;
            indices.y = -1;
        }
        return indices;
    }

    public IndexPair calculate_move(Square[][] board, int x, int y) {
        IndexPair indices = new IndexPair();
        if (x == 7) {
            indices.x = -1;
            indices.y = -1;
            return indices;
        }
        if (y == 0) {
            if (board[x + 1][y + 1].piece.type != 'x') {
                indices.x = x + 1;
                indices.y = y + 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (y == 7) {
            if (board[x + 1][y - 1].piece.type != 'x') {
                indices.x = x + 1;
                indices.y = y - 1;
            } else {
                indices = move_forward(board, x, y);
            }
            return indices;
        }
        if (board[x + 1][y - 1].piece.type != 'x' && board[x + 1][y + 1].piece.type != 'x') {
            indices.x = x + 1;
            if (board[x + 1][y - 1].piece.score > board[x + 1][y + 1].piece.score) {
                indices.y = y - 1;
            } else {
                indices.y = y + 1;
            }
        } else if (board[x + 1][y - 1].piece.type != 'x') {
            indices.x = x + 1;
            indices.y = y - 1;
        } else if (board[x + 1][y + 1].piece.type != 'x') {
            indices.x = x + 1;
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
            if (indices.x != 7) {
                board[indices.x][indices.y] = board[x][y];
            } else {
                board[indices.x][indices.y] = new Square(indices.x, indices.y,
                        new BQueen());
            }
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.y + 97) + (8 - indices.x));
        }
        System.out.flush();
        return indices;
    }
}