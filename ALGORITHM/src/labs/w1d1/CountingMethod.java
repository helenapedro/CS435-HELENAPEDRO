package labs.w1d1;

public class CountingMethod {
    public static int is235Array(int[] A) {
        int c2 = 0, c3 = 0, c5 = 0, cN = 0;
        for (int x : A) {
            if (x % 2 == 0) c2++;
            if (x % 3 == 0) c3++;
            if (x % 5 == 0) c5++;
            if (x % 2 != 0 && x % 3 != 0 && x % 5 != 0) cN++;
        }
        return (c2 + c3 + c5 + cN == A.length) ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 7};
        System.out.println(is235Array(arr)); // expected output: 1
    }
}
