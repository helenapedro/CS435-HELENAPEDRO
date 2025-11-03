package algorithms.dp;

/*
To prevent redundant computation, solutions to subproblems can be stored in a table
and accessed whenever needed during execution of the algorithm.

Solution with DP: Memoization (armazenar e reutilizar)
A técnica de memoization diz:
    Se já calculei fib(k) uma vez, guardo o resultado para não precisar recalcular.
    No código RecursiveFibFast, isso é feito através do array table[].
 */
public class RecursiveFibFast {
    Integer[] table;

    public int fib(int n) {
        if (n < 0) return  -1;
        if (n == 0 || n == 1) return n;

        // set up table for storing computations
        table = new Integer[n + 1];
        table[0] = 0;
        table[1] = 1;

        return recursiveFib(n);
    }

    private int recursiveFib(int n) {
        System.out.println("Self-call at n = " + n);
        if (table[n - 1] == null) // verifies if it was calculated before calculating fib(n-1)
            table[n - 1] = recursiveFib(n - 1);
        table[n] = table[n - 1] + table[n - 2];
        return table[n];
    }

    public static void main(String[] args) {
        RecursiveFibFast fibCalculator = new RecursiveFibFast();

        int n = 10;
        int result = fibCalculator.fib(n);

        System.out.println("\nFibonacci(" + n + ") = " + result);
        System.out.println("Final table: ");
        for (int i = 0; i <= n; i++) {
            System.out.println("table[" + i + "] = " + fibCalculator.table[i]);
        }
    }
}
