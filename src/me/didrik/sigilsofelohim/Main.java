package me.didrik.sigilsofelohim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
    private static StringTokenizer st = new StringTokenizer("");

    private static Map<Character, Boolean[][][]> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        map.put('S', new Boolean[][][]{
                {
                        {false, true , true },
                        {true , true , false}
                },{
                        {true , false},
                        {true , true },
                        {false, true }
                }
        });
        map.put('I', new Boolean[][][]{
                {
                        {true , true , true , true }
                },{
                        {true },
                        {true },
                        {true },
                        {true }
                }
        });
        map.put('O', new Boolean[][][]{
                {
                        {true , true },
                        {true , true }
                }
        });
        map.put('Z', new Boolean[][][]{
                {
                        {true , true , false},
                        {false, true , true }
                },{
                        {false, true },
                        {true , true },
                        {true , false}
                }
        });
        map.put('J', new Boolean[][][]{
                {
                        {false, true },
                        {false, true },
                        {true , true }
                },{
                        {true , false, false},
                        {true , true , true }
                },{
                        {true , true },
                        {true , false},
                        {true , false}
                },{
                        {true , true , true },
                        {false, false, true }
                }
        });
        map.put('L', new Boolean[][][]{
                {
                        {true , false},
                        {true , false},
                        {true , true }
                },{
                        {true , true , true },
                        {true , false, false}
                },{
                        {true , true },
                        {false, true },
                        {false, true }
                },{
                        {false, false, true },
                        {true , true , true }
                }
        });
        map.put('T', new Boolean[][][]{
                {
                        {false, true , false},
                        {true , true , true }
                },{
                        {true , false},
                        {true , true },
                        {true , false}
                },{
                        {true , true , true },
                        {false, true , false}
                },{
                        {false, true },
                        {true , true },
                        {false, true }
                }
        });

        int[][] board = new int[readInt()][readInt()];
        String pieces = readString().toUpperCase();
        solve(board, pieces, 1);
    }

    private static void solve(int[][] board, String pieces, int depth) {
        if (depth == pieces.length() + 1) {
            printBoard(board);
            System.exit(0);
        }
        char c = pieces.charAt(depth - 1);
        for (Boolean[][] piece : map.get(c)) {
            for (int i = 0; i < board.length - piece.length + 1; i++) {
                for (int k = 0; k < board[0].length - piece[0].length + 1; k++) {
                    if (isRoom(piece, board, i, k)) {
                        place(piece, board, i, k, depth);
                        solve(board, pieces, depth + 1);
                        remove(piece, board, i, k);
                    }
                }
            }
        }
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[0].length; k++) {
                System.out.print(Integer.toHexString(board[i][k]).toUpperCase());
            }
            System.out.println("");
        }
    }

    private static void remove(Boolean[][] piece, int[][] board, int i, int k) {
        updateBoard(piece, board, i, k, 0);
    }

    private static void place(Boolean[][] piece, int[][] board, int i, int k, int depth) {
        updateBoard(piece, board, i, k, depth);
    }

    private static void updateBoard(Boolean[][] piece, int[][] board, int i, int k, int depth) {
        for (int ii = 0; ii < piece.length; ii++) {
            for (int kk = 0; kk < piece[0].length; kk++) {
                if (piece[ii][kk]) {
                    board[i + ii][k + kk] = depth;
                }
            }
        }
    }

    private static boolean isRoom(Boolean[][] piece, int[][] board, int i, int k) {
        for (int ii = 0; ii < piece.length; ii++) {
            for (int kk = 0; kk < piece[0].length; kk++) {
                if (piece[ii][kk]) {
                    if (board[i + ii][k + kk] > 0) return false;
                }
            }
        }
        return true;
    }

    private static String readString() throws Exception {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(stdin.readLine());
        }
        return st.nextToken();
    }

    private static int readInt() throws Exception {
        return Integer.parseInt(readString());
    }
}
