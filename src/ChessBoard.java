import java.util.ArrayList;

public class ChessBoard {
    Square[][] board;
    boolean castling_white;
    boolean castling_black;

    public ChessBoard() {
        board = new Square[8][8];
        castling_white = false;
        castling_black = false;
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
    public IndexPair castling_function(String color, Square[][] board, IndexPair rook, IndexPair king) {
        IndexPair indices = new IndexPair();
        int modify_y = 0;
        int finish_column_king = 0;
        if (rook.y < king.y) {
            modify_y = -1;
            finish_column_king = 1;
        } else {
            modify_y = 1;
            finish_column_king = 7;
        }
        int column = king.y + modify_y;
        if (color.compareTo("white") == 0) {
            while (column != finish_column_king && board[king.x][column].piece.type == 'x') {
                Square aux =  board[king.x][king.y];
                board[king.x][column] = new Square(king.x, column, new WKing());
                board[king.x][king.y] = new Square(king.x, king.y);
                ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) board[king.x][column].piece).
                    is_in_check(board, king.x, column);
                board[king.x][king.y] = aux;
                board[king.x][column] = new Square(king.x, column);
                if ((is_check_result.get(0)).size() != 0)
                    return indices;
                column += modify_y;
            }
            if (modify_y == -1 && board[king.x][1].piece.type != 'x')
                return indices;
            if (column == finish_column_king) {
                indices = ((WKing)board[king.x][king.y].piece).move(board, king.x, king.y, king.x, column - modify_y);
                if (modify_y == -1) {
                    board[king.x][3] = new Square(king.x, 3, new WRook());
                    board[king.x][0] = new Square(king.x, 0);
                    ((WRook) board[king.x][3].piece).moved = true;
                    System.out.println("move " + (char) (0 + 97) + (8 - king.x) +
                    (char) (3 + 97) + (8 - king.x));
                    System.out.flush();
                } else {
                    board[king.x][5] = new Square(king.x, 5, new WRook());
                    board[king.x][7] = new Square(king.x, 7);
                    ((WRook) board[king.x][5].piece).moved = true;
                    System.out.println("move " + (char) (0 + 97) + (8 - king.x) +
                    (char) (7 + 97) + (8 - king.x));
                    System.out.flush();
                }
            }
        } else {
            while (column != finish_column_king && board[king.x][column].piece.type == 'x') {
                Square aux =  board[king.x][king.y];
                board[king.x][column] = new Square(king.x, column, new BKing());
                board[king.x][king.y] = new Square(king.x, king.y);
                ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) board[king.x][column].piece).
                    is_in_check(board, king.x, column);
                board[king.x][king.y] = aux;
                board[king.x][column] = new Square(king.x, column);
                if ((is_check_result.get(0)).size() != 0) {
                    return indices;
                }
                column += modify_y;
            }
            if (modify_y == -1 && board[king.x][1].piece.type != 'x')
                return indices;
            if (column == finish_column_king) {
                indices = ((BKing)board[king.x][king.y].piece).move(board, king.x, king.y, king.x, column - modify_y);
                if (modify_y == -1) {
                    board[king.x][3] = new Square(king.x, 3, new BRook());
                    board[king.x][0] = new Square(king.x, 0);
                    ((BRook) board[king.x][3].piece).moved = true;
                    System.out.println("move " + (char) (0 + 97) + (8 - king.x) +
                    (char) (3 + 97) + (8 - king.x));
                    System.out.flush();
                } else {
                    board[king.x][5] = new Square(king.x, 5, new BRook());
                    board[king.x][7] = new Square(king.x, 7);
                    ((BRook) board[king.x][5].piece).moved = true;
                    System.out.println("move " + (char) (0 + 97) + (8 - king.x) +
                    (char) (7 + 97) + (8 - king.x));
                    System.out.flush();
                }
            }
        }
        return indices;
    }

    IndexPair randomPiece(String color, Square[][] board, IndexPair king) {
        IndexPair indices = new IndexPair();
        if (color.compareTo("white") == 0) {
            if (!castling_white && board[7][4].piece.colour.compareTo("white") == 0 &&
                board[7][4].piece.type == 'k' &&
                ((WKing)board[7][4].piece).moved == false &&
                board[7][0].piece.colour.compareTo("white") == 0 && 
                board[7][0].piece.type == 'r' &&
                ((WRook)board[7][0].piece).moved == false) {
                    indices = castling_function(color, board, new IndexPair(7, 0), king);
                    if (indices.x != -1 && indices.y != -1) {
                        castling_white = true;
                        return indices;
                    }
                }
            if (!castling_white && board[7][4].piece.colour.compareTo("white") == 0 &&
                board[7][4].piece.type == 'k' &&
                ((WKing)board[7][4].piece).moved == false &&
                board[7][7].piece.colour.compareTo("white") == 0 && 
                board[7][7].piece.type == 'r' &&
                ((WRook)board[7][7].piece).moved == false) {
                    indices = castling_function(color, board, new IndexPair(7, 7), king);
                    if (indices.x != -1 && indices.y != -1) {
                        castling_white = true;
                        return indices;
                    }
                }

            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    if ((board[i][j].piece.type != 'x') && (board[i][j].piece.colour.compareTo("white") == 0)) {
                        indices.x = i;
                        indices.y = j;
                        if (board[indices.x][indices.y].piece.type == 'k') {
                            continue;
                        } else {
                            Square aux = board[indices.x][indices.y];
                            board[indices.x][indices.y] = new Square(indices.x, indices.y);
                            ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) board[king.x][king.y].piece).
                                is_in_check(board, king.x, king.y);
                            board[indices.x][indices.y] = aux;
                            if ((is_check_result.get(0)).size() == 0) {
                                indices = board[indices.x][indices.y].piece.move(board, indices.x, indices.y);
                                if (indices.x != -1 && indices.y != -1)
                                    return indices;
                            } else
                                continue;
                        }
                    }
                }
            }
        } else {
            if (!castling_white && board[0][4].piece.colour.compareTo("black") == 0 &&
                board[0][4].piece.type == 'k' &&
                ((BKing)board[0][4].piece).moved == false &&
                board[0][0].piece.colour.compareTo("black") == 0 && 
                board[0][0].piece.type == 'r' &&
                ((BRook)board[0][0].piece).moved == false) {
                    indices = castling_function(color, board, new IndexPair(0, 0), king);
                    if (indices.x != -1 && indices.y != -1) {
                        castling_black = true;
                        return indices;
                    }
                }
            if (!castling_white && board[0][4].piece.colour.compareTo("black") == 0 &&
                board[0][4].piece.type == 'k' &&
                ((BKing)board[0][4].piece).moved == false &&
                board[0][7].piece.colour.compareTo("black") == 0 && 
                board[0][7].piece.type == 'r' &&
                ((BRook)board[0][7].piece).moved == false) {
                    indices = castling_function(color, board, new IndexPair(0, 7), king);
                    if (indices.x != -1 && indices.y != -1) {
                        castling_black = true;
                        return indices;
                    }
                }
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j >= 0; j--) {
                    if ((board[i][j].piece.type != 'x') && (board[i][j].piece.colour.compareTo("black") == 0)) {
                        indices.x = i;
                        indices.y = j;
                        if (board[indices.x][indices.y].piece.type == 'k') {
                            continue;
                        } else {
                            Square aux = board[indices.x][indices.y];
                            board[indices.x][indices.y] = new Square(indices.x, indices.y);
                            ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) board[king.x][king.y].piece).
                                is_in_check(board, king.x, king.y);
                            board[indices.x][indices.y] = aux;
                            if ((is_check_result.get(0)).size() == 0) {
                                indices = board[indices.x][indices.y].piece.move(board, indices.x, indices.y);
                                if (indices.x != -1 && indices.y != -1)
                                    return indices;
                            } else
                                continue;
                        }
                    }
                }
            }
        }
        return indices;
    }

}