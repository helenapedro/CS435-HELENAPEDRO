package resources.lesson6.sorting;

public class MergeSort {
    /**
     * Public, stable merge sort. Guarantees O(n log n) time.
     * Uses a single auxiliary buffer allocated once.
     */
    public static void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * Compatibility wrapper matching the previous API (sort subrange [l..r]).
     */
    static void mergeSort(int[] arr, int l, int r) {
        if (arr == null) return;
        if (l < 0) l = 0;
        if (r >= arr.length) r = arr.length - 1;
        if (l >= r) return;
        int[] aux = new int[arr.length];
        sort(arr, aux, l, r);
    }

    private static void sort(int[] a, int[] aux, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        sort(a, aux, l, m);
        sort(a, aux, m + 1, r);
        // Small optimization: skip merge if already ordered
        if (a[m] <= a[m + 1]) return;
        merge(a, aux, l, m, r);
    }

    /**
     * Merges a[l..m] and a[m+1..r] back into a using aux as scratch.
     * Stable due to <= comparison when values are equal.
     */
    private static void merge(int[] a, int[] aux, int l, int m, int r) {
        System.arraycopy(a, l, aux, l, r - l + 1);
        int i = l;       // pointer into left half in aux
        int j = m + 1;   // pointer into right half in aux
        for (int k = l; k <= r; k++) {
            if (i > m) {
                a[k] = aux[j++];
            } else if (j > r) {
                a[k] = aux[i++];
            } else if (aux[i] <= aux[j]) { // keep stability
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
}
