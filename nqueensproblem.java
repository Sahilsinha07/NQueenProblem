import java.util.ArrayList;

class nqueensproblem {
    public static boolean isSafe(int rows, int columns, char[][] boards) {
        // checking the horizontal line
        for (int j = 0; j < boards.length; j++) {
            if (boards[rows][j] == 'Q') { // agar kisi column mae already queen baithi hui hae then we will return false
                                          // here because it is not save for the queen to sit here.
                return false;
            }
        }
        // checking the vertical line
        for (int i = 0; i < boards.length; i++) {
            if (boards[i][columns] == 'Q') {
                return false;
            }
        }
        // checking for upper left

        for (int c = columns, r = rows; c >= 0 && r >= 0; c--, r--) {
            if (boards[r][c] == 'Q') {
                return false;
            }
        }
        // checking for upper right

        for (int c = columns, r = rows; c < boards.length && r >= 0; c++, r--) {
            if (boards[r][c] == 'Q') {
                return false;
            }
        }
        // checking for lower left

        for (int c = columns, r = rows; c >= 0 && r < boards.length; r++, c--) {
            if (boards[r][c] == 'Q') {
                return false;
            }
        }
        // checking for lower right

        for (int c = columns, r = rows; c < boards.length && r < boards.length; c++, r++) {
            if (boards[r][c] == 'Q') {
                return false;
            }
        }
        return true;

    }

    public static void saveBoard(char[][] boards, ArrayList<ArrayList<String>> allBoards) { // this function is to save
                                                                                            // the final board
        String row = "";
        ArrayList<String> newboard = new ArrayList<>();
        for (int i = 0; i < boards.length; i++) {
            row = "";
            for (int j = 0; j < boards[0].length; j++) {
                if (boards[i][j] == 'Q') {
                    row += 'Q';
                } else {
                    row += '.';
                }
                // newboard.add(row);
            }
            newboard.add(row);
            // allBoards.add(newboard);
        }
        allBoards.add(newboard);
    }

    public static void helper(char[][] boards, ArrayList<ArrayList<String>> allBoards, int columns) {
        // this is our recursive function
        // first we need to place the queen in eveery columns. so ik ik karke haar rows
        // mae check karenge kidhr kidhr queen daal sakte hae
        // length of the board will be n
        if (columns == boards.length) { // this is the base condition
            saveBoard(boards, allBoards);
            return;
        }
        for (int rows = 0; rows < boards.length; rows++) {
            // first we need to check whether the position is save for queen or not
            if (isSafe(rows, columns, boards)) {
                boards[rows][columns] = 'Q';
                helper(boards, allBoards, columns + 1);
                // when we will come back using backtracking afte finding out the further
                // solutions are wrong then we have to remove the queen from her position.
                boards[rows][columns] = '.';
            }
        }

    }

    public static ArrayList<ArrayList<String>> solveNQueens(int n) { // this is function with return type
                                                                     // list<list<String>> and name solveNQueens and
                                                                     // argument int n.
        ArrayList<ArrayList<String>> allBoards = new ArrayList<>(); // this is where our solutions will be stored.
        char[][] boards = new char[n][n]; // this is our chess board
        helper(boards, allBoards, 0); // calling the helper function
        return allBoards;

    }

    public static void main(String args[]) {
        System.out.println(solveNQueens(4));
    }
}
