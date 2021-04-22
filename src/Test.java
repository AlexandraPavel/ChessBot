import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ChessBoard c1 = new ChessBoard();
        c1.fill_board();
        String engine_side = "";
        String xboard_side = "";
        boolean move_flag = false;
        IndexPair indices = new IndexPair();
        IndexPair white_king_position = new IndexPair(7, 4);
        IndexPair black_king_position = new IndexPair(0, 4);
//        IndexPair black_king_position = new IndexPair(0, 4);
        Scanner scan = new Scanner(System.in);
        System.out.println(c1);
        System.out.print("\n\n");
        indices = c1.board[7][1].piece.move(c1.board, 7, 1);
        System.out.println(indices.x + " " + indices.y);
        indices = c1.board[0][1].piece.move(c1.board, 0, 1);
        System.out.println("black" + indices.x + " " + indices.y);

        indices = c1.board[5][0].piece.move(c1.board, 5, 0);
        System.out.println(indices.x + " " + indices.y);
        System.out.println(c1);
        indices = c1.board[2][0].piece.move(c1.board, 2, 0);
        System.out.println("black" + indices.x + " " + indices.y);
        System.out.println(c1);
        indices = c1.board[3][1].piece.move(c1.board, 3, 1);
        System.out.println(indices.x + " " + indices.y );
        indices = c1.board[1][0].piece.move(c1.board, 1, 0);
        System.out.println(indices.x + " " + indices.y );
        indices = c1.board[0][2].piece.move(c1.board, 0, 2);
        System.out.println(indices.x + " " + indices.y );
        indices = c1.board[6][3].piece.move(c1.board, 6, 3);
        indices = c1.board[7][3].piece.move(c1.board, 7, 3);
        System.out.println(indices.x + " " + indices.y);
        indices = c1.board[6][3].piece.move(c1.board, 6, 3);
        System.out.println(indices.x + " " + indices.y);
        indices = c1.board[5][3].piece.move(c1.board, 5, 3);
        System.out.println(indices.x + " " + indices.y);
        indices = c1.board[1][7].piece.move(c1.board, 1, 7);
        System.out.println(indices.x + " " + indices.y);
        System.out.println(c1);


        while (true) {
            String command = scan.nextLine();
            if (command.compareTo("xboard") == 0) {
                continue;
            } else if(command.compareTo("new") == 0) {
                c1.fill_board();
                engine_side = "black";
                xboard_side = "white";
                move_flag = true;
                indices = c1.randomPawn(engine_side, c1.board);
            } else if(command.startsWith("protover")) {
                System.out.println("feature sigint=0 san=0 myname=\"Rosoga BOT\" done=1");
                System.out.flush();
            } else if(command.compareTo("force") == 0) {
                move_flag = false;
                continue;
            } else if(command.compareTo("black") == 0) {
                engine_side = "black";
                xboard_side = "white";
            } else if(command.compareTo("white") == 0) {
                engine_side = "white";
                xboard_side = "black";
            } else if(command.compareTo("go") == 0) {
                move_flag = true;
                if (engine_side.compareTo("black") == 0) {
                    indices = c1.randomPawn("black", c1.board);
                    indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                } else {
                    indices = c1.randomPawn("white", c1.board);
                    indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                }
                // Verific daca primesc o mutare valida de la xboard ---a2a3
            } else if((command.charAt(1) >= '1') && (command.charAt(1) <= '8')) {
                System.out.println(command);
                int start_x = 8 - command.charAt(1) + 48;
                int start_y = command.charAt(0) - 97;
                int end_x = 8 - command.charAt(3) + 48;
                int end_y = command.charAt(2) - 97;

                c1.board[end_x][end_y] = c1.board[start_x][start_y];
                c1.board[start_x][start_y] = new Square(start_x, start_y);
                if(move_flag) {
                    if(indices.x == end_x && indices.y == end_y) {
                        System.out.println("resign");
                        System.out.flush();
                    } else {
                        indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                    }
                }
            } else if(command.compareTo("quit") == 0) {
                break;
            }
        }
    }
}