public class App {
    public static void main(String[] args) {
        // Create a graph with 8 vertices
        int numVertices = 8;
        Graph g = new Graph(numVertices);

        // Add edges to create the graph structure
        // This example is from the CLRS book (Figure 22.3)
        // Note: Vertices are 0-indexed in code, so we map r=0, s=1, t=2, u=3, v=4, w=5,
        // x=6, y=7
        g.addEdge(0, 1); // r - s
        g.addEdge(0, 4); // r - v
        g.addEdge(1, 5); // s - w
        g.addEdge(2, 3); // t - u
        g.addEdge(2, 5); // t - w
        g.addEdge(2, 6); // t - x
        g.addEdge(3, 6); // u - x
        g.addEdge(3, 7); // u - y
        g.addEdge(5, 6); // w - x
        g.addEdge(6, 7); // x - y

        // Create an instance of the BFS class and run the algorithm
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        int startNode = 1; // Start BFS from vertex 's' (index 1)
        bfs.bfs(g, startNode);
    }
}
