import java.util.ArrayList;

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

    public ArrayList<Integer> black_pawn_move_forward(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if(x == 1 && board[x + 1][y] == 0 && board[x + 2][y] == 0) {
            indices.add(x + 2);
            indices.add(y);
        } else if(board[x + 1][y] == 0) {
            indices.add(x + 1);
            indices.add(y);
        } else {
            indices.add(-1);
            indices.add(-1);
        }
        return indices;
    }

    public ArrayList<Integer> black_pawn_calculate_move(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if(x == 7) {
            indices.add(-1);
            indices.add(-1);
            return indices;
        }
        if(y == 0) {
            if(board[x + 1][y + 1] != 0) {
                indices.add(x + 1);
                indices.add(y + 1);
            } else {
                indices = black_pawn_move_forward(x, y);
            }
            return indices;
        }
        if(y == 7) {
            if(board[x + 1][y - 1] != 0) {
                indices.add(x + 1);
                indices.add(y - 1);
            } else {
                indices = black_pawn_move_forward(x, y);
            }
            return indices;
        }
        if(board[x + 1][y - 1] != 0 && board[x + 1][y + 1] != 0) {
            indices.add(x + 1);
            if(board[x + 1][y - 1] > board[x + 1][y + 1]) {
                indices.add(y - 1);
            } else {
                indices.add(y + 1);
            }
        } else if(board[x + 1][y - 1] != 0) {
            indices.add(x + 1);
            indices.add(y - 1);
        } else if(board[x + 1][y + 1] != 0) {
            indices.add(x + 1);
            indices.add(y + 1);
        } else {
            indices = black_pawn_move_forward(x, y);
        }
        return indices;
    }

    public ArrayList<Integer> black_pawn_move(int x, int y) {
        ArrayList<Integer> indices;
        indices = black_pawn_calculate_move(x, y);
        if(indices.get(0) == -1) {
            System.out.println("@@@@"); ////// ???????
        } else {
            if(indices.get(0) != 7) {
                board[indices.get(0)][indices.get(1)] = board[x][y];
            } else {
                board[indices.get(0)][indices.get(1)] = 10;
            }
            board[x][y] = 0;
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.get(1) + 97) + (8 - indices.get(0)));
        }
        return indices;
    }

    public ArrayList<Integer> white_pawn_move_forward(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if(x == 6 && board[x - 1][y] == 0 && board[x - 2][y] == 0) {
            indices.add(x - 2);
            indices.add(y);
        } else if(board[x - 1][y] == 0) {
            indices.add(x - 1);
            indices.add(y);
        } else {
            indices.add(-1);
            indices.add(-1);
        }
        return indices;
    }

    public ArrayList<Integer> white_pawn_calculate_move(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if(x == 0) {
            indices.add(-1);
            indices.add(-1);
            return indices;
        }
        if(y == 0) {
            if(board[x - 1][y + 1] != 0) {
                indices.add(x - 1);
                indices.add(y + 1);
            } else {
                indices = white_pawn_move_forward(x, y);
            }
            return indices;
        }
        if(y == 7) {
            if(board[x - 1][y - 1] != 0) {
                indices.add(x - 1);
                indices.add(y - 1);
            } else {
                indices = white_pawn_move_forward(x, y);
            }
            return indices;
        }
        if(board[x - 1][y - 1] != 0 && board[x - 1][y + 1] != 0) {
            indices.add(x - 1);
            if(board[x - 1][y + 1] > board[x - 1][y - 1]) {
                indices.add(y + 1);
            } else {
                indices.add(y - 1);
            }
        } else if(board[x - 1][y - 1] != 0) {
            indices.add(x - 1);
            indices.add(y - 1);
        } else if(board[x - 1][y + 1] != 0) {
            indices.add(x - 1);
            indices.add(y + 1);
        } else {
            indices = white_pawn_move_forward(x, y);
        }
        return indices;
    }

    public ArrayList<Integer> white_pawn_move(int x, int y) {
        ArrayList<Integer> indices;
        indices = white_pawn_calculate_move(x, y);
        if(indices.get(0) == -1) {
            System.out.println("@@@@"); ////// ???????
        } else {
            if(indices.get(0) != 0) {
                board[indices.get(0)][indices.get(1)] = board[x][y];
            } else {
                board[indices.get(0)][indices.get(1)] = 10;
            }
            board[x][y] = 0;
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.get(1) + 97) + (8 - indices.get(0)));
        }
        return indices;
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

        //c1.board[1][0] = 6;
        //c1.board[2][1] = 1;
        //c1.board[4][2] = 1;

        c1.board[5][6] = 7;
        c1.board[2][5]=  7;
        c1.board[6][7] = 8;

        ArrayList<Integer> indices;
//        indices = c1.black_pawn_move(1, 0);
//        while(indices.get(0) != -1) {
//            indices =  c1.black_pawn_move(indices.get(0), indices.get(1));
//        }

        indices =  c1.white_pawn_move(5, 6);
        while(indices.get(0) != -1) {
            indices = c1.white_pawn_move(indices.get(0), indices.get(1));
        }
        // index pozitie initiala linie / coloana - index pozitie finala linie / coloana
        //
        System.out.println(c1);

        //suntem cu negru
        //while(jocul continua) {
        // comanda = primire comanda (xboard) (new) (protover).......
        // botul_nostru = negru
        // c1.white_pawn_moves sau c1.black_pawn_moves
    }
}