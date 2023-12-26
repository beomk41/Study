import java.io.*;
import java.util.*;

class Star {
    int idx;
    double x;
    double y;

    public Star(int idx, double x, double y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
    }
}

class Node implements Comparable<Node> {
    int s;
    int e;
    double cost;

    public Node (int s, int e, double cost) {
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
    static int N;
    static Star[] stars;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static double cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        stars = new Star[N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(i, x, y);
        }

        set();

        calDist();

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(find(now.s) != find(now.e)) {
                union(now.s, now.e);
                cost += now.cost;
            }
        }

        System.out.printf("%.2f", cost);
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
        parent = new int[N+1];
        for (int i=0; i<N; i++) {
            parent[i] = i;
        }
    }

    private static void calDist() {
        for (int i=0; i<N-1; i++) {
            for (int j=i; j<N; j++) {
                if(i != j) {
                    pq.offer(new Node(i, j, Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2))));
                }
            }
        }
    }

}