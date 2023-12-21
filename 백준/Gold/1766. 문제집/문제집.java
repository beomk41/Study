import java.io.*;
import java.util.*;

class Node {
    int in;
    int out;

}

public class Main {

    static int N, M;
    static int[] in;
    static ArrayList<Integer> graph[];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        in = new int[N+1];
        graph = new ArrayList[N+1];

        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            in[e]++;
        }

        sort();

        bw.flush();
        br.close();
        bw.close();
    }

    private static void sort() throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=1; i<N+1; i++) {
            if(in[i]==0) pq.add(i);
        }

        while (!pq.isEmpty()) {
            int tmp = pq.poll();
            bw.write(tmp + " ");

            int size = graph[tmp].size();
            for (int i=0; i<size; i++){
                int next = graph[tmp].get(i);
                in[next]--;
                if(in[next] == 0) pq.add(next);
            }
        }
    }
}