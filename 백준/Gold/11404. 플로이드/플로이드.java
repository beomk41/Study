import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int e;
    int cost;

    public Node (int e, int cost) {
        this.e = e;
        this.cost = cost;
    }

    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
public class Main {
    static int V, E, INF = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] dist;
    static ArrayList<Node> graph[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        dist = new int[V+1][V+1];

        graph = new ArrayList[V+1];
        for (int i=0; i<V+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, cost));
        }

        for (int i=1; i<V+1; i++) {
            dijkstra(i);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i=1; i<V+1; i++) {
            for (int j=1; j<V+1; j++) {
                if(dist[i][j] == INF) dist[i][j] = 0;
                bw.write(dist[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    private static void dijkstra(int start) {
        visited = new boolean[V+1];
        Arrays.fill(dist[start], INF);
        dist[start][start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int cur = pq.poll().e;

            if(visited[cur]) continue;
            visited[cur] = true;

            for (int i = 0; i < graph[cur].size(); i++) {
                Node next = graph[cur].get(i);

                if (dist[start][next.e] > dist[start][cur] + next.cost) {
                    dist[start][next.e] = dist[start][cur] + next.cost;

                    pq.offer(new Node(next.e, dist[start][next.e]));
                }
            }
        }
    }
}