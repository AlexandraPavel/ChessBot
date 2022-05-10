import java.util.ArrayList;

public class BKing extends Piece {
    public boolean moved;
    public boolean in_check;
    public int checked;
    public BKing(int checked) {
        super("black", 'k', Integer.MAX_VALUE);
        moved = false;
        in_check = false;
        this.checked = checked;
    }

    public BKing() {
        super("black", 'k', Integer.MAX_VALUE);
        moved = false;
        in_check = false;
        checked = 0;
    }
    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
    public IndexPair force_move(Square[][] board, int x, int y, IndexPair move) {
        return new IndexPair();
    }

    public IndexPair move(Square[][] board, int x, int y, int next_x, int next_y) {
        board[next_x][next_y] = board[x][y];
        board[x][y] = new Square(x, y);
        ((BKing) board[next_x][next_y].piece).moved = true;
        System.out.println("move " + (char) (y + 97) + (8 - x) +
                (char) (next_y + 97) + (8 - next_x));
        System.out.flush();
        return new IndexPair(next_x, next_y);
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }
    public IndexPair take_or_enter_attack(Square[][] board, int x, int y, ArrayList<IndexPair> moves,IndexPair check_source) {
        IndexPair indices = new IndexPair();
        int i, j;
        int modify_x, modify_y;
        modify_x = check_source.x - x;
        modify_y = check_source.y - y;
        if (modify_x > 0)
            modify_x = 1;
        else if (modify_x == 0)
            modify_x = 0;
        else
            modify_x = -1;

        if (modify_y > 0)
            modify_y = 1;
        else if (modify_y == 0)
            modify_y = 0;
        else
            modify_y = -1;
        int k_x, k_y;
        k_x = Math.min(x, check_source.x);
        k_y = Math.min(y, check_source.y);
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++)
                if (board[i][j].piece.colour.compareTo("black") == 0) {
                    indices = board[i][j].piece.force_move(board, i, j, check_source);
                    if (indices.x != -1 && indices.y != -1) {
                        return indices;
                    }
                    k_x += modify_x;
                    k_y += modify_y;
                    while (valid(k_x, k_y) && k_x <= Math.max(x, check_source.x) && k_y <= Math.max(y, check_source.y)) {
                        if (board[k_x][k_y].piece.type == 'x')
                            indices = board[i][j].piece.force_move(board, i, j, new IndexPair(k_x, k_y));
                        if (indices.x != -1 && indices.y != -1) {
                            return indices;
                        }
                        k_x += modify_x;
                        k_y += modify_y;
                    }
                }
        return indices;
    }

    public ArrayList<IndexPair> verify_moves(Square[][] board, int x, int y, ArrayList<IndexPair> illegal_moves, ArrayList<IndexPair> source_array) {
        ArrayList<IndexPair> moves = new ArrayList<>();
        IndexPair source = new IndexPair();
        if (source_array.size() != 0) {
            source.x = source_array.get(0).x;
            source.y = source_array.get(0).y;
        }
        if (valid(x - 1, y - 1) && (board[x - 1][y - 1].piece.type == 'x' || (x - 1 == source.x && y - 1 == source.y)))
            moves.add(new IndexPair(x - 1, y - 1));
        if (valid(x - 1, y) && (board[x - 1][y].piece.type == 'x' || (x - 1 == source.x && y == source.y)))
            moves.add(new IndexPair(x - 1, y));
        if (valid(x - 1, y + 1) && (board[x - 1][y + 1].piece.type == 'x' || (x - 1 == source.x && y + 1 == source.y)))
            moves.add(new IndexPair(x - 1, y + 1));
        if (valid(x, y - 1) && (board[x][y - 1].piece.type == 'x' || (x == source.x && y - 1 == source.y)))
            moves.add(new IndexPair(x , y - 1));
        if (valid(x, y + 1) && (board[x][y + 1].piece.type == 'x' || (x == source.x && y + 1 == source.y)))
            moves.add(new IndexPair(x, y + 1));
        if (valid(x + 1, y - 1) && (board[x + 1][y - 1].piece.type == 'x' || (x + 1 == source.x && y - 1 == source.y)))
            moves.add(new IndexPair(x + 1, y - 1));
        if (valid(x + 1, y) && (board[x + 1][y].piece.type == 'x' || (x + 1 == source.x && y == source.y)))
            moves.add(new IndexPair(x + 1, y));
        if (valid(x + 1, y + 1) && (board[x + 1][y + 1].piece.type == 'x' || (x + 1 == source.x && y + 1 == source.y)))
            moves.add(new IndexPair(x + 1, y + 1));
        for (IndexPair move : illegal_moves)
            moves.remove(move);

        ArrayList<IndexPair> result = new ArrayList<>();
        for (IndexPair move : moves) {
            board[move.x][move.y] = new Square(move.x, move.y, new BKing(((BKing)board[x][y].piece).checked));
            board[x][y] = new Square(x, y);
            ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) board[move.x][move.y].piece).is_in_check(board, move.x, move.y);
            board[x][y] = new Square(x, y, new BKing(((BKing)board[move.x][move.y].piece).checked));
            board[move.x][move.y] = new Square(move.x, move.y);
            if (is_check_result.get(0).size() == 0)
                result.add(move);
        }
        return result;
    }

    public ArrayList<ArrayList<IndexPair>> is_in_check(Square[][] board, int x, int y) {
        ArrayList<IndexPair> check_positions = new ArrayList<>();
        ArrayList<IndexPair> not_good_moves = new ArrayList<>();
        int i, j;
        for(i = y - 1; i >= 0; i--) {
            if(!board[x][i].piece.colour.equals("default")) {
                if(board[x][i].piece.colour.equals("white") && (board[x][i].piece.type == 'r' || board[x][i].piece.type == 'q')) {
                    check_positions.add(new IndexPair(x, i));
                    not_good_moves.add(new IndexPair(x, y - 1));
                    not_good_moves.add(new IndexPair(x, y + 1));
                }
                break;
            }
        }
        for(i = y + 1; i < 8; i++) {
            if(!board[x][i].piece.colour.equals("default")) {
                if(board[x][i].piece.colour.equals("white") && (board[x][i].piece.type == 'r' || board[x][i].piece.type == 'q')) {
                    check_positions.add(new IndexPair(x, i));
                    not_good_moves.add(new IndexPair(x, y + 1));
                    not_good_moves.add(new IndexPair(x, y - 1));
                }
                break;
            }
        }
        for(i = x - 1; i >= 0; i--) {
            if(!board[i][y].piece.colour.equals("default")) {
                if(board[i][y].piece.colour.equals("white") && (board[i][y].piece.type == 'r' || board[i][y].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, y));
                    not_good_moves.add(new IndexPair(x - 1, y));
                    not_good_moves.add(new IndexPair(x + 1, y));
                }
                break;
            }
        }
        for(i = x + 1; i < 8; i++) {
            if(!board[i][y].piece.colour.equals("default")) {
                if(board[i][y].piece.colour.equals("white") && (board[i][y].piece.type == 'r' || board[i][y].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, y));
                    not_good_moves.add(new IndexPair(x + 1, y));
                    not_good_moves.add(new IndexPair(x - 1, y));
                }
                break;
            }
        }
        for(i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'b' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x - 1, y - 1));
                    not_good_moves.add(new IndexPair(x + 1, y + 1));
                } 
                break;
            }
        }
        for(i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'b' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x + 1, y + 1));
                    not_good_moves.add(new IndexPair(x - 1, y - 1));
                } else if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'p' && i == x + 1 && j == y + 1)) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x + 1, y + 1));
                }
                break;
            }
        }
        for(i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'b' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x - 1, y + 1));
                    not_good_moves.add(new IndexPair(x + 1, y - 1));
                } 
                break;
            }
        }
        for(i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'b' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x + 1, y - 1));
                    not_good_moves.add(new IndexPair(x - 1, y + 1));
                } else if(board[i][j].piece.colour.equals("white") && (board[i][j].piece.type == 'p' && i == x + 1 && j == y - 1)) {
                    check_positions.add(new IndexPair(i, j));
                    not_good_moves.add(new IndexPair(x + 1, y - 1));
                }
                break;
            }
        }
        int[] v = new int[] {2, 1, 1, 2, -1, 2, -2, 1, -2, -1, -1, -2, 1, -2, 2, -1};
        for(i = 0; i < v.length; i+=2) {
            if(valid(x - v[i], y - v[i + 1])) {
                if(board[x - v[i]][y - v[i + 1]].piece.colour.equals("white") && board[x - v[i]][y - v[i + 1]].piece.type == 'n') {
                    check_positions.add(new IndexPair(x - v[i], y - v[i + 1]));
                }
            }
        }
        ArrayList<ArrayList<IndexPair>> solution = new ArrayList<>();
        solution.add(check_positions);
        solution.add(not_good_moves);
        return solution;
    }
}
