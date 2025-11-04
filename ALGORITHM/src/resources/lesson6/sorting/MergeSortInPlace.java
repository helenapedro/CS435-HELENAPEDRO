package resources.lesson6.sorting;

public class MergeSortInPlace {
    private static int[] theArray;

    // public sorter
    public static int[] sort(int[] input) {
        int n = input.length;
        int[] tempStorage = new int[n];
        theArray = input;
        mergeSort(tempStorage, 0, n - 1);
        return theArray;
    }

    private static void mergeSort(int[] temp, int lower, int upper) {
        if (lower == upper) {
            return;
        } else {
            int mid = (lower + upper) / 2;
            mergeSort(temp, lower, mid);
            mergeSort(temp, mid + 1, upper);
            merge(temp, lower, mid + 1, upper);
        }
    }

    private static void merge(int[] t, int l, int m, int u){
    }
}
