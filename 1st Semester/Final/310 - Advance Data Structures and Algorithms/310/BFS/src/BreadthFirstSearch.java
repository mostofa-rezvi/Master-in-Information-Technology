import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

  enum Color {
    WHITE, GRAY, BLACK
  }

  public void bfs(Graph G, int s) {
    // Data from pseudocode: color[V], prev[V], d[V]
    int V = G.getV();
    Color[] color = new Color[V];
    int[] prev = new int[V];
    int[] d = new int[V]; // d[u] stores the distance from source 's' to 'u'

    // --- Start of BFS algorithm ---

    // Step 1: Initialization of all vertices except the source
    // for each vertex u  V-{s}
    for (int u = 0; u < V; u++) {
      if (u != s) {
        color[u] = Color.WHITE;
        prev[u] = -1; // Using -1 to represent NIL
        d[u] = Integer.MAX_VALUE; // Using MAX_VALUE to represent infinity
      }
    }

    // Step 2: Initialization of the source vertex
    color[s] = Color.GRAY;
    d[s] = 0;
    prev[s] = -1; // Source has no predecessor (NIL)

    // Step 3: Initialize the queue
    Queue<Integer> Q = new LinkedList<>();
    // ENQUEUE(Q, s);
    Q.add(s);

    // Step 4: Process the queue until it is empty
    // While(Q not empty)
    while (!Q.isEmpty()) {
      // u = DEQUEUE(Q);
      int u = Q.poll();

      // Explore neighbors of u
      // for each v  adj[u]
      for (int v : G.getAdj()[u]) {
        // if (color[v] == WHITE)
        if (color[v] == Color.WHITE) {
          color[v] = Color.GRAY;
          d[v] = d[u] + 1;
          prev[v] = u;
          // Enqueue(Q, v);
          Q.add(v);
        }
      }
      // color[u] = BLACK;
      color[u] = Color.BLACK;
    }

    // --- End of BFS algorithm ---

    // Print the results for verification
    printResults(s, V, d, prev);
  }

  /**
   * Helper method to print the results of the BFS traversal.
   */
  private void printResults(int s, int V, int[] d, int[] prev) {
    System.out.println("BFS Results starting from source vertex " + s + ":");
    System.out.println("------------------------------------------");
    System.out.println("Vertex | Distance from Source | Predecessor");
    System.out.println("------------------------------------------");
    for (int i = 0; i < V; i++) {
      String distance = (d[i] == Integer.MAX_VALUE) ? "∞" : String.valueOf(d[i]);
      String predecessor = (prev[i] == -1) ? "NIL" : String.valueOf(prev[i]);
      System.out.printf("%-6d | %-20s | %-11s\n", i, distance, predecessor);
    }
    System.out.println("------------------------------------------");
  }
}
