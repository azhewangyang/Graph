import java.util.Queue;
import java.util.Stack;

/**
 * Created by Mingzhe on 5/8/2016.
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int w = queue.poll();
            if (!marked[w]) {
                edgeTo[w] = v;
                marked[w] = true;
                queue.add(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != v; x = edgeTo[v])
            path.push(x);
        path.push(s);
        return path;
    }
}
