import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        BipartiteGraphCheck checker = new BipartiteGraphCheck();

        // --- Test Case 1: A Bipartite Graph ---
        // 0 -- 1
        // | |
        // 3 -- 2
        System.out.println("--- Test Case 1: Bipartite Graph ---");
        int V1 = 4;
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++)
            adj1.add(new ArrayList<>());
        // Edges
        adj1.get(0).add(1);
        adj1.get(1).add(0);
        adj1.get(1).add(2);
        adj1.get(2).add(1);
        adj1.get(2).add(3);
        adj1.get(3).add(2);
        adj1.get(3).add(0);
        adj1.get(0).add(3);

        if (checker.isBipartite(V1, adj1)) {
            System.out.println("Result: The graph is Bipartite.");
        } else {
            System.out.println("Result: The graph is NOT Bipartite.");
        }
        System.out.println();

        // --- Test Case 2: A Non-Bipartite Graph (contains a 3-cycle) ---
        // 0 -- 1
        // | \ |
        // | \ |
        // 2 -- 3
        System.out.println("--- Test Case 2: Non-Bipartite Graph ---");
        int V2 = 4;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++)
            adj2.add(new ArrayList<>());
        // Edges
        adj2.get(0).add(1);
        adj2.get(1).add(0);
        adj2.get(1).add(2);
        adj2.get(2).add(1);
        adj2.get(2).add(3);
        adj2.get(3).add(2);
        adj2.get(0).add(2);
        adj2.get(2).add(0); // This edge creates a 3-cycle (0-1-2)

        if (checker.isBipartite(V2, adj2)) {
            System.out.println("Result: The graph is Bipartite.");
        } else {
            System.out.println("Result: The graph is NOT Bipartite.");
        }
        System.out.println();

        // --- Test Case 3: A Disconnected Bipartite Graph ---
        // Component 1: 0-1 Component 2: 2-3-4
        System.out.println("--- Test Case 3: Disconnected Bipartite Graph ---");
        int V3 = 5;
        List<List<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < V3; i++)
            adj3.add(new ArrayList<>());
        // Edges for component 1
        adj3.get(0).add(1);
        adj3.get(1).add(0);
        // Edges for component 2
        adj3.get(2).add(3);
        adj3.get(3).add(2);
        adj3.get(3).add(4);
        adj3.get(4).add(3);

        if (checker.isBipartite(V3, adj3)) {
            System.out.println("Result: The graph is Bipartite.");
        } else {
            System.out.println("Result: The graph is NOT Bipartite.");
        }
    }
}
