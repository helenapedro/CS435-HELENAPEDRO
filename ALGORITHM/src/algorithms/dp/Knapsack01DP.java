package algorithms.dp;

import java.util.*;

public class Knapsack01DP {
    // Estrutura de um caso de teste
    static class Case {
        String name;
        int[] values, weights;
        int capacity;

        Case(String name, int[] values, int[] weights, int capacity) {
            this.name = name;
            this.values = values;
            this.weights = weights;
            this.capacity = capacity;
        }
    }

    // Executa um caso, imprime a tabela e reconstrói a solução
    static void runCase(Case c) {
        int n = c.values.length;
        int W = c.capacity;

        int[][] dp = new int[n + 1][W + 1];
        boolean[][] take = new boolean[n + 1][W + 1]; // para reconstrução

        // Preenchimento da DP
        for (int i = 1; i <= n; i++) {
            int val = c.values[i - 1];
            int wt  = c.weights[i - 1];
            for (int w = 0; w <= W; w++) {
                // não pegar
                int best = dp[i - 1][w];
                boolean choose = false;

                // tentar pegar se couber
                if (wt <= w) {
                    int candidate = dp[i - 1][w - wt] + val;
                    if (candidate > best) {
                        best = candidate;
                        choose = true;
                    }
                }
                dp[i][w] = best;
                take[i][w] = choose;
            }
        }

        // Impressão da tabela
        System.out.println("\n=== " + c.name + " ===");
        printHeader(W);
        for (int i = 0; i <= n; i++) {
            if (i == 0) System.out.printf("%-8s", "Ø");
            else System.out.printf("%-8s", itemLabel(c.values[i-1], c.weights[i-1]));
            for (int w = 0; w <= W; w++) {
                System.out.printf("%4d", dp[i][w]);
            }
            System.out.println();
        }

        // Reconstruir itens escolhidos
        List<Integer> picked = new ArrayList<>();
        int w = W;
        for (int i = n; i >= 1; i--) {
            if (take[i][w]) {              // se decidiu pegar i-ésimo
                picked.add(i - 1);         // índice real do item
                w -= c.weights[i - 1];
            }
        }
        Collections.reverse(picked);

        // Resumo da solução
        int totalValue = dp[n][W];
        int totalWeight = picked.stream().mapToInt(idx -> c.weights[idx]).sum();

        System.out.println("\nValor ótimo = " + totalValue + " | Capacidade = " + W);
        System.out.println("Itens escolhidos (valor|peso):");
        for (int idx : picked) {
            System.out.println(" - " + itemLabel(c.values[idx], c.weights[idx]));
        }
        System.out.println("Peso total = " + totalWeight);
    }

    private static String itemLabel(int value, int weight) {
        return "(" + value + "|" + weight + ")";
    }

    private static void printHeader(int W) {
        System.out.printf("%-8s", "");
        for (int w = 0; w <= W; w++) System.out.printf("%4d", w);
        System.out.println();
    }

    public static void main(String[] args) {
        Case hw = new Case(
                "Caso hw",
                new int[]{25, 12, 24, 16, 28},   // values
                new int[]{5, 6, 8, 2, 7},     // weights
                20                       // Wmax (capacity)
        );

        Case c0 = new Case(
                "Caso 0",
                new int[]{2, 3, 4, 5},   // valores
                new int[]{1, 2, 3, 4},     // pesos
                5                       // capacidade
        );

        // Caso 1: exemplo didático
        Case c1 = new Case(
                "Caso 1: Itens pequenos",
                new int[]{6, 10, 12},   // valores
                new int[]{1, 2, 3},     // pesos
                5                       // capacidade
        );

        // Caso 2: típico knapsack
        Case c2 = new Case(
                "Caso 2: Knapsack clássico",
                new int[]{60, 100, 120},
                new int[]{10, 20, 30},
                50
        );

        // Caso 3: mistura de itens
        Case c3 = new Case(
                "Caso 3: Mix",
                new int[]{20, 5, 10, 40, 15, 25},
                new int[]{1, 2, 3, 8, 7, 4},
                10
        );

        runCase(hw);
        //runCase(c0);
//        runCase(c1);
//        runCase(c2);
//        runCase(c3);

        // Dica: para rodar com seus próprios dados:
        // int[] values = { ... };
        // int[] weights = { ... };
        // int W = ...;
        // runCase(new Case("Meu Caso", values, weights, W));
    }
}

