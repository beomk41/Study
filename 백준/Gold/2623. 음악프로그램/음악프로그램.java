import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int vertex; // 정점
		Node next; // 인접 정점

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}

		@Override
		public String toString() {
			return " - " + vertex + ", next=" + next + "]";
		}
		
	}
	

	static int N, M;
	static Node[] adjList; // 인접 리스트
	static int[] inDegree; // 정점 별 차수

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N + 1];
		inDegree = new int[N + 1];

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int t = Integer.parseInt(st.nextToken());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			++inDegree[to];				
			for (int j=0; j<t-2; j++) {
				from = to;
				to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				++inDegree[to];
			}
		}
		
//		for (int i=1; i<adjList.length; i++) {
//			if(adjList[i] != null) System.out.println(i + " " + adjList[i].toString());
//		}
		
		ArrayList<Integer> orderList = topologySort();
		if (orderList.size() == N) {
			StringBuilder sb = new StringBuilder();
			for (int o : orderList) {
				sb.append(o).append("\n");
			}
			System.out.println(sb);
		} else { // 사이클 일 경우
			System.out.println("0");
		}
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i <= N; ++i) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);

			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);
			}
		}
		return orderList;
	}
}