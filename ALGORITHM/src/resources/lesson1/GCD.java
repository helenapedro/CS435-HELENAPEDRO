package resources.lesson1;

public class GCD {
    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int a = 13;
        int b = 8;
        System.out.println(gcd(a, b));
    }
}
