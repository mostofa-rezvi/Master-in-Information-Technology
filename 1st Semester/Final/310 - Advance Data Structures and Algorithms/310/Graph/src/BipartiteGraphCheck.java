import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BipartiteGraphCheck {
  
  
    public boolean isBipartite(int V, List<List<Integer>> adj) {
        // color array to store colors of vertices
        // 0: no color (unvisited)
        // 1: first color
        // -1: second color
        int[] color = new int[V]; // Java initializes int arrays to 0 by default

        // Loop through all vertices to handle disconnected components
        for (int i = 0; i < V; i++) {
            // If the vertex is not yet colored, start a new BFS traversal
            if (color[i] == 0) {
                // If the component starting at 'i' is not bipartite,
                // the whole graph is not bipartite.
                if (!checkComponentBipartiteBFS(i, V, adj, color)) {
                    return false;
                }
            }
        }

        // If all components are bipartite
        return true;
    }

    private boolean checkComponentBipartiteBFS(int startNode, int V, List<List<Integer>> adj, int[] color) {
        Queue<Integer> queue = new LinkedList<>();

        // Start with the first color and add to the queue
        color[startNode] = 1;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            // Iterate through all neighbors of the current vertex 'u'
            for (int v : adj.get(u)) {
                // Case 1: If neighbor 'v' is not yet colored
                if (color[v] == 0) {
                    // Color 'v' with the opposite color of 'u'
                    color[v] = -color[u];
                    queue.add(v);
                }
                // Case 2: If neighbor 'v' is already colored AND has the same color as 'u'
                else if (color[v] == color[u]) {
                    // This means there is an edge between two vertices of the same color,
                    // which violates the bipartite property (it indicates an odd-length cycle).
                    return false;
                }
                // Case 3 (implicit): If neighbor 'v' is already colored with the opposite color,
                // we do nothing, as this is expected in a bipartite graph.
            }
        }

        // If the BFS completes without finding any conflicts, this component is bipartite.
        return true;
    }

}
