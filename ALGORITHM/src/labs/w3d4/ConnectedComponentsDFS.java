package labs.w3d4;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsDFS {
    // find all components of a graph given the adjacency matrix through DFS.
    public static List<List<Integer>> findComponents(int[][] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        List<List<Integer>> components = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                List<Integer> current = new ArrayList<>();
                dfs(v, adj, visited, current);
                components.add(current);
            }
        }

        return components;
    }

    // recursive DFS using adjacency matrix
    private static void dfs(int v, int[][] adj, boolean[] visited, List<Integer> current) {
        visited[v] = true;
        current.add(v);

        int n = adj.length;
        for (int w = 0; w < n; w++) {
            if (adj[v][w] == 1 && !visited[w]) {
                dfs(w, adj, visited, current);
            }
        }
    }

    // convert indices 0..8 into letters 'A'...'I'.
    static char indexToVertex(int i) {
        return (char) ('A' + i); // 0->A, 1->B, ...
    }

}
