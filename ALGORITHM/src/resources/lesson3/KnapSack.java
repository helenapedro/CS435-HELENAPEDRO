package resources.lesson3;

class KnapSack {
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // Making and initializing dp array
        int[] dp = new int[W + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int w = W; w >= 0; w--) {
                if (wt[i - 1] <= w)
                    // Finding the maximum value
                    dp[w]
                            = Math.max(dp[w],
                            dp[w - wt[i - 1]]+ val[i - 1]);
            }
            for (int k = 1; k < W + 1; k++) {
                System.out.print(" " + dp[k]);
            }
            System.out.println();
        }
        // Returning the maximum value of knapsack
        return dp[W];
    }

    // Driver code
    public static void main(String[] args)
    {
        int value[] = {15, 12, 9, 16, 17};
        int weight[] = {2, 5, 3, 4, 6};
        int W = 12;

        int n = value.length;
        System.out.print("Maximum Value is " + knapSack(W, weight, value, n));
    }
}