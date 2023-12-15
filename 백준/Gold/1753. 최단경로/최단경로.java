import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int idx; // 도착지점
    int cost; // 도착지점 가중치

    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {

    static int V, E, K, INF = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        for (int i=0; i<V+1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) { // 정점 양방향 x, 여러개 , 값 입력
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); // 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 가중치
            
            graph[u].add(new Node(v, w));
        }

        dijkstra();

        for (int i=1; i<dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }
    }

    private static void dijkstra () {
        visited = new boolean[V+1]; // 방문배열
        dist = new int[V+1]; // 최소거리
        Arrays.fill(dist, INF);
        dist[K] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));

        while(!pq.isEmpty()) {
            int curNode = pq.poll().idx;

            if(visited[curNode]) continue;
            visited[curNode] = true;

            for (Node next : graph[curNode]) {
                if (dist[next.idx] > dist[curNode] + next.cost) {
                    dist[next.idx] = dist[curNode] + next.cost;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}