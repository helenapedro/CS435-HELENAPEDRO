package algorithms.greedy;

import java.util.*;

public class FractionalKnapsack {

    static class Item {
        int value, weight, index;
        double ratio; // value per weight

        Item(int value, int weight, int index) {
            this.value = value;
            this.weight = weight;
            this.index = index;
            this.ratio = (weight == 0 ? 0.0 : (double) value / weight);
        }
    }

    static class Pick {
        int index;          // índice original do item
        double takeWeight;  // quanto do peso foi pego (pode ser fracionado)
        double fraction;    // fração do item (0..1)
        int value;          // valor total do item
        int weight;         // peso total do item
        double ratio;       // valor/peso
        double contributedValue; // valor contribuído pela fração escolhida
    }

    static class Result {
        double totalValue;
        double totalWeight;
        List<Pick> picks = new ArrayList<>();
    }

    public static Result solve(int[] values, int[] weights, int capacity) {
        int n = values.length;
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) items.add(new Item(values[i], weights[i], i));

        // Ordena por valor/peso desc (greedy)
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        Result res = new Result();
        double remaining = capacity;

        for (Item it : items) {
            if (remaining <= 0) break;
            double canTake = Math.min(remaining, it.weight);
            double frac = (it.weight == 0) ? 0.0 : canTake / it.weight;

            if (canTake > 0) {
                Pick p = new Pick();
                p.index = it.index;
                p.takeWeight = canTake;
                p.fraction = frac;
                p.value = it.value;
                p.weight = it.weight;
                p.ratio = it.ratio;
                p.contributedValue = it.ratio * canTake;

                res.picks.add(p);
                res.totalValue += p.contributedValue;
                res.totalWeight += p.takeWeight;
                remaining -= canTake;
            }
        }
        return res;
    }

    // Utilitário: imprime um caso e a solução
    static void runCase(String name, int[] values, int[] weights, int capacity) {
        System.out.println("\n=== " + name + " ===");
        System.out.println("Capacidade: " + capacity);
        System.out.println("Itens (idx: value|weight | value/weight):");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("  %2d: %d|%d | %.3f%n", i, values[i], weights[i],
                    (weights[i] == 0 ? 0.0 : (double) values[i] / weights[i]));
        }

        Result r = solve(values, weights, capacity);

        System.out.println("\nEscolhas (ordenadas por valor/peso):");
        for (Pick p : r.picks) {
            System.out.printf("  idx %2d -> take %.2f of (%d|%d, ratio=%.3f)  => +%.3f de valor%n",
                    p.index, p.takeWeight, p.value, p.weight, p.ratio, p.contributedValue);
        }
        System.out.printf("%nPeso total = %.2f | Valor total = %.3f%n", r.totalWeight, r.totalValue);
    }

    public static void main(String[] args) {
        // from quiz
        int[] v_qz = {5, 6, 2, 1};
        int[] w_qz = {1, 3, 4, 2};
        int W_qz = 7;
        runCase("Fractional — Case quiz", v_qz, w_qz, W_qz);

        int[] v_hw = {25, 12, 24, 16, 28};
        int[] w_hw = {5, 6, 8, 2, 7};
        int W_hw = 20;
        runCase("Fractional — Caso hw", v_hw, w_hw, W_hw);

        // Outro caso didático
        int[] v1 = {60, 100, 120};
        int[] w1 = {10, 20, 30};
        int W1 = 50;
        runCase("Fractional — Caso clássico", v1, w1, W1);

        // Um terceiro caso curto
        int[] v2 = {6, 10, 12};
        int[] w2 = {1, 2, 3};
        int W2 = 5;
        runCase("Fractional — Itens pequenos", v2, w2, W2);
    }
}
