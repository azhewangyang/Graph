import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mingzhe.Jiang on 9/6/2016.
 */
public class GraphNode {
    // This is the GraphNode definition
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int k) {
        key = k;
        neighbors = new LinkedList<GraphNode>();
    }
}
