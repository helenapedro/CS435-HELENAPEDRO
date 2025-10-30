package labs.w1d1;

public class TestOposite {
    public static int is235Array(int[] A) {
        for (int x : A) {
            int div = 0;
            if (x % 2 == 0) div++;
            if (x % 3 == 0) div++;
            if (x % 5 == 0) div++;
            if (div >= 2) return 0;   // violates the rule (count would exceed n)
        }
        return 1; // no violations found
    }

    public static void main(String[] args) {
        System.out.println(is235Array(new int[]{2,3,4,5,7})); // 1
        System.out.println(is235Array(new int[]{6,7,8}));     // 0 (6 is /2 and /3)
    }
}
