package labs.w3d4;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Adjacency Matriz of the graph A..I, in the order A,B,C,D,E,F,G,H,I
        int[][] adj = {
                // A  B  C  D  E  F  G  H  I
                { 0, 1, 1, 0, 0, 1, 0, 0, 0 }, // A
                { 1, 0, 0, 0, 0, 1, 0, 0, 0 }, // B
                { 1, 0, 0, 0, 0, 1, 1, 0, 0 }, // C
                { 0, 0, 0, 0, 1, 0, 0, 0, 1 }, // D
                { 0, 0, 0, 1, 0, 0, 0, 0, 1 }, // E
                { 1, 1, 1, 0, 0, 0, 0, 1, 0 }, // F
                { 0, 0, 1, 0, 0, 0, 0, 1, 0 }, // G
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, // H
                { 0, 0, 0, 1, 1, 0, 0, 0, 0 }  // I
        };

        List<List<Integer>> components = ConnectedComponentsDFS.findComponents(adj);

        System.out.println("Connected Components:");
        int idx = 1;
        for (List<Integer> comp : components) {
            System.out.print("Component " + idx++ + ": { ");
            for (int v : comp) {
                System.out.print(ConnectedComponentsDFS.indexToVertex(v) + " ");
            }
            System.out.println("}");
        }
    }
}
