package resources.backtracking;

public class NQueenProblem {
    static final int N = 4;
    static final boolean singleSolution = true;

    //Function to check whether it is safe to place a queen at board(row, col).
    static boolean isSafe(int rows[], int row, int col) {
        // rows[i] contains row value for column i
        int i, j;

        for (i = 0; i < col; i++) {
            if (rows[i] == row)   // row not safe
                return false;
            if (Math.abs(i - col) == Math.abs(rows[i] - row)) //diagonal not safe
                return false;
        }
        return true;
    }

    static boolean solveNQUtil(int rows[], int col) {
        if (col >= N) {
            //This means all columns are completed.
            if (singleSolution) return true;
            //printSolution(rows);
            printFancy(rows);
        }
        //In the current column col, we are trying our option at row 0 to N-1.
        for (int i = 0; i < N; i++) {
            if (isSafe(rows, i, col)) {
                rows[col] = i;         //Found a good row, Place Queen, Go FWD.
                if (solveNQUtil(rows, col + 1))
                    return true;
            }
        }
        return false;
    }

    static void printSolution(int rows[]) {
        for (int i = 0; i < N; i++) {
            System.out.print("(" + i + ","  + rows[i] + ")");
        }
        System.out.println();
    }

    static void printFancy(int rows[]) {
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[rows[i]][i] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int rows[] = new int[N];

        if (!solveNQUtil(rows, 0)) {
            System.out.println("\tPrinted all Solutions!");
            return;
        }
        System.out.println("\tThe very first solution!");
        //printSolution(rows);
        printFancy(rows);
    }
}