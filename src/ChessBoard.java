public class ChessBoard {
    int[][] board;

    public ChessBoard() {
        board = new int[8][8];
    }

    public void fill_board() {
        for(int i = 0; i < 8; i++) {
            board[0][i] = 1;
            board[1][i] = 1;
            board[6][i] = 1;
            board[7][i] = 1;
        }
    }

    public void white_pawn_moves_forward1(int x, int y, int x_prime, int y_prime) {
        if(board[x_prime][y_prime] == 0) {
            if(x_prime == 7) {
                board[x_prime][y_prime] = 10;
            } else {
                board[x_prime][y_prime] = 1;
            }
            board[x][y] = 0;
        } else {
            System.out.println("Move 1 square is invalid");
        }
    }

    public void white_pawn_moves_forward2(int x, int y, int x_prime, int y_prime) {
        if(board[x_prime][y_prime] == 0 && board[x_prime - 1][y_prime] == 0) {
            board[x_prime][y_prime] = 1;
            board[x][y] = 0;
        } else {
            System.out.println("Move 2 squares is invalid");
        }
    }

    public void white_pawn_takes(int x, int y, int x_prime, int y_prime) {
        if(board[x_prime][y_prime] != 0 && Math.abs(y_prime - y) == 1) {
            if(x_prime == 7) {
                board[x_prime][y_prime] = 10;
            } else {
                board[x_prime][y_prime] = 1;
            }
            board[x][y] = 0;
        } else {
            System.out.println("Take move is invalid");
        }
    }

    public void white_pawn_moves(int x, int y, int x_prime, int y_prime) {
        int nr = Math.abs(x_prime - x);
        int nr2 = Math.abs(y_prime - y);
        if(nr == 1 && y == y_prime) {
            white_pawn_moves_forward1(x, y, x_prime, y_prime);
            return;
        }
        if(nr == 2 && y == y_prime && x == 1) {
            white_pawn_moves_forward2(x, y, x_prime, y_prime);
            return;
        }
        if(nr == 1 && nr2 == 1) {
            white_pawn_takes(x, y, x_prime, y_prime);
            return;
        }
        System.out.println("Move is invalid");
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result = result + board[i][j] + " ";
            }
            if(i != 7) {
                result = result + "\n";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ChessBoard c1 = new ChessBoard();
        c1.fill_board();

        c1.board[6][1] = 4;
        c1.white_pawn_moves(1, 0, 3, 0);
        c1.white_pawn_moves(3, 0, 4, 0);
        c1.white_pawn_moves(4, 0, 5, 0);
        c1.white_pawn_moves(5, 0, 6, 1);
        c1.white_pawn_moves(6, 1, 7, 0);
        // index pozitie initiala linie / coloana - index pozitie finala linie / coloana
        System.out.println(c1);
    }
}