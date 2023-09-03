import java.util.ArrayList;
import java.util.Arrays;

class revision {
    public static boolean isSafe(int row, int col, char[][] board) {
        // checking the horizontal line
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }
        // checking the vertical line
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // checking for upper left
        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // checking for upper right
        r = row;
        for (int c = col; c < board.length && r >= 0; c++, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // checking for lower left
        r = row;
        for (int c = col; c >= 0 && r < board.length; r++, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        // checking for lower right
        r = row;
        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void saveBoard(char[][] board, ArrayList<ArrayList<String>> allBoards) {
        ArrayList<String> newboard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                row.append(board[i][j]);
            }
            newboard.add(row.toString());
        }
        allBoards.add(newboard);
    }

    public static void helper(char[][] board, ArrayList<ArrayList<String>> allBoards, int col) {
        if (col == board.length) {
            saveBoard(board, allBoards);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                helper(board, allBoards, col + 1);
                board[row][col] = '.';
            }
        }
    }

    public static ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        helper(board, allBoards, 0);
        return allBoards;
    }

    public static void main(String args[]) {
        ArrayList<ArrayList<String>> result = solveNQueens(4);
        for (ArrayList<String> board : result) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
