package labs.w3d4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentsBFS {
    public static List<List<Integer>>findComponentsBFS(int[][] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        List<List<Integer>> components = new ArrayList<>();

        for (int s = 0; s < n; s++) { // s -> start
            if(!visited[s]) {
                List<Integer> currComponent = new ArrayList<>();
                bfs(s, adj, visited, currComponent);
                components.add(currComponent);
            }
        }
        return components;
    }

    static void bfs(int s, int[][] adj, boolean[] visited, List<Integer> currentComponent) {
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            currentComponent.add(v);

            for(int w = 0; w < adj.length; w++) {
                if (adj[v][w] == 1 && !visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    static char indexToVertex(int index) {
        return (char) ('A' + index);
    }
}
