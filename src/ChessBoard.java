import java.util.ArrayList;
import java.util.Scanner;

public class ChessBoard {
    Square[][] board;

    public ChessBoard() {
        board = new Square[8][8];
    }

    public void fill_board() {
        board[0][0] = new Square(0, 0, new BRook());
        board[0][1] = new Square(0, 1, new BKnight());
        board[0][2] = new Square(0, 2, new BBishop());
        board[0][3] = new Square(0, 3, new BQueen());
        board[0][4] = new Square(0, 4, new BKing());
        board[0][5] = new Square(0, 5, new BBishop());
        board[0][6] = new Square(0, 6, new BKnight());
        board[0][7] = new Square(0, 7, new BRook());
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Square(1, i, new BPawn());
            board[6][i] = new Square(6, i, new WPawn());
        }
        board[7][0] = new Square(7, 0, new WRook());
        board[7][1] = new Square(7, 1, new WKnight());
        board[7][2] = new Square(7, 2, new WBishop());
        board[7][3] = new Square(7, 3, new WQueen());
        board[7][4] = new Square(7, 4, new WKing());
        board[7][5] = new Square(7, 5, new WBishop());
        board[7][6] = new Square(7, 6, new WKnight());
        board[7][7] = new Square(7, 7, new WRook());
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }
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

    IndexPair randomPiece(String color, Square[][] board) {
        IndexPair indices = new IndexPair();
        if (color.compareTo("white") == 0){
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    // System.out.println("Fraieri suntem "+board[i][j].piece.type);
                    if ((board[i][j].piece.type != 'x') && (board[i][j].piece.colour.compareTo("white") == 0)) {
                        indices.x = i;
                        indices.y = j;
                        if (board[indices.x][indices.y].piece.type == 'k') {
//                            System.out.println("\nAm ales Regele si va trebui sa vad cum fac mutatrea :((");
                            continue;
                        } else {
                            indices = board[indices.x][indices.y].piece.move(board, indices.x, indices.y);
                            if (indices.x != -1 && indices.y != -1)
                                return indices;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j >= 0; j--) {
                    // System.out.println("Fraieri suntem "+board[i][j].piece.type);
                    if ((board[i][j].piece.type != 'x') && (board[i][j].piece.colour.compareTo("black") == 0)) {
                        indices.x = i;
                        indices.y = j;
                        if (board[indices.x][indices.y].piece.type == 'k') {
//                            System.out.println("\nAm ales Regele si va trebui sa vad cum fac mutatrea :((");
                            ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing)board[i][j].piece).
                                is_in_check(board,i, j);
                            ArrayList<IndexPair> available_moves = ((BKing) board[i][j].piece).
                                verify_moves(board, i, j, is_check_result.get(1));
                            // System.out.println(c1);
                            if ((is_check_result.get(0)).size() == 0)
                                continue;
                            else
                                System.out.println("Wtf Romania Ce naiba");
                        } else {
                            indices = board[indices.x][indices.y].piece.move(board, indices.x, indices.y);
                            if (indices.x != -1 && indices.y != -1)
                                return indices;
                        }
                    }
                }
            }
        }
        return indices;
    }

}