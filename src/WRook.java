import java.util.ArrayList;

public class WRook extends Piece {
    public WRook() {
        super("white", 'r', 5);
    }

    public IndexPair calculateMove(Square[][] board, int x, int y){
        int i = x, j = y, k = x, l = y;
        //ma duc in jos
        if(i + 1 != 8) {
            while (board[i + 1][y].piece.type == 'x' && i < 8) {
                i++;
            }
            i += 1;
        }
        //ma duc la dreapta
        if(j + 1 != 8) {
            while (board[x][j + 1].piece.type == 'x' && j < 8) {
                j++;
            }
            j += 1;
        }
        //ma duc in sus
        if(k - 1 > 0) {
            while (board[k - 1][y].piece.type == 'x' && k - 1 > 0) {
                k--;
            }
            k -= 1;
        }
        //ma duc la stanga
        if(l - 1 >= 0) {
            while (board[x][l - 1].piece.type == 'x' && l >= 0) {
                l--;
            }
            l -= 1;
        }

        ArrayList<IndexPair> found = new ArrayList<>();
        ArrayList<Piece> pieces_found = new ArrayList<>();
        if(board[i][y].piece.colour.equals("black")){
            pieces_found.add(board[i][y].piece);
            found.add(new IndexPair(i , y));
        }
        if(board[x][j].piece.colour.equals("black")){
            pieces_found.add(board[x][j].piece);
            found.add(new IndexPair(x ,j));
        }
        if(board[k][y].piece.colour.equals("black")){
            pieces_found.add(board[k][y].piece);
            found.add(new IndexPair(k, y));
        }
        if(board[x][l].piece.colour.equals("black")){
            pieces_found.add(board[x][l].piece);
            found.add(new IndexPair(x, l));
        }

        if(pieces_found.isEmpty()){
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
            System.out.println("resign");
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