import java.util.LinkedList;
import java.util.List;

// A class to represent the graph structure using an adjacency list
class Graph {
  private int V; // Number of vertices
  private List<Integer>[] adj; // Adjacency list

  // Constructor
  @SuppressWarnings("unchecked")
  public Graph(int V) {
    this.V = V;
    adj = new LinkedList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  // Function to add an edge to the graph (for an undirected graph)
  public void addEdge(int u, int v) {
    adj[u].add(v);
    adj[v].add(u); // Since the graph is undirected
  }

  // Getter for the number of vertices
  public int getV() {
    return V;
  }

  // Getter for the adjacency list
  public List<Integer>[] getAdj() {
    return adj;
  }
}