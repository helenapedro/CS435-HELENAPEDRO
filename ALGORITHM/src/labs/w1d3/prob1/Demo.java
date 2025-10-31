package labs.w1d3.prob1;

public class Demo {
    public static void main(String[] args) {
        int n = 5;
        int[][] M = MatrixBuilders.buildM1(n, 5);
        System.out.println("Matrix (M1):");
        MatrixBuilders.printMatrix(M);

        int key1 = 23;
        System.out.println("\nsearchSS key=" + key1 + " => " + SearchAlgorithms.searchSS(M, key1));
        System.out.println("DACsearchSS key=" + key1 + " => " + SearchAlgorithms.DACsearchSS(M, key1));

        int key2 = 1000;
        System.out.println("\nsearchSS key=" + key2 + " => " + SearchAlgorithms.searchSS(M, key2));
        System.out.println("DACsearchSS key=" + key2 + " => " + SearchAlgorithms.DACsearchSS(M, key2));

        // Show another matrix variant
        int[][] M2 = MatrixBuilders.buildM2(n, 1);
        System.out.println("\nMatrix (M2):");
        MatrixBuilders.printMatrix(M2);
        int key3 = M2[3][1]; // pick an existing value
        System.out.println("\nsearchSS key=" + key3 + " on M2 => " + SearchAlgorithms.searchSS(M2, key3));
        System.out.println("DACsearchSS key=" + key3 + " on M2 => " + SearchAlgorithms.DACsearchSS(M2, key3));
    }
}
