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
       

        Scanner scan = new Scanner(System.in);

        while (true) {
            String command = scan.nextLine();
            if (command.compareTo("xboard") == 0) {
                continue;
            } else if(command.compareTo("new") == 0) {
                c1.fill_board();
                engine_side = "black";
                xboard_side = "white";
                white_king_position = new IndexPair(7, 4);
                black_king_position = new IndexPair(0, 4);
                c1.castling_white = false;
                c1.castling_black = false;
                move_flag = true;
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
                        ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                            is_in_check(c1.board, black_king_position.x, black_king_position.y);
                        ArrayList<IndexPair> available_moves = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                            verify_moves(c1.board, black_king_position.x, black_king_position.y, is_check_result.get(1), is_check_result.get(0));
                        if ((is_check_result.get(0)).size() == 0) {
                            boolean flag_rocada = c1.castling_black;
                            indices = c1.randomPiece("black", c1.board, black_king_position);
                            if (indices.x != -1 && flag_rocada == false && c1.castling_black == true) {
                                black_king_position = indices;
                            } else if (indices.x != -1 && c1.board[indices.x][indices.y].piece instanceof BKing) {
                                    if (available_moves.size() == 0) {
                                        System.out.println("resign");
                                        System.out.flush();
                                    } else {
                                        white_king_position = ((BKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                            move(c1.board, white_king_position.x,white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                                    }
                                } 
                        } else if ((is_check_result.get(0)).size() == 1) {
                            indices = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                take_or_enter_attack(c1.board, black_king_position.x, black_king_position.y, available_moves,is_check_result.get(0).get(0));
                            if (indices.x == -1) {
                                if (available_moves.size() == 0) {
                                    System.out.println("resign");
                                    System.out.flush();
                                } else {
                                    black_king_position = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                        move(c1.board, black_king_position.x, black_king_position.y,
                                        available_moves.get(0).x, available_moves.get(0).y);
                                }
                            }
                        } else {
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                black_king_position = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                    move(c1.board, black_king_position.x, black_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                            }
                        }
                    } else {
                        ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                            is_in_check(c1.board, white_king_position.x, white_king_position.y);
                        ArrayList<IndexPair> available_moves = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                            verify_moves(c1.board, white_king_position.x, white_king_position.y, is_check_result.get(1), is_check_result.get(0));
                        if ((is_check_result.get(0)).size() == 0) {
                            boolean flag_rocada = c1.castling_white;
                            indices = c1.randomPiece("white", c1.board, white_king_position);
                            if (indices.x != -1 && flag_rocada == false && c1.castling_white == true) {
                                white_king_position = indices;
                            } else if (indices.x == -1 && c1.board[indices.x][indices.y].piece instanceof WKing) {
                                    if (available_moves.size() == 0) {
                                        System.out.println("resign");
                                        System.out.flush();
                                    } else {
                                        white_king_position = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                            move(c1.board, white_king_position.x,white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                                    }
                                }
                        } else if ((is_check_result.get(0)).size() == 1) {
                            indices = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                take_or_enter_attack(c1.board, white_king_position.x, white_king_position.y, available_moves,is_check_result.get(0).get(0));
                            if (indices.x == -1) {
                                if (available_moves.size() == 0) {
                                    System.out.println("resign");
                                    System.out.flush();
                                } else {
                                    white_king_position = ((BKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                        move(c1.board, white_king_position.x, white_king_position.y,
                                        available_moves.get(0).x, available_moves.get(0).y);
                                }
                            }
                        } else {
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                white_king_position = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                    move(c1.board, white_king_position.x, white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
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

                if (c1.board[start_x][start_y].piece.type == 'k') {
                    if (c1.board[start_x][start_y].piece.colour.compareTo("white") == 0) {
                        white_king_position.x = end_x;
                        white_king_position.y = end_y;
                    } else {
                        black_king_position.x = end_x;
                        black_king_position.y = end_y;
                    }
                }
                c1.board[end_x][end_y] = c1.board[start_x][start_y];
                c1.board[start_x][start_y] = new Square(start_x, start_y);
                if(move_flag) {
                    if (engine_side.compareTo("black") == 0) {
                        ArrayList<ArrayList<IndexPair>> is_check_result = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                            is_in_check(c1.board, black_king_position.x, black_king_position.y);
                        ArrayList<IndexPair> available_moves = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                            verify_moves(c1.board, black_king_position.x, black_king_position.y, is_check_result.get(1),is_check_result.get(0));
                        for (int i = 0; i < is_check_result.get(1).size();i++){
                            System.out.println(is_check_result.get(1).get(i) + " ");
                            System.out.flush();
                        }
                        if ((is_check_result.get(0)).size() == 0) {
                            boolean flag_rocada = c1.castling_black;
                            indices = c1.randomPiece("black", c1.board, black_king_position);
                            if (indices.x != -1 && flag_rocada == false && c1.castling_black == true) {
                                black_king_position = indices;
                            } else if (indices.x != -1 && c1.board[indices.x][indices.y].piece instanceof BKing) {
                                    if (available_moves.size() == 0) {
                                        System.out.println("resign");
                                        System.out.flush();
                                    } else {
                                        white_king_position = ((BKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                            move(c1.board, white_king_position.x,white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                                    }
                                } 
                        } else if ((is_check_result.get(0)).size() == 1) {
                            indices = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                take_or_enter_attack(c1.board, black_king_position.x, black_king_position.y, available_moves,is_check_result.get(0).get(0));
                            if (indices.x == -1) {
                                if (available_moves.size() == 0) {
                                    System.out.println("resign");
                                    System.out.flush();
                                } else {
                                    black_king_position = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                        move(c1.board, black_king_position.x, black_king_position.y,
                                        available_moves.get(0).x, available_moves.get(0).y);
                                }
                            }
                        } else {
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                black_king_position = ((BKing) c1.board[black_king_position.x][black_king_position.y].piece).
                                    move(c1.board, black_king_position.x, black_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                            }
                        }
                    } else {
                        ArrayList<ArrayList<IndexPair>> is_check_result = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                            is_in_check(c1.board, white_king_position.x, white_king_position.y);
                        ArrayList<IndexPair> available_moves = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                            verify_moves(c1.board, white_king_position.x, white_king_position.y, is_check_result.get(1), is_check_result.get(0));
                        if ((is_check_result.get(0)).size() == 0) {
                            boolean flag_rocada = c1.castling_white;
                            indices = c1.randomPiece("white", c1.board, white_king_position);
                            if (indices.x != -1 && flag_rocada == false && c1.castling_white == true) {
                                white_king_position = indices;
                            } else if (indices.x == -1 && c1.board[indices.x][indices.y].piece instanceof WKing) {
                                    if (available_moves.size() == 0) {
                                        System.out.println("resign");
                                        System.out.flush();
                                    } else {
                                        white_king_position = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                            move(c1.board, white_king_position.x,white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                                    }
                                }
                        } else if ((is_check_result.get(0)).size() == 1) {
                            indices = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                take_or_enter_attack(c1.board, white_king_position.x, white_king_position.y, available_moves,is_check_result.get(0).get(0));
                            if (indices.x == -1) {
                                if (available_moves.size() == 0) {
                                    System.out.println("resign");
                                    System.out.flush();
                                } else {
                                    white_king_position = ((BKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                        move(c1.board, white_king_position.x, white_king_position.y,
                                        available_moves.get(0).x, available_moves.get(0).y);
                                }
                            }
                        } else {
                            if (available_moves.size() == 0) {
                                System.out.println("resign");
                                System.out.flush();
                            } else {
                                white_king_position = ((WKing) c1.board[white_king_position.x][white_king_position.y].piece).
                                    move(c1.board, white_king_position.x, white_king_position.y, available_moves.get(0).x, available_moves.get(0).y);
                            }
                        }
                    }
                }
            } else if(command.compareTo("quit") == 0) {
                break;
            }
        }
    }
}
