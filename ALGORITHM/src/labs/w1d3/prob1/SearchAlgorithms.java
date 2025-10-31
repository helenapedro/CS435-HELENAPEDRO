package labs.w1d3.prob1;

public class SearchAlgorithms {

    /**
     * searchSS: "staircase" search in a matrix sorted by rows and columns (non-decreasing).
     * Time: O(n), Space: O(1).
     */
    public static boolean searchSS(int[][] M, int key) {
        int n = M.length;
        if (n == 0) return false;
        int i = 0;        // top row
        int j = n - 1;    // rightmost column

        while (i < n && j >= 0) {
            int cur = M[i][j];
            if (cur == key) return true;
            if (cur > key) {
                j--; // eliminate column j
            } else {
                i++; // eliminate row i
            }
        }
        return false;
    }

    /**
     * Public wrapper for DACsearchSS
     */
    public static boolean DACsearchSS(int[][] M, int key) {
        int n = M.length;
        if (n == 0) return false;
        return DACsearchSS(M, 0, n - 1, 0, n - 1, key);
    }

    /**
     * Divide-and-Conquer search.
     * Recurrence: T(n) = 3 T(n/2) + O(1) => Theta(n^{log_2 3}) ~ Theta(n^{1.585}).
     * Space: O(log n) due to recursion depth.
     */
    private static boolean DACsearchSS(int[][] M, int rs, int re, int cs, int ce, int key) {
        if (rs > re || cs > ce) return false;

        // Small region optimization: linear scan (avoids excessive recursion on thin strips)
        int rows = re - rs + 1;
        int cols = ce - cs + 1;
        if (rows <= 2 || cols <= 2) {
            for (int i = rs; i <= re; i++) {
                // staircase within submatrix bounds
                int j = ce;
                while (j >= cs) {
                    int cur = M[i][j];
                    if (cur == key) return true;
                    if (cur > key) j--;
                    else break; // row is increasing; rest of the row to the left is <= cur
                }
                // note: could continue scanning, but this shortcut is fine
            }
            // fallback: simple full scan on tiny block
            for (int i = rs; i <= re; i++) {
                for (int j = cs; j <= ce; j++) {
                    if (M[i][j] == key) return true;
                }
            }
            return false;
        }

        int midR = (rs + re) >>> 1;
        int midC = (cs + ce) >>> 1;
        int pivot = M[midR][midC];

        if (pivot == key) return true;

        if (key < pivot) {
            // eliminate Q4 (bottom-right). Search Q1, Q2, Q3.
            // Q1: [rs..midR-1][cs..midC-1]
            if (DACsearchSS(M, rs, midR - 1, cs, midC - 1, key)) return true;
            // Q2: [rs..midR-1][midC..ce]
            if (DACsearchSS(M, rs, midR - 1, midC, ce, key)) return true;
            // Q3: [midR..re][cs..midC-1]
            if (DACsearchSS(M, midR, re, cs, midC - 1, key)) return true;
            return false;
        } else {
            // key > pivot: eliminate Q1 (top-left). Search Q2, Q3, Q4.
            // Q2: [rs..midR][midC+1..ce]
            if (DACsearchSS(M, rs, midR, midC + 1, ce, key)) return true;
            // Q3: [midR+1..re][cs..midC]
            if (DACsearchSS(M, midR + 1, re, cs, midC, key)) return true;
            // Q4: [midR+1..re][midC+1..ce]
            if (DACsearchSS(M, midR + 1, re, midC + 1, ce, key)) return true;
            return false;
        }
    }
}
