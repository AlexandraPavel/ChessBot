import java.util.ArrayList;

public class BBishop extends Piece {
    public BBishop() {
        super("black", 'b', 3);
    }

    public IndexPair calculate_move(Square[][] board, int x, int y){
		IndexPair indices = new IndexPair();
		IndexPair maxVal = new IndexPair();
		int cost = 0;
		int x_tmp = x;
		int y_tmp = y;
		if (x + 1 < 8 && y + 1 < 8) {
			while ((y + 1) < 8 && (x + 1) < 8  && board[x + 1][y + 1].piece.type == 'x' ) {
				x++;
				y++;
			}
			if ( y + 1 < 8 && x + 1 < 8) {
				if (board[x + 1][y + 1].piece.type != 'x' && board[x + 1][y + 1].piece.colour.equals("white")) {
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
				if (board[x + 1][y - 1].piece.type != 'x'  && board[x + 1][y - 1].piece.colour.equals("white")) {
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
				if (board[x - 1][y + 1].piece.type != 'x' && board[x - 1][y + 1].piece.colour.equals("white") ) {
					
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
				if (board[x - 1][y - 1].piece.type != 'x' && board[x - 1][y - 1].piece.colour.equals("white")) {
					if (cost < board[x - 1][y - 1].piece.score){
						indices.x = x - 1;
						indices.y = y - 1;
					}
				}

			}
		}
		//cand ma aflu pe prima linie
		/*if (x == 0) {
			//cand ma aflu pe prima coloana
			if (y == 0) {
				// next = liber
				if (board[x + 1][y + 1].piece.type == 'x'){
					while (board[x + 1][y + 1].piece.type == 'x' && (y + 1) < 8 && (x + 1) < 8) {
						x++;
						y++;
					}
					if ( y + 1 < 8 && x + 1 < 8) {
						if (board[x + 1][y + 1].piece.type != 'x') {
							indices.x = x + 1;
							indices.y = y + 1;
						}

					}
					//indices.x = x + 1;
					//indices.y = y + 1;
				} else if (board[x + 1][y + 1].piece.type != 'x' && board[x + 1][y + 1].piece.colour == "white" ) {
					indices.x = x + 1;
					indices.y = y + 1;
				}
			} else if (y == 7) {
				// ma aflu pe ultima coloana
				if (board[x + 1][y - 1].piece.type == 'x'){
					while (board[x + 1][y - 1].piece.type == 'x' && (y - 1) >= 0 && (x + 1) < 8) {
						x++;
						y--;
					}
					if ( y - 1 >= 0 && x + 1 < 8) {
						if (board[x + 1][y - 1].piece.type != 'x') {
							indices.x = x + 1;
							indices.y = y - 1;
						}

					}
					//indices.x = x + 1;
					//indices.y = y + 1;
				} else if (board[x + 1][y - 1].piece.type != 'x' && board[x + 1][y - 1].piece.colour == "white" ) {
					indices.x = x + 1;
					indices.y = y - 1;
				}


			}
			/*if (board[x + 1][y + 1].piece.type != 'x' && board[x + 1][y - 1].piece.type != 'x') {
				indices.x = -1;
				indices.y = -1;
			}*/ 

			// ma aflu pe linia 0, [1:6] si ma duc in dreapta
			/*else if ( board[x + 1][y + 1].piece.type == 'x'  ) {
				while (board[x + 1][y + 1].piece.type == 'x' && (y + 1) < 8 && (x + 1) < 8) {
					x++;
					y++;
				}
				if ( y + 1 < 8 && x + 1 < 8) {
					if (board[x + 1][y + 1].piece.type != 'x') {
						indices.x = x + 1;
						indices.y = y + 1;
					}

				}
			// ma aflu pe linia 0, [1:6] si ma duc in stanga
			/*} else if (board[x + 1][y - 1].piece.type == 'x' ) {
				while (board[x + 1][y - 1].piece.type == 'x' && (y - 1) >= 0 && (x + 1) < 8) {
					x++;
					y--;
				}
				if ( y - 1 >= 0 && x + 1 < 8) {
					if (board[x + 1][y - 1].piece.type != 'x') {
						indices.x = x + 1;
						indices.y = y - 1;
					}

				}
			}
		//sunt pe ultima linie;
		/*} else if (x == 7) {
			if (y == 0) {
				if (board[x - 1][y + 1].piece.type == 'x'){
					indices.x = x - 1;
					indices.y = y + 1;
				}
			} else if (y == 7) {
				if (board[x - 1][y - 1].piece.type == 'x'){
					indices.x = x - 1;
					indices.y = y - 1;
				}
			}
			/*if (board[x + 1][y + 1].piece.type != 'x' && board[x + 1][y - 1].piece.type != 'x') {
				indices.x = -1;
				indices.y = -1;
			}*/ 
			/*else if ( board[x - 1][y + 1].piece.type == 'x') {
				indices.x = x - 1;
				indices.y = y + 1;
			} else if (board[x - 1][y - 1].piece.type == 'x') {
				indices.x = x - 1;
				indices.y = y - 1;
			}
		} else {
			if (y == 0) {
				if (board[x - 1][y + 1].piece.type == 'x'){
					indices.x = x - 1;
					indices.y = y + 1;
				}else if (board[x + 1][y + 1].piece.type == 'x') {
					indices.x = x + 1;
					indices.y = y + 1;
				} 	
			} else if (y == 7) {
				if (board[x - 1][y - 1].piece.type == 'x'){
					indices.x = x - 1;
					indices.y = y - 1;
				}else if (board[x + 1][y - 1].piece.type == 'x') {
					indices.x = x + 1;
					indices.y = y - 1;
				} 
			} else {
				if (board[x - 1][y + 1].piece.type == 'x'){
					indices.x = x - 1;
					indices.y = y + 1;
				} else if (board[x - 1][y - 1].piece.type == 'x') {
					indices.x = x - 1;
					indices.y = y - 1;
				} else if (board[x + 1][y - 1].piece.type == 'x') {
					indices.x = x + 1;
					indices.y = y - 1;
				} else if (board[x + 1][y + 1].piece.type == 'x') {
					indices.x = x + 1;
					indices.y = y + 1;
				}
			}
		}*/

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