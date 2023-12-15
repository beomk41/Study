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

    static int V, E, v1, v2 ,INF = Integer.MAX_VALUE, min = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

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
            graph[v].add(new Node(u, w)); // 양방향
        }

        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] distS, distV1, distV2;
        dijkstra(1, distS = new int[V+1]);
        dijkstra(v1, distV1 = new int[V+1]);
        dijkstra(v2, distV2 = new int[V+1]);

        // s -> v1 -> v2 -> e
        int dist1 = distS[v1] + distV1[v2] + distV2[V];
        // s -> v2 -> v1 -> e
        int dist2 = distS[v2] + distV2[v1] + distV1[V];
        min = Math.min(min, dist1);
        min = Math.min(min, dist2);

        if(distV1[v2] == INF || min == INF || min < 0)
            System.out.println(-1);
        else System.out.println(min);
    }


    private static void dijkstra (int start, int[] dist) {
        visited = new boolean[V+1]; // 방문배열
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

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