package resources.backtracking;

public class PermutationStudent {
    static final int N = 3;
    static final boolean singleSolution = true;

    //Function to check whether it is safe to place a queen at slots(option, col).
    static boolean isSafe(int slots[], int option, int col) {

        // Check this option on the left side
        for (int i = 0; i < col; i++)
            if (slots[i] == option)
                return false;
        return true;
    }

    static boolean solveUtil(int slots[], int col) {
        //Trying to fill next column
        if (col >= N) {
            //This means all columns are completed.
            printSolution(slots);
            if (singleSolution) return true;
        }

        //In the current column col, we are trying our option from 1 to N.
        for (int option = 1; option <= N; option++) {
            if (isSafe(slots, option, col)) {
                slots[col] = option;      //Found a good option. Go FWD.
                if (solveUtil(slots, col + 1))
                    return true;
            }
        }
        return false;
    }

    static void printSolution(int slots[]) {
        for (int i = 0; i < N; i++)
            System.out.print(" " + slots[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int slots[] = new int[N];

        if (!solveUtil(slots, 0)) {
            System.out.println("\tAll done!");
            return;
        }
    }
}