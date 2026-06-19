import java.util.*;
// C03 - Prim's MST and Bridge Detection
public class PrimsMSTBridgeDetection {

    static class Edge {
        String to;
        int cost;

        Edge(String to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        String vertex;
        String parent;
        int cost;

        Node(String vertex, String parent, int cost) {
            this.vertex = vertex;
            this.parent = parent;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {

        Map<String, List<Edge>> graph = new HashMap<>();

        addEdge(graph, "A", "B", 2);
        addEdge(graph, "B", "C", 2);
        addEdge(graph, "C", "G", 3);
        addEdge(graph, "F", "G", 4);
        addEdge(graph, "E", "F", 2);
        addEdge(graph, "D", "E", 3);
        addEdge(graph, "A", "D", 7);
        addEdge(graph, "B", "M", 3);
        addEdge(graph, "M", "E", 4);
        addEdge(graph, "A", "M", 4);
        addEdge(graph, "M", "C", 5);
        addEdge(graph, "M", "G", 6);

        primMST(graph, "M");
    }

    static void addEdge(Map<String, List<Edge>> graph,
                        String u, String v, int cost) {

        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        graph.get(u).add(new Edge(v, cost));
        graph.get(v).add(new Edge(u, cost));
    }

    static void primMST(Map<String, List<Edge>> graph,
                        String start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        pq.add(new Node(start, "-", 0));

        int totalCost = 0;

        System.out.println("MST Edges:");

        while (!pq.isEmpty()) {

            Node curr = pq.poll();

            if (visited.contains(curr.vertex))
                continue;

            visited.add(curr.vertex);

            if (!curr.parent.equals("-")) {
                System.out.println(
                        curr.parent + " - " +
                        curr.vertex + " : " +
                        curr.cost);
                totalCost += curr.cost;
            }

            for (Edge edge : graph.get(curr.vertex)) {

                if (!visited.contains(edge.to)) {

                    pq.add(new Node(
                            edge.to,
                            curr.vertex,
                            edge.cost));
                }
            }
        }

        System.out.println("\nTotal MST Cost = "
                           + totalCost + " crore");
    }
}