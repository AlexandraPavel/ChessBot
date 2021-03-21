public class Piece {
    public String color;
    public char type;
    public int score;

    public Piece(String color, char type, int score) {
        this.color = color;
        this.type = type;
        this.score = score;
    }

    public Piece() {
        this("default",'x', 0);
    }
}
