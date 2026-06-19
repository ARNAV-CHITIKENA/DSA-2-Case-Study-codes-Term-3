import java.util.*;


public class FloydWarshallUber {
 
static final int INF = 99999;


public static void floydWarshall(int[][] dist, int V) {


for(int k = 0; k < V; k++) {


for(int i = 0; i < V; i++) {


for(int j = 0; j < V; j++) {


if(dist[i][k] + dist[k][j] < dist[i][j]) {


dist[i][j] = dist[i][k] + dist[k][j];
}
}
}
}


System.out.println("Final Shortest Path Matrix:");


for(int i = 0; i < V; i++) {


for(int j = 0; j < V; j++) {


if(dist[i][j] == INF) System.out.print("INF\t");
else
System.out.print(dist[i][j] + "\t");
}
System.out.println();
}
}
 
public static void main(String[] args) {


int V = 5;


int[][] graph = {
{0,	300, INF, 200, INF}, // HYD
{300, 0,	250, INF, 500}, // BLR
{INF, 250, 0, 150, INF}, // CHE
{200, INF, 150, 0,  600}, // VJA
{INF, 500, INF, 600, 0}	// MUM
};


floydWarshall(graph, V);
}
}
