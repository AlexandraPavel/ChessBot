
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

    public IndexPair calculateMove(Square[][] board, int x, int y){
        int i = x, j = y, k = x, l = y;
        //ma duc in jos
        if(i + 1 != 8) {
            while (board[i + 1][y].piece.type == 'x' && i < 6) {
                i++;
            }
            if(i != x) {
                i += 1;
            }
        }
        //ma duc la dreapta
        if(j + 1 != 8) {
            while (board[x][j + 1].piece.type == 'x' && j < 6) {
                j++;
            }
            if(j != y) {
                j += 1;
            }
        }
        //ma duc in sus
        if(k - 1 > 0) {
            while (board[k - 1][y].piece.type == 'x' && k - 1 > 0) {
                k--;
            }
            if(k != x) {
                k -= 1;
            }
        }
        //ma duc la stanga
        if(l - 1 >= 0) {
            while (board[x][l - 1].piece.type == 'x' && l - 1 > 0) {
                l--;
            }
            if(l != y) {
                l -= 1;
            }
        }

        ArrayList<IndexPair> found = new ArrayList<>();
        ArrayList<Piece> pieces_found = new ArrayList<>();
        if (board[i][y].piece.colour.equals("white")){
            pieces_found.add(board[i][y].piece);
            found.add(new IndexPair(i , y));
        }
        if (board[x][j].piece.colour.equals("white")) {
            pieces_found.add(board[x][j].piece);
            found.add(new IndexPair(x ,j));
        }
        if (board[k][y].piece.colour.equals("white")) {
            pieces_found.add(board[k][y].piece);
            found.add(new IndexPair(k, y));
        }
        if (board[x][l].piece.colour.equals("white")) {
            pieces_found.add(board[x][l].piece);
            found.add(new IndexPair(x, l));
        }

        if (pieces_found.isEmpty()) {
            if(i != x && j != y && k != x && l != y){
                int max = i;
                if(max < j){
                    max = j;
                }
                if(max < k){
                    max = k;
                }
                if(max < l){
                    max = l;
                }
                if(max == i){
                    return new IndexPair(i - 1, y);
                }
                if(max == j){
                    return new IndexPair(x, j - 1);
                }
                if(max == k){
                    return new IndexPair(k + 1 ,y);
                }
                if(max == l){
                    return new IndexPair(x, l + 1);
                }
            }
            //123
            if(i != x && j != y && k != x && l == y){
                int max = x;
                if(max < j){
                    max = j;
                }
                if(max < k){
                    max = k;
                }
                if(max == i){
                    return new IndexPair(i - 1, y);
                }
                if(max == j){
                    return new IndexPair(x, j - 1);
                }
                if(max == k){
                    return new IndexPair(k + 1, y);
                }
            }

            //124
            if(i != x && j != y && k == x && l != y){
                int max = x;
                if(max < j){
                    max = j;
                }
                if(max < l){
                    max = l;
                }
                if(max == i){
                    return new IndexPair(i - 1, y);
                }
                if(max == j){
                    return new IndexPair(x, j - 1);
                }
                if(max == l){
                    return new IndexPair(x, l + 1);
                }
            }

            //134
            if(i != x && j == y && k != x && l != y){
                int max = x;
                if(max < l){
                    max = k;
                }
                if(max < l){
                    max = l;
                }
                if(max == i){
                    return new IndexPair(i - 1, y);
                }
                if(max == k){
                    return new IndexPair(k + 1, y);
                }
                if(max == l){
                    return new IndexPair(x, l + 1);
                }
            }

            //234
            if(i == x && j != y && k != x && l != y){
                int max = j;
                if(max < k){
                    max = k;
                }
                if(max < l){
                    max = l;
                }
                if(max == i){
                    return new IndexPair(x, j - 1);
                }
                if(max == k){
                    return new IndexPair(k + 1, y);
                }
                if(max == l){
                    return new IndexPair(x, l + 1);
                }
            }

            //12
            if(i != x && j != y && k == x && l == y){
                int max = i;
                if(max < j){
                    return new IndexPair(x, j - 1);
                }
                return new IndexPair(i - 1, y);
            }

            //13
            if(i != x && j == y && k != x && l == y){
                int max = i;
                if(max < k){
                    return new IndexPair(k + 1, y);
                }
                return new IndexPair(i - 1, y);
            }

            //14
            if(i != x && j == y && k == x && l != y){
                int max = i;
                if(max < l){
                    return new IndexPair(x, l + 1);
                }
                return new IndexPair(i - 1, y);
            }

            //23
            if(i == x && j != y && k != x && l == y){
                int max = j;
                if(max < k){
                    return new IndexPair(k, y);
                }
                return new IndexPair(x, j - 1);
            }

            //24
            if(i == x && j != y && k == x && l != y){
                int max = j;
                if(max < l){
                    return new IndexPair(x, l + 1);
                }
                return new IndexPair(x, j - 1);
            }

            //34
            if(i == x && j == y && k != x && l != y){
                int max = k;
                if(max < l){
                    return new IndexPair(x, l + 1);
                }
                return new IndexPair(k + 1, y);
            }

            if(i != x && j == y && k == x && l == y){
                return new IndexPair(i - 1, y);
            }
            if(i == x && j != y && k == x && l == y) {
                return new IndexPair(x, j - 1);
            }
            if(i == x && j == y && k != x && l == y){
                return new IndexPair(k + 1, y);
            }
            if(i == x && j == y && k == x && l != y){
                return new IndexPair(x, l + 1);
            }

            return new IndexPair(-1, -1);
        }

        int max = Integer.MIN_VALUE, position = 0;
        for(i = 0; i < pieces_found.size(); i++){
            if(pieces_found.get(i).score > max){
                max = pieces_found.get(i).score;
                position = i;
            }
        }
        return found.get(position);
    }

    public IndexPair move(Square[][] board, int x, int y) {
        IndexPair temp = calculateMove(board, x, y);
        if(temp.x == -1){
            // System.out.println("resign");
        } else {
            board[temp.x][temp.y] = board[x][y];
            board[x][y] = new Square(x, y);
            System.out.println("move " + (char) (y + 97) + (8 - x) +
                    (char) (temp.y + 97) + (8 - temp.x));
        }
        System.out.flush();
        return temp;
    }
}