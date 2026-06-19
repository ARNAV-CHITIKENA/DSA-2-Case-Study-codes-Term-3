import java.util.*;

class Edge {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

public class C04 {

    static int[][] bellmanFordWithPath(int n, List<Edge> edges, int source) {

        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        // V-1 Relaxation Passes
        for (int iter = 0; iter < n - 1; iter++) {

            boolean changed = false;

            for (Edge e : edges) {

                if (dist[e.u] != Integer.MAX_VALUE &&
                        dist[e.u] + e.weight < dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.weight;
                    parent[e.v] = e.u;
                    changed = true;
                }
            }

            // Early Termination
            if (!changed)
                break;
        }

        // Negative Cycle Check
        for (Edge e : edges) {

            if (dist[e.u] != Integer.MAX_VALUE &&
                    dist[e.u] + e.weight < dist[e.v]) {

                throw new RuntimeException(
                        "Negative Weight Cycle Detected");
            }
        }

        return new int[][] { dist, parent };
    }

    // Path Reconstruction
    static List<Integer> reconstructPath(int[] parent, int target) {

        LinkedList<Integer> path = new LinkedList<>();

        int curr = target;

        while (curr != -1) {
            path.addFirst(curr);
            curr = parent[curr];
        }

        return path;
    }

    public static void main(String[] args) {

        int V = 7;

        List<Edge> edges = new ArrayList<>();

        // IND=0, KOR=1, MGR=2, HSR=3,
        // BTM=4, JPN=5, EC=6

        edges.add(new Edge(0, 1, 8));    // IND -> KOR
        edges.add(new Edge(0, 3, 5));    // IND -> HSR
        edges.add(new Edge(1, 2, 7));    // KOR -> MGR
        edges.add(new Edge(1, 4, -5));   // KOR -> BTM
        edges.add(new Edge(3, 4, 6));    // HSR -> BTM
        edges.add(new Edge(3, 5, -3));   // HSR -> JPN
        edges.add(new Edge(2, 4, 4));    // MGR -> BTM
        edges.add(new Edge(2, 6, 10));   // MGR -> EC
        edges.add(new Edge(4, 6, 8));    // BTM -> EC
        edges.add(new Edge(5, 6, 14));   // JPN -> EC

        int source = 0; // IND

        int[][] result = bellmanFordWithPath(V, edges, source);

        int[] dist = result[0];
        int[] parent = result[1];

        String[] zones = {
                "IND", "KOR", "MGR",
                "HSR", "BTM", "JPN", "EC"
        };

        System.out.println("Shortest Distances from IND:");

        for (int i = 0; i < V; i++) {
            System.out.println(
                    zones[i] + " = " + dist[i]);
        }

        System.out.println("\nShortest Path IND -> EC:");

        List<Integer> path = reconstructPath(parent, 6);

        for (int node : path) {
            System.out.print(zones[node] + " ");
        }

        System.out.println();
    }
}