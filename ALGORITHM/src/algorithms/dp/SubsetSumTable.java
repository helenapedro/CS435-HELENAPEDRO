package algorithms.dp;

public class SubsetSumTable {
    public static boolean subsetSum(int[] S, int k) {
        int n = S.length;
        boolean[][] dp = new boolean[n + 1][k + 1];

        // Step 1: Initialize base case
        for (int i = 0; i <= n; i++) dp[i][0] = true;

        // Step 2: Fill table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (S[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // can’t include element
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - S[i - 1]];
                }
            }
        }

        // Print the table
        printTable(dp, S, k);

        return dp[n][k];
    }

    private static void printTable(boolean[][] dp, int[] S, int k) {
        System.out.println("\nDP Table (rows = elements, cols = sum):");
        System.out.print("     ");
        for (int j = 0; j <= k; j++) System.out.printf("%3d", j);
        System.out.println();
        for (int i = 0; i <= S.length; i++) {
            if (i == 0) System.out.print("Ø | ");
            else System.out.printf("%d | ", S[i - 1]);
            for (int j = 0; j <= k; j++) {
                System.out.printf("%3s", dp[i][j] ? "T" : ".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] S = {3, 2, 7, 1};
        int k = 6;
        boolean result = subsetSum(S, k);
        System.out.println("\nSubset sum " + k + (result ? " exists ✅" : " does NOT exist ❌"));
    }
}
