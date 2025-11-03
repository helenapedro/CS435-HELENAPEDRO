package resources.lesson2;

public class FindMax {
    /**
     * Find the maximum value in an Array interval [low, high].
     * Implement the Divide & Conquer pattern.
     */
    static int FindMax(int[] A, int low, int high) {
        // 1. Base Case: If the interval has only one element, it is the maximum.
        if (low == high) return A[low];

        // 2. Divide
        int mid = (low + high) / 2;

        // 3. Conquer: solve the problem with the two halves (recursion).
        int left = FindMax(A, low, mid);
        int right = FindMax(A, mid + 1, high);

        // 4. Combine
        return max(left, right);
    }

    static int FindMax2(int[] A, int low, int high) {
        if (low == high) return A[low];

        int left = A[low];
        int right = FindMax2(A, low + 1, high);

        return max(left, right);
    }

    // Simple helper function.
    static int max(int left, int right) {
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] data = {5, 8, 2, 10, 4, 6};
        int result = FindMax2(data, 0, data.length - 1);
        System.out.println("Maximum value in the array: " + result); // Output: 10
    }
}
