import java.util.Set;

/**
 * Created by Mingzhe on 5/6/2016.
 */
public class Graph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    /*
     * Create a V-vertex graph with no edges.
     */
    Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    /*
     * Read a graph from input stream in.
     */
    //Graph(In in) {
    //    this(in.readInt());
    //    int E = in.readInt();
    //    for (int i = 0; i < E; i++) {
    //        int v = int.readInt();
    //        int w = int.readInt();
    //        addEdge(v,w);
    //    }
    //}

    /*
     * Return the number of vertices.
     */
    public int V() {
        return V;
    }

    /*
     * Return the number of edges.
     */
    public int E() {
        return E;
    }

    /*
     * Add edge v-w to this graph
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /*
     * Return vertices adjacent to v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /*
     * String representation
     */
    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w: this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }


    // ----------------------- //
    //      Static Method      //
    // ----------------------- //
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w: G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G,v) > max)
                max = degree(G,v);
        return max;
    }

    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w: G.adj(v))
                if (v == w) count++;
        return count/2;
    }

}
