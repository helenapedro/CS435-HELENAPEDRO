package resources.lesson2;

public class MergeSort {

    public static void mergeSort(int[] array, int n) {
        if (n < 2) { return; }

        int mid = n / 2;

        // Create the sub-arrays
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        // Copy the data for the sub-arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }

        for (int i = mid; i < n; i++) {
            right[i - mid] = array[i];
        }

        // recursive calls to divide
        mergeSort(left, mid);
        mergeSort(right, n - mid);

        // Combine the ordered sub-arrays
        merge(array, left, right, mid, n - mid);
    }

    private static void merge(
            int[] array, int[] left, int[] right, int leftLen, int rightLen) {

        int i = 0, j = 0, k = 0;

        // Compare and merge the element of left[] and right[] in array[]
        while (i < leftLen && j < rightLen) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            }
            else {
                array[k++] = right[j++];
            }
        }

        // Copy the remaining elements of left[] (if exists)
        while (i < leftLen) {
            array[k++] = left[i++];
        }

        // Copy the remaining elements of right[] (if exists)
        while (j < rightLen) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] data = {12, 11, 13, 5, 6, 7};
        mergeSort(data, data.length);

        System.out.println("sorted array:");
        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }
}