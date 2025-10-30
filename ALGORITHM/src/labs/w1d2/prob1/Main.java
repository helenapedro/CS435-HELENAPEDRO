package labs.w1d2.prob1;

public class Main {
    public static void main(String[] args) {
        ThirdLargestLab.warmup();
        int[] sizes = {1_000, 2_000, 5_000, 10_000, 20_000, 50_000};

        // Print CSV header
        System.out.println("n,algo,time_ns");

        for (int n : sizes) {
            int trials = 5; // average a few runs to smooth noise

            long onePassSum = 0, treeMapSum = 0, threePassesSum = 0;

            for (int t = 0; t < trials; t++) {
                int[] a = ThirdLargestLab.randomArray(n);

                long dt1 = ThirdLargestLab.timeNanos(() -> {
                    int ans = ThirdLargestLab.thirdLargest_OnePass(a);
                    // (prevent JIT dead-code elimination)
                    if (ans == Integer.MIN_VALUE + 1) System.out.print("");
                });

                long dt2 = ThirdLargestLab.timeNanos(() -> {
                    int ans = ThirdLargestLab.thirdLargest_TreeMap(a);
                    if (ans == Integer.MIN_VALUE + 1) System.out.print("");
                });

                long dt3 = ThirdLargestLab.timeNanos(() -> {
                    int ans = ThirdLargestLab.thirdLargest_ThreePasses(a);
                    if (ans == Integer.MIN_VALUE + 1) System.out.print("");
                });

                onePassSum += dt1;
                treeMapSum += dt2;
                threePassesSum += dt3;
            }

            long onePassAvg = onePassSum / trials;
            long treeMapAvg = treeMapSum / trials;
            long threePassesAvg = threePassesSum / trials;

            System.out.println(n + ",onePass," + onePassAvg);
            System.out.println(n + ",treeMap," + treeMapAvg);
            System.out.println(n + ",threePasses," + threePassesAvg);
        }
    }
}
