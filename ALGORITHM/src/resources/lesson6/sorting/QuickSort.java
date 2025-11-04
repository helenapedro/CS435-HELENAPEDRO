package resources.lesson6.sorting;

public class QuickSort {
    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int p = partition(A, low, high);
            quickSort(A, low, p - 1);
            quickSort(A, p + 1, high);
        }
    }

    private static int partition(int[] A, int low, int high) {
        // (pick pivot as median of three)
        int pivot = A[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i]; A[i] = A[j]; A[j] = temp;
    }
}
