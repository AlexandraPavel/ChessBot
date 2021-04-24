public class Square {
    public int line_index;
    public int column_index;
    public Piece piece;

    public Square(int line_index, int column_index, Piece piece) {
        this.line_index = line_index;
        this.column_index = column_index;
        this.piece = piece;
    }

    public Square(int line_index, int column_index) {
        this.line_index = line_index;
        this.column_index = column_index;
        this.piece = new Default();
    }
}
