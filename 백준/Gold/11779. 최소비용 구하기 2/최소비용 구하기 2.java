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
        if(this.cost != o.cost){
            return Integer.compare(this.cost, o.cost);
        }
        return Integer.compare(this.idx, o.idx);
    }
}

public class Main {

    static int V, E, INF = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] dist, path;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        for (int i=0; i<V+1; i++){
            graph[i] = new ArrayList<>();
        }
        path = new int[V+1];

        for (int i=0; i<E; i++) { // 정점 양방향 x, 여러개 , 값 입력
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); // 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 가중치

            boolean checkNode = false;
            for (int j=0; j<graph[u].size(); j++){
                Node tmp = graph[u].get(j);
                if(tmp.idx == v){
                    checkNode = true;
                    if(tmp.cost > w)
                        graph[u].set(j, new Node(v, w));
                    break;
                }
            }
            if(!checkNode)
                graph[u].add(new Node(v, w));
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);

        System.out.println(dist[end]);
        ArrayList<Integer> routes = new ArrayList<>();
        int current = end;
        while(current != 0) {
            routes.add(current);
            current = path[current];
        }
        System.out.println(routes.size());
        for(int i = routes.size() - 1; i >= 0; i--) {
            System.out.print(routes.get(i) + " ");
        }
    }

    private static void dijkstra (int start, int end) {
        visited = new boolean[V+1]; // 방문배열
        dist = new int[V+1]; // 최소거리
        Arrays.fill(dist, INF);
        dist[start] = 0;
        path[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int curNode = pq.poll().idx;

            if(visited[curNode]) continue;
            visited[curNode] = true;

            for (Node next : graph[curNode]) {
                if (dist[next.idx] > dist[curNode] + next.cost) {
                    dist[next.idx] = dist[curNode] + next.cost;
                    path[next.idx] = curNode;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}