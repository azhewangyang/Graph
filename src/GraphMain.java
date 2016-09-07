import java.util.List;

/**
 * Created by Mingzhe on 5/8/2016.
 */
public class GraphMain {
    public static void main(String[] args) {
        //Graph g = new Graph(9);
        //System.out.println(g.toString());
        //System.out.println("WTF!");
        List<GraphNode> graph = GraphMethod.createGraph();
        GraphMethod.print(graph);
        GraphMethod.BFSPrint(graph);
        GraphMethod.DFSPrint(graph);
    }
}
