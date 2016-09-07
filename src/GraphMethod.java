import java.util.*;

/**
 * Created by SkyTouch on 9/6/16.
 */
public class GraphMethod {

    public static List<GraphNode> createGraph() {
        // Setup nodes
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);
        GraphNode g5 = new GraphNode(5);
        GraphNode g6 = new GraphNode(6);
        GraphNode g7 = new GraphNode(7);
        GraphNode g8 = new GraphNode(8);

        // Setup edges.
        g1.neighbors.add(g2);

        g2.neighbors.add(g5);
        g2.neighbors.add(g6);
        g2.neighbors.add(g3);

        g3.neighbors.add(g7);
        g3.neighbors.add(g4);

        g4.neighbors.add(g3);
        g4.neighbors.add(g8);

        g5.neighbors.add(g1);
        g5.neighbors.add(g6);

        g6.neighbors.add(g7);

        g7.neighbors.add(g6);
        g7.neighbors.add(g8);

        g8.neighbors.add(g8);

        // Load nodes into result list
        List<GraphNode> result = new ArrayList<GraphNode>();
        result.add(g1); result.add(g2); result.add(g3); result.add(g4);
        result.add(g5); result.add(g6); result.add(g7); result.add(g8);
        return result;
    }

    public static void print(List<GraphNode> list) {
        if (list == null) {
            System.out.println("Input graph is null");
            return;
        }

        int nodes = list.size();
        int edges = 0;
        for (GraphNode gn: list) {
            System.out.print(gn.key + " : ");
            for (GraphNode subGn: gn.neighbors) {
                edges++;
                System.out.print(subGn.key + ", ");
            }
            System.out.print("\n");
        }
        System.out.println("Total nodes: " + nodes + ". Total edges: " + edges);
    }

    public static void BFSPrint(List<GraphNode> graph) {
        if (graph == null) {
            System.out.println("Input graph is null");
            return;
        }
        System.out.print("BFS: ");

        Set<GraphNode> set = new HashSet<GraphNode>();
        Queue<GraphNode> q = new LinkedList<GraphNode>();

        for (int i = 0; i < graph.size(); i++) {
            if (!set.contains(graph.get(i))) {
                q.offer(graph.get(i));
                set.add(graph.get(i));
            }

            while (!q.isEmpty()) {
                GraphNode curr = q.poll();
                // Do something to this node
                System.out.print(curr.key + " > ");

                for (GraphNode gn : curr.neighbors) {
                    if (!set.contains(gn)) {
                        set.add(gn);
                        q.offer(gn);
                    }
                }
            }
        }
    }


    public static void DFSPrint(List<GraphNode> graph) {
        if (graph == null) return;

        Set<GraphNode> set = new HashSet<GraphNode>();
        System.out.print("DFS: ");

        for (int i = 0; i < graph.size(); i++) {
            DFSPrintHelper(graph.get(i), set);
        }
    }

    private static void DFSPrintHelper(GraphNode curr, Set<GraphNode> set) {
        if (!set.contains(curr)) { // Not visited yet.
            set.add(curr);
            System.out.print(curr.key + " > ");
            for (GraphNode gn: curr.neighbors) {
                DFSPrintHelper(gn, set);
            }
        }
    }

    public static List<GraphNode> deepCopyGraph(List<GraphNode> graph) {
        if (graph == null) return null;
        List<GraphNode> result = new ArrayList<GraphNode>();

        // BFS traversal:
        // Below code can only deep copy connected component from graph.get(0) node.
        // For other connected component separate from graph.get(0), below method cannot
        // copy them. There is a hole in this algorithm.
        /*
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        Map<GraphNode, GraphNode> map = new HashMap<GraphNode,GraphNode>();
        q.offer(graph.get(0));
        GraphNode newNode = new GraphNode(graph.get(0).key);
        map.put(graph.get(0), newNode);
        result.add(graph.get(0));
        while(!q.isEmpty()) {
            GraphNode curr = q.poll();
            GraphNode newCurr = map.get(curr);

            for (GraphNode gn: curr.neighbors) {
                if(!map.containsKey(gn)) {
                    // Construct the node
                    // Put pairs into map and final result
                    newNode = new GraphNode(gn.key);
                    map.put(gn,newNode);
                    result.add(newNode);

                    // Establish edges for this newNode.
                    newCurr.neighbors.add(newNode);

                    // BFS
                    q.offer(gn);
                }
                else {
                    newCurr.neighbors.add(map.get(gn));
                }
            }
        }
        */

        // Let's try another way of doing this.
        Map<GraphNode, GraphNode> map = new HashMap<GraphNode,GraphNode>();
        for (GraphNode gn: graph) {
            GraphNode newNode = null;
            GraphNode subNewNode = null;
            if (!map.containsKey(gn)) {
                newNode = new GraphNode(gn.key);
                map.put(gn,newNode);
                result.add(newNode);
            }
            else {
                newNode = map.get(gn);
            }

            for (GraphNode subGn: gn.neighbors) {
                if (!map.containsKey(subGn)) {
                    subNewNode = new GraphNode(subGn.key);
                    map.put(subGn, subNewNode);
                    result.add(subNewNode);
                    newNode.neighbors.add(subNewNode);
                }
                else {
                    subNewNode = map.get(subGn);
                    newNode.neighbors.add(subNewNode);
                }
            }
        }

        return result;
    }
}
