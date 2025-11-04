package resources.lesson6;

import java.util.Arrays;

public class QuickSelect {
    // API 1: k-ésimo menor em todo o array (k é 1-based)
    public static int quickSelect(int[] A, int k) {
        if (A == null || A.length == 0) throw new IllegalArgumentException("Array vazio");
        if (k < 1 || k > A.length) throw new IllegalArgumentException("k fora do intervalo");
        return quickSelect(A, 0, A.length - 1, k);
    }

    // API 2: k-ésimo menor restrito a [low..high] (k é 1-based nesse intervalo)
    public static int quickSelect(int[] A, int low, int high, int k) {
        while (low <= high) {
            int p = partitionMedian3(A, low, high); // posição final do pivô
            int m = p - low + 1;                    // qtd de elementos <= pivô em [low..p]
            if (k == m) {
                return A[p];
            } else if (k < m) {
                high = p - 1;
            } else {
                low = p + 1;
                k -= m;
            }
        }
        // fallback (não deveria chegar aqui)
        return A[low];
    }

    // Particionamento estilo dos slides: median-of-three, pivô vai ao fim, i/j convergem, swap final do pivô.
    private static int partitionMedian3(int[] A, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivotIndex = medianIndex(A, low, mid, high);
        swap(A, pivotIndex, high);
        int pivot = A[high];

        int i = low, j = high - 1;
        while (i <= j) {
            while (i <= j && A[i] < pivot) i++;
            while (i <= j && A[j] > pivot) j--;
            if (i <= j) {
                swap(A, i, j);
                i++; j--;
            }
        }
        swap(A, i, high); // pivô vai para posição final
        return i;
    }

    // Mediana de três (retorna o índice cujo valor é a mediana)
    private static int medianIndex(int[] A, int a, int b, int c) {
        int va = A[a], vb = A[b], vc = A[c];
        if ((va <= vb && vb <= vc) || (vc <= vb && vb <= va)) return b;
        if ((vb <= va && va <= vc) || (vc <= va && va <= vb)) return a;
        return c;
    }

    private static void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i]; A[i] = A[j]; A[j] = t;
    }

    // Demo
    public static void main(String[] args) {
        int[] arr = {10,7,1,8,2,6,4,3,9,8,2};
        int k = 5; // 5º menor
        int kth = quickSelect(arr.clone(), k);
        System.out.println("5º menor = " + kth); // esperado: 4
        // Se quiser conferir: ordene e veja arr[k-1]
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println("Verificação sort: " + sorted[k-1]);
    }
}
