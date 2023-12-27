import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int s;
    int e;
    int cost;

    public Node (int s, int e, int cost) {
        this.s = s;
        this.e = e;
        this.cost = cost;
    }

    public int compareTo(Node o) {
        return Double.compare(this.cost, o.cost);
    }
}

public class Main {
    // BJ4386
    static int V, E;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Node(s, e, c));
        }

        set();

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(find(now.s) != find(now.e)) {
                union(now.s, now.e);
                min += now.cost;
            }
        }

        System.out.println(min);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x < y) parent[y] = x;
            else parent[x] = y;
        }
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void set() {
        parent = new int[V+1];
        for (int i=0; i<V; i++) {
            parent[i] = i;
        }
    }
}