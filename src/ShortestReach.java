import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestReach {

    private static int N, M, S, Answer;
    private static UndirectedWeightedGraph graph;
    private static int[] dist;

    public static void main(String[] args) throws FileNotFoundException {
	/*
	 * Enter your code here. Read input from STDIN. Print output to STDOUT.
	 * Your class should be named Solution.
	 */

	Scanner sc = new Scanner(new FileInputStream("inputShortestReach.txt"));

	int T = sc.nextInt();

	for (int test_case = 0; test_case < T; test_case++) {
	    N = sc.nextInt();
	    M = sc.nextInt();

	    graph = new UndirectedWeightedGraph(N);

	    for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
		    graph.addEdge(i, j, 0);
		}
	    }

	    for (int i = 0; i < M; i++) {
		sc.nextLine();
		graph.addEdge(sc.nextInt() - 1, sc.nextInt() - 1, 6);
	    }

	    S = sc.nextInt() - 1;

	    dist = countMSWithDjikstra(S);

	    for (int i = 0; i < dist.length; i++) {
		if (i == S) {
		    continue;
		}
		if (dist[i] == Integer.MAX_VALUE)
		    System.out.print(-1 + " ");
		else
		    System.out.print(dist[i] + " ");
	    }
	    System.out.println();

	}
    }

    public static int[] countMSWithDjikstra(int src) {

	int[] distance = new int[N];
	boolean[] shortPath = new boolean[N];

	for (int i = 0; i < N; i++) {
	    distance[i] = Integer.MAX_VALUE;
	    shortPath[i] = false;
	}

	distance[src] = 0;

	for (int count = 0; count < N - 1; count++) {
	    int u = minDistance(distance, shortPath);
	    shortPath[u] = true;
	    for (int v = 0; v < N; v++) {
		if (!shortPath[v] && graph.getWeight(u, v) > 0
			&& distance[u] != Integer.MAX_VALUE
			&& (distance[u] + graph.getWeight(u, v)) < distance[v]) {

		    distance[v] = distance[u] + graph.getWeight(u, v);
		}
	    }
	}

	return distance;
    }

    public static int minDistance(int[] dist, boolean[] shortPath) {
	int min = Integer.MAX_VALUE;
	int min_index = 0;

	for (int v = 0; v < N; v++) {
	    if (!shortPath[v] && dist[v] <= min) {
		min = dist[v];
		min_index = v;
	    }
	}
	return min_index;
    }

}

class UndirectedWeightedGraph {
    private int[][] adjacencyMatrix;
    private int vertexCount;

    public UndirectedWeightedGraph(int vCount) {
	this.vertexCount = vCount;
	this.adjacencyMatrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j, int w) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    adjacencyMatrix[i][j] = w;
	    adjacencyMatrix[j][i] = w;
	}
    }

    public void removeEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount) {
	    adjacencyMatrix[i][j] = 0;
	    adjacencyMatrix[j][i] = 0;
	}
    }

    public int getWeight(int i, int j) {
	return adjacencyMatrix[i][j];
    }

    public int isEdge(int i, int j) {
	if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
	    return adjacencyMatrix[i][j];
	else
	    return 0;
    }

}