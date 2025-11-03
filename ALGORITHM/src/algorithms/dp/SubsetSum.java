package algorithms.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSum {
    public static List<Integer> subsetSum(int[] S, int k) {
        return recSubsetSum(S, k);
    }

    private static List<Integer> recSubsetSum(int[] S, int k) {
        if (S == null || S.length == 0) {
            if (k == 0) return new ArrayList<>(); // empty subset
            else return null; // no solution
        }

        int n = S.length;
        int last = S[n - 1];
        int[] remaining = Arrays.copyOf(S, n - 1); // remove last

        // 1️⃣ Try without including last
        List<Integer> T = recSubsetSum(remaining, k);
        if (T != null && sum(T) == k) {
            return T;
        }

        // 2️⃣ Try including last
        if (k >= last) {
            T = recSubsetSum(remaining, k - last);
            if (T != null && sum(T) == k - last) {
                T.add(last);
                return T;
            }
        }

        // 3️⃣ if non results
        return null;
    }

    private static int sum(List<Integer> list) {
        int s = 0;
        for (int val : list) s += val;
        return s;
    }

    public static void main(String[] args) {
        int[] S = {3, 2, 7, 1};
        int k = 6;

        System.out.println("Set: " + Arrays.toString(S));
        System.out.println("Sum target: " + k);

        List<Integer> result = subsetSum(S, k);

        if (result != null) {
            System.out.println("Found subset: " + result);
        } else {
            System.out.println("Non subset sum " + k);
        }
    }
}
