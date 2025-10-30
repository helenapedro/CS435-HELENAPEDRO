package labs.w1d2.prob1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ThirdLargestLab {
    // Algorithm 2: One pass
    public static int thirdLargest_OnePass(int[] A) {
        int max = Integer.MIN_VALUE;
        int preMax = Integer.MIN_VALUE;
        int prePreMax = Integer.MIN_VALUE;

        for (int x : A) {
            if (x >= max) {
                prePreMax = preMax;
                preMax = max;
                max = x;
            } else if (x >= preMax) {
                prePreMax = preMax;
                preMax = x;
            } else if (x >= prePreMax) {
                prePreMax = x;
            }
        }
        return prePreMax; // if n>=3 this is defined even with duplicates
    }

    // Algorithm 3: Ordered dictionary (TreeMap value->freq), then consume in DESC order
    public static int thirdLargest_TreeMap(int[] A) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int x : A) freq.merge(x, 1, Integer::sum);

        int seen = 0;
        for (int v : freq.descendingKeySet()) {
            int c = freq.get(v);
            for (int i = 0; i < c; i++) {
                seen++;
                if (seen == 3) return v;
            }
        }
        // If array length < 3, lab usually doesn’t ask for behavior; return min as sentinel
        return Integer.MIN_VALUE;
    }

    // Algorithm 1 three passes
    public static int thirdLargest_ThreePasses(int[] A) {
        int max1 = A[0];
        for (int x : A) if (x > max1) max1 = x;

        int max2 = Integer.MIN_VALUE;
        for (int x : A) if (x > max2 && x <= max1) max2 = x;

        int max3 = Integer.MIN_VALUE;
        for (int x : A) if (x > max3 && x <= max2) max3 = x;

        return max3;
    }

    // Tiny benchmark harness – prints CSV: n,algo,ns
    static int[] randomArray(int n) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = r.nextInt(-1_000_000, 1_000_001);
        return a;
    }

    // warm up a bit (very light)
    static void warmup() {
        int[] a = randomArray(10000);
        for (int i = 0; i < 5; i++) {
            thirdLargest_OnePass(a);
            thirdLargest_TreeMap(a);
            thirdLargest_ThreePasses(a);
        }
    }

    // time a Runnable-like lambda
    static long timeNanos(Runnable f) {
        long t0 = System.nanoTime();
        f.run();
        return System.nanoTime() - t0;
    }
}
