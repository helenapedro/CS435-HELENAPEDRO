package resources.lesson6.sorting;

public class SortingTest {
    public static void main(String[] args) {
        // QUICK SORT
        System.out.println("____________QUICK SORT______________");
        int[] arr = {10, 7, 1, 8, 2, 6, 4, 3, 9, 8, 2};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        for (int n : arr) { System.out.print(n + " "); }

        // MERGE SORT
        System.out.println("\n____________MERGE SORT______________");
        int[] S = {38, 27, 43, 10};
        MergeSort.mergeSort(S, 0, S.length - 1);
        for (int j : S) System.out.print(j + " ");
        System.out.println();
    }
}
