package labs.w1d4;

import java.util.*;

public class Question2_SubsetSum {
    public static boolean existsSubsetSum(int[] S, int k) {
        if (k == 0) return true;
        return backtrackExists(S, 0, k);
    }

    private static boolean backtrackExists(int[] S, int i, int rem) {
        if (rem == 0) return true;
        if (i == S.length || rem < 0) return false;
        return backtrackExists(S, i + 1, rem - S[i]) ||
                backtrackExists(S, i + 1, rem);
    }

    public static List<Integer> findOneSubset(int[] S, int k) {
        List<Integer> path = new ArrayList<>();
        backtrackOne(S, 0, k, path);
        return path.isEmpty() ? null : path;
    }
    private static boolean backtrackOne(int[] S, int i, int rem, List<Integer> path) {
        if (rem == 0) return true;
        if (i == S.length || rem < 0) return false;
        path.add(S[i]);
        if (backtrackOne(S, i + 1, rem - S[i], path)) return true;
        path.remove(path.size() - 1);
        return backtrackOne(S, i + 1, rem, path);
    }

    public static List<List<Integer>> findAllSubsets(int[] S, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrackAll(S, 0, k, new ArrayList<>(), ans);
        return ans;
    }
    private static void backtrackAll(int[] S, int i, int rem, List<Integer> path, List<List<Integer>> ans) {
        if (rem == 0) { ans.add(new ArrayList<>(path)); return; }
        if (i == S.length || rem < 0) return;
        path.add(S[i]);
        backtrackAll(S, i + 1, rem - S[i], path, ans);
        path.remove(path.size() - 1);
        backtrackAll(S, i + 1, rem, path, ans);
    }

    public static void main(String[] args) {
        int[] S = {3, 4, 7, 8};
        int k = 15;

        System.out.println("QUESTION 2");
        System.out.println("Set S = " + Arrays.toString(S) + ", target k = " + k);

        System.out.println("(a) T or F: " + (existsSubsetSum(S, k) ? "T" : "F"));

        List<Integer> one = findOneSubset(S, k);
        System.out.println("(b) One solution: " + (one != null ? one : "None"));

        List<List<Integer>> all = findAllSubsets(S, k);
        System.out.println("(c) All solutions: " + (all.isEmpty() ? "None" : all));
    }
}
