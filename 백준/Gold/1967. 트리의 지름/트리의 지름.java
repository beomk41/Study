import java.io.*;
import java.util.*;

class Node{
    int idx,cnt;
    Node(int idx, int cnt){
        this.idx = idx;
        this.cnt = cnt;
    }
}

public class Main {
    static ArrayList<Node> list[];
    static int N, max = 0, maxIdx = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            String [] t = br.readLine().split(" ");
            int parent = Integer.parseInt(t[0]);
            int child = Integer.parseInt(t[1]);
            int weight = Integer.parseInt(t[2]);
            list[parent].add(new Node(child,weight));
            list[child].add(new Node(parent,weight));
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1,0);

        visited = new boolean[N+1];
        visited[maxIdx] = true;
        dfs(maxIdx,0);
        System.out.println(max);
    }
    public static void dfs(int idx, int cnt) {

        if(max < cnt) {
            max = cnt;
            maxIdx = idx;
        }

        for(Node a : list[idx]) {
            if(!visited[a.idx]) {
                visited[a.idx] = true;
                dfs(a.idx, cnt+a.cnt);
            }
        }
    }
}