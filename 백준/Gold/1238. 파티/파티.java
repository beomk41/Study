import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int idx;
    int cost;

    public Node (int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    public int compareTo(Node o) {
      if(this.cost != o.cost)
        return Integer.compare(this.cost, o.cost);
      return Integer.compare(this.idx, o.idx);
    }
}

public class Main {

    static int N, M, X, max = Integer.MIN_VALUE, INF = Integer.MAX_VALUE;
    static int[][] dist;
    static boolean[] visited;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()); // 도착지
        // 각각 1~N 까지 의 마을에서 X까지 도착하는데 이동 거리 필요

        graph = new ArrayList[N+1];
        for (int i=0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }
        dist = new int [N+1][N+1];

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        for(int i=1; i<N+1; i++){
            dijkstra(i);
        }
        for(int i=1; i<N+1; i++){
            max = Math.max(max, dist[i][X] + dist[X][i]);
        }

        System.out.println(max);
    }

    private static void dijkstra(int start) {
        visited = new boolean[N+1];
        Arrays.fill(dist[start], INF);
        dist[start][start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int curNode = pq.poll().idx;

            if(visited[curNode]) continue;
            visited[curNode] = true;

            for (Node next : graph[curNode]) {
                if (dist[start][next.idx] > dist[start][curNode] + next.cost) {
                    dist[start][next.idx] = dist[start][curNode] + next.cost;
                    pq.offer(new Node(next.idx, dist[start][next.idx]));
                }
            }
        }
    }
}