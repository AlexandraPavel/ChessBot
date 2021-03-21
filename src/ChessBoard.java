import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ChessBoard {
    Square[][] board;

    public ChessBoard() {
        board = new Square[8][8];
    }

    public void fill_board() {
        board[0][0] = new Square(0, 0, new Piece("black", 'r', 5));
        board[0][1] = new Square(0, 1, new Piece("black", 'n', 3));
        board[0][2] = new Square(0, 2, new Piece("black", 'b', 3));
        board[0][3] = new Square(0, 3, new Piece("black", 'q', 9));
        board[0][4] = new Square(0, 4, new Piece("black", 'k', Integer.MAX_VALUE));
        board[0][5] = new Square(0, 5, new Piece("black", 'b', 3));
        board[0][6] = new Square(0, 6, new Piece("black", 'n', 3));
        board[0][7] = new Square(0, 7, new Piece("black", 'r', 5));
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Square(1, i, new Piece("black", 'p', 1));
            board[6][i] = new Square(6, i, new Piece("white", 'p', 1));
        }
        board[7][0] = new Square(7, 0, new Piece("white", 'r', 5));
        board[7][1] = new Square(7, 1, new Piece("white", 'n', 3));
        board[7][2] = new Square(7, 2, new Piece("white", 'b', 3));
        board[7][3] = new Square(7, 3, new Piece("white", 'q', 9));
        board[7][4] = new Square(7, 4, new Piece("white", 'k', Integer.MAX_VALUE));
        board[7][5] = new Square(7, 5, new Piece("white", 'b', 3));
        board[7][6] = new Square(7, 6, new Piece("white", 'n', 3));
        board[7][7] = new Square(7, 7, new Piece("white", 'r', 5));
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }
    }

    public ArrayList<Integer> black_pawn_move_forward(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if (x == 1 && board[x + 1][y].piece.type == 'x' && board[x + 2][y].piece.type == 'x') {
            indices.add(x + 2);
            indices.add(y);
        } else if (board[x + 1][y].piece.type == 'x') {
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
        if (x == 7) {
            indices.add(-1);
            indices.add(-1);
            return indices;
        }
        if (y == 0) {
            if (board[x + 1][y + 1].piece.type != 'x') {
                indices.add(x + 1);
                indices.add(y + 1);
            } else {
                indices = black_pawn_move_forward(x, y);
            }
            return indices;
        }
        if (y == 7) {
            if (board[x + 1][y - 1].piece.type != 'x') {
                indices.add(x + 1);
                indices.add(y - 1);
            } else {
                indices = black_pawn_move_forward(x, y);
            }
            return indices;
        }
        if (board[x + 1][y - 1].piece.type != 'x' && board[x + 1][y + 1].piece.type != 'x') {
            indices.add(x + 1);
            if (board[x + 1][y - 1].piece.score > board[x + 1][y + 1].piece.score) {
                indices.add(y - 1);
            } else {
                indices.add(y + 1);
            }
        } else if (board[x + 1][y - 1].piece.type != 'x') {
            indices.add(x + 1);
            indices.add(y - 1);
        } else if (board[x + 1][y + 1].piece.type != 'x') {
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
        if (indices.get(0) == -1) {
            System.out.println("resign");
        } else {
            if (indices.get(0) != 7) {
                board[indices.get(0)][indices.get(1)] = board[x][y];
            } else {
                board[indices.get(0)][indices.get(1)] = new Square(indices.get(0), indices.get(1),
                        new Piece("black", 'q', 9));
            }
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.get(1) + 97) + (8 - indices.get(0)));
        }
        System.out.flush();
        return indices;
    }

    public ArrayList<Integer> white_pawn_move_forward(int x, int y) {
        ArrayList<Integer> indices = new ArrayList<>();
        if (x == 6 && board[x - 1][y].piece.type == 'x' && board[x - 2][y].piece.type == 'x') {
            indices.add(x - 2);
            indices.add(y);
        } else if (board[x - 1][y].piece.type == 'x') {
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
        if (x == 0) {
            indices.add(-1);
            indices.add(-1);
            return indices;
        }
        if (y == 0) {
            if (board[x - 1][y + 1].piece.type != 'x') {
                indices.add(x - 1);
                indices.add(y + 1);
            } else {
                indices = white_pawn_move_forward(x, y);
            }
            return indices;
        }
        if (y == 7) {
            if (board[x - 1][y - 1].piece.type != 'x') {
                indices.add(x - 1);
                indices.add(y - 1);
            } else {
                indices = white_pawn_move_forward(x, y);
            }
            return indices;
        }
        if (board[x - 1][y - 1].piece.type != 'x' && board[x - 1][y + 1].piece.type != 'x') {
            indices.add(x - 1);
            if (board[x - 1][y + 1].piece.score > board[x - 1][y - 1].piece.score) {
                indices.add(y + 1);
            } else {
                indices.add(y - 1);
            }
        } else if (board[x - 1][y - 1].piece.type != 'x') {
            indices.add(x - 1);
            indices.add(y - 1);
        } else if (board[x - 1][y + 1].piece.type != 'x') {
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
        if (indices.get(0) == -1) {
            System.out.println("resign");
        } else {
            if (indices.get(0) != 0) {
                board[indices.get(0)][indices.get(1)] = board[x][y];
            } else {
                board[indices.get(0)][indices.get(1)] = new Square(indices.get(0), indices.get(1),
                        new Piece("white", 'q', 9));
            }
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.get(1) + 97) + (8 - indices.get(0)));
        }
        System.out.flush();
        return indices;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result = result + board[i][j].piece.type + " ";
            }
            if (i != 7) {
                result = result + "\n";
            }
        }
        return result;
    }

    ArrayList<Integer> randomPawn(String color, Square[][] board) {
        ArrayList<Integer> indices = new ArrayList<>();
        if(color.compareTo("white") == 0){
            for (int i = 1; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((board[i][j].piece.type == 'p') && (board[i][j].piece.color.compareTo(color) == 0)) {
                        indices.add(i);
                        indices.add(j);
                    }
                }
            }
        } else {
            for (int i = 6; i >= 1; i--) {
                for (int j = 7; j >= 0; j--) {
                    if ((board[i][j].piece.type == 'p') && (board[i][j].piece.color.compareTo(color) == 0)) {
                        indices.add(i);
                        indices.add(j);
                    }
                }
            }
        }
        return indices;
    }

    public Square[][] move_from_to(Piece piece, int z, int w, Square[][] board) {
        board[z][w] = new Square(z, w, piece);
        return board;
    }

    public static void main(String[] args) {
        ChessBoard c1 = new ChessBoard();
        c1.fill_board();
        String engine_side = null;
        String xboard_side = null;
        boolean move_flag = false; 
        ArrayList<Integer> indices = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            String command = scan.nextLine();
            if (command.compareTo("xboard") == 0) {
                continue;
            } else if(command.compareTo("new") == 0) {
                c1.fill_board();
                engine_side = "black";
                xboard_side = "white";
                move_flag = true;
                indices = c1.randomPawn(engine_side, c1.board);
            } else if(command.startsWith("protover")) {
                System.out.println("feature sigint=0 san=0 myname=\"Rosoga BOT\" done=1");
                System.out.flush();
            } else if(command.compareTo("force") == 0) {
                move_flag = false;
                continue;
            } else if(command.compareTo("black") == 0) {
                engine_side = "black";
                xboard_side = "white";
            } else if(command.compareTo("white") == 0) {
                engine_side = "white";
                xboard_side = "black";
            } else if(command.compareTo("go") == 0) { 
                move_flag = true;
                if (engine_side.compareTo("black") == 0) {
                    indices = c1.randomPawn("black", c1.board);
                    indices = c1.black_pawn_move(indices.get(0), indices.get(1));
                } else {
                    indices = c1.randomPawn("white", c1.board);
                    indices = c1.white_pawn_move(indices.get(0), indices.get(1));
                }
                // Verific daca primesc o mutare valida de la xboard ---a2a3
            } else if((command.charAt(1) >= '1') && (command.charAt(1) <= '8')) {
                System.out.println(command);
                int start_x = 8 - command.charAt(1) + 48;
                int start_y = command.charAt(0) - 97;
                int end_x = 8 - command.charAt(3) + 48;
                int end_y = command.charAt(2) - 97;
                
                c1.board[end_x][end_y] = c1.board[start_x][start_y];
                c1.board[start_x][start_y] = new Square(start_x, start_y); 
                if(move_flag == true) {
                    if(indices.get(0) == end_x && indices.get(1) == end_y) {
                        System.out.println("resign");
                        System.out.flush();
                    } else if(engine_side.compareTo("black") == 0) {
                            indices = c1.black_pawn_move(indices.get(0), indices.get(1));
                        } else {
                            indices = c1.white_pawn_move(indices.get(0), indices.get(1));
                        }
                } 
               
            } else if(command.compareTo("quit") == 0) {
                break;
            }

        }
    }
}