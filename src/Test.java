import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public IndexPair find_king(Square[][] board, String colour) {
        IndexPair indices = new IndexPair();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (board[i][j].piece.colour.compareTo(colour) == 0 && board[i][j].piece.type == 'k'){
                    indices.x = i;
                    indices.y = j;
                    return indices;
                }
        return indices;
    }

    public static void main(String[] args) {
        ChessBoard c1 = new ChessBoard();
        c1.fill_board();
        String engine_side = "";
        String xboard_side = "";
        boolean move_flag = false;
        IndexPair indices = new IndexPair();
//        IndexPair white_king_position = new IndexPair(7, 4);
//        IndexPair black_king_position = new IndexPair(0, 4);

        Scanner scan = new Scanner(System.in);
//        System.out.println(c1);
//        System.out.print("\n\n");
//
//        // pentru regina
//        indices = c1.board[1][3].piece.move(c1.board, 1, 3);
//
//        indices = c1.board[0][3].piece.move(c1.board, 0, 3);
//        indices = c1.board[1][3].piece.move(c1.board, 1, 3);
//
//        indices = c1.board[6][2].piece.move(c1.board, 6, 2);
//        indices = c1.board[3][3].piece.move(c1.board, 3, 3);
//        indices = c1.board[4][4].piece.move(c1.board, 4, 4);
//
//        // pentru nebun
//        indices = c1.board[6][5].piece.move(c1.board, 6, 5);
//        indices = c1.board[4][5].piece.move(c1.board, 4, 5);
//        indices = c1.board[0][2].piece.move(c1.board, 0, 2);
//
//        // pentru tura
//
//        indices = c1.board[1][7].piece.move(c1.board, 1, 7);
//        indices = c1.board[3][7].piece.move(c1.board, 3, 7);
//        indices = c1.board[4][7].piece.move(c1.board, 4, 7);
//        indices = c1.board[5][7].piece.move(c1.board, 5, 7);
//        indices = c1.board[6][7].piece.move(c1.board, 6, 7);
//        indices = c1.board[0][7].piece.move(c1.board, 0, 7);
//
//
//        // pentru cal
//        indices = c1.board[0][6].piece.move(c1.board, 0, 6);
//        indices = c1.board[2][5].piece.move(c1.board, 2, 5);
//
//
//        c1.board[6][4] = c1.board[7][3];
//        c1.board[7][3] = new Square(7, 3);
//
//
//        ((WKing) c1.board[7][4].piece).move(c1.board, 7, 4, 4, 4);
//
//        ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) c1.board[4][4].piece).is_in_check(c1.board, 4, 4);
//
//        ArrayList<IndexPair> available_moves = ((WKing) c1.board[4][4].piece).verify_moves(c1.board, 4, 4, is_check_result.get(1));
//
//
//        for (IndexPair move : available_moves)
//            System.out.println("black " + move.x + " " + move.y);
//        System.out.println(c1);
//        if ((is_check_result.get(0)).size() == 0) {
//            indices = c1.randomPiece("white", c1.board);
//            if (c1.board[indices.x][indices.y].piece instanceof WKnight) {
//                System.out.println("\nAm ales Regele si va trebui sa vad cum fac mutatrea :((");
//            } else {
//                indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
//            }
//        } else if ((is_check_result.get(0)).size() == 1) {
//            System.out.println("\nRegele trebuie sa mute sau alta piesa trebuie sa intervina");
//            System.out.println(available_moves.get(1).x + " " + available_moves.get(1).y);
//            indices = ((WKing) c1.board[4][4].piece).take_or_enter_attack(c1.board, 4, 4, available_moves,is_check_result.get(0).get(0));
//        } else {
//            System.out.println("\nRegele trebuie sa mute");
//            if (available_moves.size() == 0) {
//                System.out.println("resign");
//                System.out.flush();
//            } else {
//                ((WKing) c1.board[4][4].piece).move(c1.board, 4, 4, available_moves.get(0).x, available_moves.get(0).y);
//            }
//        }
        System.out.println(c1);

        Test obj = new Test();

        while (true) {
            String command = scan.nextLine();
            if (command.compareTo("xboard") == 0) {
                continue;
            } else if(command.compareTo("new") == 0) {
                c1.fill_board();
                engine_side = "black";
                xboard_side = "white";
                move_flag = true;
                indices = c1.randomPiece(engine_side, c1.board);
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
//                    indices = c1.randomPawn("black", c1.board);
//                    indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                    IndexPair black_king = obj.find_king(c1.board, "black");
                    ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) c1.board[black_king.x][black_king.y].piece).is_in_check(c1.board, black_king.x, black_king.y);
                    ArrayList<IndexPair> available_moves = ((BKing) c1.board[black_king.x][black_king.y].piece).verify_moves(c1.board, black_king.x, black_king.y, is_check_result.get(1));
                    System.out.println(c1);
                    if ((is_check_result.get(0)).size() == 0) {
                        indices = c1.randomPiece("black", c1.board);
                        if (c1.board[indices.x][indices.y].piece instanceof BKing) {
//                            System.out.println("\nAm ales Regele si va trebui sa vad cum fac mutatrea :((");
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                ((BKing) c1.board[black_king.x][black_king.y].piece).move(c1.board, black_king.x, black_king.y, available_moves.get(0).x, available_moves.get(0).y);
                            }
                        } else {
                            indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                        }
                    } else if ((is_check_result.get(0)).size() == 1) {
//                        System.out.println("\nRegele trebuie sa mute sau alta piesa trebuie sa intervina");
                        System.out.println(available_moves.get(1).x + " " + available_moves.get(1).y);
                        indices = ((BKing) c1.board[black_king.x][black_king.y].piece).take_or_enter_attack(c1.board, black_king.x, black_king.y, available_moves,is_check_result.get(0).get(0));
                    } else {
//                        System.out.println("\nRegele trebuie sa mute");
                        if (available_moves.size() == 0) {
                            System.out.println("resign");
                            System.out.flush();
                        } else {
                            ((BKing) c1.board[black_king.x][black_king.y].piece).move(c1.board, black_king.x, black_king.y, available_moves.get(0).x, available_moves.get(0).y);
                        }
                    }
                } else {
//                    indices = c1.randomPawn("white", c1.board);
//                    indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                    IndexPair white_king = obj.find_king(c1.board, "white");
                    ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) c1.board[white_king.x][white_king.y].piece).is_in_check(c1.board, white_king.x, white_king.y);
                    ArrayList<IndexPair> available_moves = ((WKing) c1.board[white_king.x][white_king.y].piece).verify_moves(c1.board, white_king.x, white_king.y, is_check_result.get(1));
                    System.out.println(c1);
                    if ((is_check_result.get(0)).size() == 0) {
                        indices = c1.randomPiece("white", c1.board);
                        if (c1.board[indices.x][indices.y].piece instanceof WKing) {
//                            System.out.println("\nAm ales Regele si va trebui sa vad cum fac mutatrea :((");
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                ((WKing) c1.board[white_king.x][white_king.y].piece).move(c1.board, white_king.x, white_king.y, available_moves.get(0).x, available_moves.get(0).y);
                            }
                        } else {
                            indices = c1.board[indices.x][indices.y].piece.move(c1.board, indices.x, indices.y);
                        }
                    } else if ((is_check_result.get(0)).size() == 1) {
//                        System.out.println("\nRegele trebuie sa mute sau alta piesa trebuie sa intervina");
                        System.out.println(available_moves.get(1).x + " " + available_moves.get(1).y);
                        indices = ((WKing) c1.board[white_king.x][white_king.y].piece).take_or_enter_attack(c1.board, white_king.x, white_king.y, available_moves,is_check_result.get(0).get(0));
                    } else {
//                        System.out.println("\nRegele trebuie sa mute");
                        if (available_moves.size() == 0) {
                            System.out.println("resign");
                            System.out.flush();
                        } else {
                            ((WKing) c1.board[white_king.x][white_king.y].piece).move(c1.board, white_king.x, white_king.y, available_moves.get(0).x, available_moves.get(0).y);
                        }
                    }
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
