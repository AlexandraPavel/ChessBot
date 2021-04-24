import java.util.ArrayList;

public class BRook extends Piece {
    public BRook() {
        super("black", 'r', 5);
    }

	public IndexPair force_move (Square[][] board,int x,int y,IndexPair move) {
		IndexPair indices = new IndexPair();
		int x_temp = x;
		int y_temp = y;
		while ((x + 1) < 7  && board[x + 1][y].piece.type == 'x' &&  (x + 1) != move.x ) {
			x++;
		}
		if (y == move.y && (x + 1) == move.x ) {
			board[move.x][move.y] = board[x_temp][y_temp];
			board[x_temp][y_temp] = new Square(x_temp,x_temp);
			System.out.println("move " + (char) (y_temp + 97) + (8 - x_temp) +
                    (char) (move.y + 97) + (8 - move.x));
			System.out.flush();
			return new IndexPair(x_temp,y_temp);
		}

		x = x_temp;
		y = y_temp;
		while ((y - 1) > 0 && board[x][y - 1].piece.type == 'x' && (y - 1) != move.y) {
			y--;
		}
		if ((y - 1) == move.y && x == move.x ) {
			board[move.x][move.y] = board[x_temp][y_temp];
			board[x_temp][y_temp] = new Square(x_temp,x_temp);
			System.out.println("move " + (char) (y_temp + 97) + (8 - x_temp) +
                    (char) (move.y + 97) + (8 - move.x));
			System.out.flush();
			return new IndexPair(x_temp,y_temp);
		}
		x = x_temp;
		y = y_temp;
		while ((y + 1) < 7 && board[x][y + 1].piece.type == 'x' && (y + 1) != move.y) {
			y++;
		}	
		if ((y + 1) == move.y && x == move.x) {
			board[move.x][move.y] = board[x_temp][y_temp];
			board[x_temp][y_temp] = new Square(x_temp,x_temp);
			System.out.println("move " + (char) (y_temp + 97) + (8 - x_temp) +
                    (char) (move.y + 97) + (8 - move.x));
			System.out.flush();
			return new IndexPair(x_temp,y_temp);
		}
		
		x = x_temp;
		y = y_temp;
		while ((x - 1) > 0  && board[x - 1][y].piece.type == 'x' && (x - 1) != move.x) {
			x--;
		}	
		if (y == move.y && (x - 1) == move.x) {
			board[move.x][move.y] = board[x_temp][y_temp];
			board[x_temp][y_temp] = new Square(x_temp,x_temp);
			System.out.println("move " + (char) (y_temp + 97) + (8 - x_temp) +
                    (char) (move.y + 97) + (8 - move.x));
			System.out.flush();
			return new IndexPair(x_temp,y_temp);
		}
		return indices;
	}


    public IndexPair move(Square[][] board, int x, int y) {
        return new IndexPair();
    }
}