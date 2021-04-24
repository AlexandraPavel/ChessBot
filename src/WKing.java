import java.util.ArrayList;

public class WKing extends Piece {
    public boolean moved;
    public boolean in_check;
    public int checked;

    public WKing() {
        super("white", 'k', Integer.MAX_VALUE);
        moved = false;
        in_check = false;
        checked = 0;
    }
	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		return new IndexPair();
	}

    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }

    public boolean valid(int a, int b) {
        if(a >= 0 && a < 8 && b >= 0 && b < 8) {
            return true;
        }
        return false;
    }

    public ArrayList<IndexPair> is_in_check(Square[][] board, int x, int y) {
        ArrayList<IndexPair> check_positions = new ArrayList<>();
        int i, j;
        for(i = y - 1; i >= 0; i--) {
            if(!board[x][i].piece.colour.equals("default")) {
                if(board[x][i].piece.colour.equals("black") && (board[x][i].piece.type == 'r' || board[x][i].piece.type == 'q')) {
                    check_positions.add(new IndexPair(x, i));
                }
                break;
            }
        }
        for(i = y + 1; i < 8; i++) {
            if(!board[x][i].piece.colour.equals("default")) {
                if(board[x][i].piece.colour.equals("black") && (board[x][i].piece.type == 'r' || board[x][i].piece.type == 'q')) {
                    check_positions.add(new IndexPair(x, i));
                }
                break;
            }
        }
        for(i = x - 1; i >= 0; i--) {
            if(!board[i][y].piece.colour.equals("default")) {
                if(board[i][y].piece.colour.equals("black") && (board[i][y].piece.type == 'r' || board[i][y].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, y));
                }
                break;
            }
        }
        for(i = x + 1; i < 8; i++) {
            if(!board[i][y].piece.colour.equals("default")) {
                if(board[i][y].piece.colour.equals("black") && (board[i][y].piece.type == 'r' || board[i][y].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, y));
                }
                break;
            }
        }
        for(i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'r' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                } else if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'p' && i == x - 1 && j == y - 1)) {
                    check_positions.add(new IndexPair(i, j));
                }
                break;
            }
        }
        for(i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'r' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                }
                break;
            }
        }
        for(i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'r' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                } else if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'p' && i == x - 1 && j == y + 1)) {
                    check_positions.add(new IndexPair(i, j));
                }
                break;
            }
        }
        for(i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if(!board[i][j].piece.colour.equals("default")) {
                if(board[i][j].piece.colour.equals("black") && (board[i][j].piece.type == 'r' || board[i][j].piece.type == 'q')) {
                    check_positions.add(new IndexPair(i, j));
                }
                break;
            }
        }
        int[] v = new int[] {2, 1, 1, 2, -1, 2, -2, 1, -2, -1, -1, -2, 1, -2, 2, -1};
        for(i = 0; i < v.length; i+=2) {
            if(valid(x - v[i], y - v[i + 1])) {
                if(board[x - v[i]][y - v[i + 1]].piece.colour.equals("black") && board[x - v[i]][y - v[i + 1]].piece.type == 'n') {
                    check_positions.add(new IndexPair(x - v[i], y - v[i + 1]));
                }
            }
        }
        return check_positions;
    }
}
