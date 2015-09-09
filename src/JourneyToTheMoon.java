import java.io.*;
import java.util.*;

public class JourneyToTheMoon {
    
	private boolean[] isVisited;
	
	public JourneyToTheMoon(int numOfAstronauts) {
		isVisited = new boolean[numOfAstronauts];

		for (int i = 0; i < isVisited.length; ++i) {
			isVisited[i] = false;
		}
	}
    
   public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);

		int numOfAstronauts = scanner.nextInt();
		int numOfPairs = scanner.nextInt();

		JourneyToTheMoon solution = new JourneyToTheMoon(numOfAstronauts);

		// Buat daftar representasi sebuah graph dimana setiap key merepresentasikan 1 astronot
		// dan value dari setiap astronot (vertex) dalam graph adalah daftar kedekatan yang meliputi
		// astronot yg lain yg terkoneksi dengan current astronot(key), atau dari country yg sama.
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>(numOfAstronauts);

		for (int i = 0; i < numOfPairs; ++i) {
			int astronautA = scanner.nextInt();
			int astronautB = scanner.nextInt();
			solution.addEdge(graph, astronautA, astronautB);
		}
		
		// Lakukan dfs untuk mencari berapa jumlah komponen terkoneksi,
		// calculate kombinasi yang munkin untuk memilih 2 astronot dari country yg berbeda
		// print jawaban
		System.out.println(Long.toString(solution.dfs(graph)));
    }
	
	public long dfs(HashMap<Integer, ArrayList<Integer>> graph) {

		HashMap<Integer, Integer> connectedComponents = new HashMap<Integer, Integer>();
		Iterator<Integer> vertexIterator = graph.keySet().iterator();

		while (vertexIterator.hasNext()) {
			int currentVertex = vertexIterator.next();
			
			if (!isVisited[currentVertex]) {
				isVisited[currentVertex] = true;
				findConnectedComponents(graph, currentVertex, connectedComponents);
			}
		}
		
		long numOfAstronauts = (long)isVisited.length;
		long totalCombinations = (numOfAstronauts * (numOfAstronauts - 1)) / 2;
		long sameCombinations = 0;

		for (int vertexCount : connectedComponents.values()) {
			sameCombinations += (vertexCount * (vertexCount - 1)) / 2;
		}

		// Untuk mendapatkan jumlah kombinasi yg mungkin dari pemilihan 2 astronot
		// dari 2 country yg berbeda, ambil dari jumlah kombinasi dengan menghitung jumlah kombinasi
		// yang ada untuk memilih 2 astronot dari country yg sama dengan kombinasi yang mungkin untuk memilih 
		// 2 astronot dari jumlah total astronot.
		// I.e.: (N choose 2) - (Sum(C_i choose 2) for i = 1 to C), dimana
		// N adalah jumlah total astronot, 
		// C_i adalah jumlah astronot pada country yg terkoneksi(country) i
		// C adalah jumlah total komponen yg terkoneksi
		return totalCombinations - sameCombinations;
	}
	
	private void findConnectedComponents(HashMap<Integer, ArrayList<Integer>> graph, 
											int startVertex, HashMap<Integer, Integer> connectedComponents) {
		Stack<Integer> stack = new Stack<Integer>();
		
		// Gunakan vertex sebagai key pada connectedComponents map,
		// sebagai inisialisasi awal tambahkan vertex dengan jumlah 1
		connectedComponents.put(startVertex, 1);
		
		stack.push(startVertex);
		
		while (!stack.isEmpty()) {
			int vertex = stack.pop();

			ArrayList<Integer> adjacentVertices = graph.get(vertex);
			
			for (int v : adjacentVertices) {
				if (!isVisited[v]) {
					isVisited[v] = true;
					int count = connectedComponents.get(startVertex);
					connectedComponents.put(startVertex, ++count);
					stack.push(v);
				}
			}
			
		}
	}
	
	public void addEdge(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		updateAdjencyList(graph, vertex1, vertex2);
		updateAdjencyList(graph, vertex2, vertex1);
	}
	
	private void updateAdjencyList(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		ArrayList<Integer> adjencyList = graph.get(vertex1);
		
		if (adjencyList == null) {
			adjencyList = new ArrayList<Integer>();
		}
		
		adjencyList.add(vertex2);
		graph.put(vertex1, adjencyList);
	}    
    
}
