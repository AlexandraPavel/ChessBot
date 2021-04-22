import java.util.ArrayList;

public class WBishop extends Piece {
    public WBishop() {
        super("black", 'b', 3);
    }

    public IndexPair calculate_move(Square[][] board, int x, int y){
        IndexPair indices = new IndexPair();
        IndexPair temp = new IndexPair();
        int flag = 0;
        int cost = 0;
        int x_tmp = x;
        int y_tmp = y;
        if (x + 1 < 8 && y + 1 < 8) {
            while ((y + 1) < 7 && (x + 1) < 7  && board[x + 1][y + 1].piece.type == 'x' ) {
                x++;
                y++;
            }
            if (board[x + 1][y + 1].piece.colour.equals("black")) {
                if (cost < board[x + 1][y + 1].piece.score){
                    cost = board[x + 1][y + 1].piece.score;
                    indices.x = x + 1;
                    indices.y = y + 1;
                }

            }else {
                if (flag == 0 && (x != x_tmp || y != y_tmp)) {
                    if (board[x + 1][y + 1].piece.colour.equals("white")){
                        temp.x = x;
                        temp.y = y;
                    } else {
                        temp.x = x+1;
                        temp.y = y+1;
                    }
                    flag = 1;
                }

            }

        }
        x = x_tmp;
        y = y_tmp;
        if (x + 1 < 8 && y - 1 >= 0) {
            while ((y - 1) > 0 && (x + 1) < 7 && board[x + 1][y - 1].piece.type == 'x') {
                x++;
                y--;
            }
            if (board[x + 1][y - 1].piece.colour.equals("black")) {
                if (cost < board[x + 1][y - 1].piece.score){
                    cost = board[x + 1][y - 1].piece.score;
                    indices.x = x + 1;
                    indices.y = y - 1;
                }

            }else {
                if (flag == 0 && (x != x_tmp || y != y_tmp)) {
                    if (board[x + 1][y - 1].piece.colour.equals("white")){
                        temp.x = x;
                        temp.y = y;
                    } else {
                        temp.x = x+1;
                        temp.y = y-1;
                    }
                    flag = 1;
                }

            }
        }

        x = x_tmp;
        y = y_tmp;
        if (x - 1 >= 0 && y + 1 < 8 ) {
            while ((y + 1) < 7 && (x - 1) > 0 && board[x - 1][y + 1].piece.type == 'x') {
                x--;
                y++;
            }
            if (board[x - 1][y + 1].piece.colour.equals("black")) {
                if (cost < board[x - 1][y + 1].piece.score){
                    cost = board[x - 1][y + 1].piece.score;
                    indices.x = x - 1;
                    indices.y = y + 1;
                }

            }else {
                if (flag == 0 && (x != x_tmp || y != y_tmp)) {
                    if (board[x - 1][y + 1].piece.colour.equals("white")){
                        temp.x = x;
                        temp.y = y;
                    } else {
                        temp.x = x - 1;
                        temp.y = y + 1;
                    }
                    flag = 1;
                }

            }
        }

        x = x_tmp;
        y = y_tmp;
        if (x - 1 >= 0 && y - 1 >= 0 ) {
            while ((y - 1) > 0 && (x - 1) > 0 && board[x - 1][y - 1].piece.type == 'x') {
                x--;
                y--;
            }
            if (board[x - 1][y - 1].piece.colour.equals("black")) {
                if (cost < board[x - 1][y - 1].piece.score){
                    cost = board[x - 1][y - 1].piece.score;
                    indices.x = x - 1;
                    indices.y = y - 1;
                }

            }else {
                if (flag == 0 && (x != x_tmp || y != y_tmp)) {
                    if (board[x - 1][y - 1].piece.colour.equals("white")){
                        temp.x = x;
                        temp.y = y;
                    } else {
                        temp.x = x - 1;
                        temp.y = y - 1;
                    }
                    flag = 1;
                }

            }
        }
        if (cost == 0  && flag == 1) {
            return temp;
        }
        return indices;
    }
    public IndexPair move(Square[][] board, int x,int y) {

        IndexPair indices;
        indices = calculate_move(board, x, y);
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