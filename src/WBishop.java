import java.util.ArrayList;

public class WBishop extends Piece {
    public WBishop() {
        super("white", 'b', 3);
    }

    public IndexPair calculate_move(Square[][] board, int x, int y){
		IndexPair indices = new IndexPair();
		int cost = 0;
		int x_tmp = x;
		int y_tmp = y;
		if (x + 1 < 8 && y + 1 < 8) {
			while ((y + 1) < 8 && (x + 1) < 8  && board[x + 1][y + 1].piece.type == 'x' ) {
				x++;
				y++;
			}
			if ( y + 1 < 8 && x + 1 < 8) {
				if (board[x + 1][y + 1].piece.type != 'x' && board[x + 1][y + 1].piece.colour.equals("black")) {
					if (cost < board[x + 1][y + 1].piece.score){
						cost = board[x + 1][y + 1].piece.score;
						indices.x = x + 1;
						indices.y = y + 1;
					}
					
				}

			}
		} 
		x = x_tmp;
		y = y_tmp;
		if (x + 1 < 8 && y - 1 >= 0) {
			while ((y - 1) >= 0 && (x + 1) < 8 && board[x + 1][y - 1].piece.type == 'x') {
				x++;
				y--;
			}
			if ( y - 1 >= 0 && x + 1 < 8) {
				if (board[x + 1][y - 1].piece.type != 'x'  && board[x + 1][y - 1].piece.colour.equals("black")) {
					if (cost < board[x + 1][y - 1].piece.score){
						cost = board[x + 1][y - 1].piece.score;
						indices.x = x + 1;
						indices.y = y - 1;
					}
					
				}

			}
		}

		x = x_tmp;
		y = y_tmp;
		if (x - 1 >= 0 && y + 1 < 8 ) {
			while ((y + 1) < 8 && (x - 1) >= 0 && board[x - 1][y + 1].piece.type == 'x') {
				x--;
				y++;
			}
			if (y + 1 < 8 && x - 1 >= 0) {
				if (board[x - 1][y + 1].piece.type != 'x' && board[x - 1][y + 1].piece.colour.equals("black") ) {
					
					if (cost < board[x - 1][y + 1].piece.score){
						indices.x = x - 1;
						indices.y = y + 1;
					}
				}

			}
		}

		x = x_tmp;
		y = y_tmp;
		if (x - 1 >= 0 && y - 1 >= 0 ) {
			while ((y - 1) >= 0 && (x - 1) >= 0 && board[x - 1][y - 1].piece.type == 'x') {
				x--;
				y--;
			}
			if (y - 1 >= 0 && x - 1 >= 0) {
				if (board[x - 1][y - 1].piece.type != 'x' && board[x - 1][y - 1].piece.colour.equals("black")) {
					if (cost < board[x - 1][y - 1].piece.score){
						indices.x = x - 1;
						indices.y = y - 1;
					}
				}

			}
		}

		return indices;
	}
	public IndexPair move(Square[][] board, int x,int y) {

		IndexPair indices;
		indices = calculate_move(board, x, y);
		System.out.println("X " +indices.x + " Y " + indices.y);
		if (indices.x == -1) {
			System.out.println("resign");
		} else {
			board[indices.x][indices.y] = board[x][y];
			System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (indices.y + 97) + (8 - indices.x));
			board[x][y] = new Square(x,y);
		}
		
		System.out.flush();
		return indices;

	}
}
