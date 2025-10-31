package labs.w1d3.prob1;

public class MatrixBuilders {

    // M1: simple row-major increasing values: base + i*n + j
    public static int[][] buildM1(int n, int base) {
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = base + i * n + j;
            }
        }
        return M;
    }

    // M2: grows by (n+1) per row, +1 per column (still increasing by rows & cols)
    public static int[][] buildM2(int n, int base) {
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = base + j + i * (n + 1);
            }
        }
        return M;
    }

    // M3: linear form A*i + B*j + C with A>0 and B>0 guarantees sorted rows/cols
    public static int[][] buildM3(int n, int A, int B, int C) {
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = A * i + B * j + C;
            }
        }
        return M;
    }

    public static void printMatrix(int[][] M) {
        for (int[] row : M) {
            StringBuilder sb = new StringBuilder();
            for (int v : row) {
                sb.append(String.format("%4d", v));
            }
            System.out.println(sb.toString());
        }
    }
}
