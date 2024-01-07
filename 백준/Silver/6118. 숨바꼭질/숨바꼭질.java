import java.io.*;
import java.util.*;

class Node{
    int vertex, len;

    Node(int vertex, int len){
        this.vertex = vertex;
        this.len = len;
    }
}

public class Main {
    static int N;
    static List<List<Integer>> l = new ArrayList<>();
    static boolean[] visited;
    static int[] lenArray;
    static int maxLen = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        lenArray = new int[N+1];
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        bfs();

        int maxIdx = 0;
        int maxCnt = 0;
        for(int i = 1; i<=N; i++)
            if(lenArray[i] == maxLen){
                if(maxIdx == 0)
                    maxIdx = i;
                maxCnt++;
            }

        System.out.println(maxIdx+" "+maxLen+" "+maxCnt);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0));
        visited[1] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            maxLen = Math.max(maxLen, a.len);
            lenArray[a.vertex] = a.len;

            for(int i = 0; i<l.get(a.vertex).size(); i++){
                int nextV = l.get(a.vertex).get(i);

                if(visited[nextV])
                    continue;

                q.offer(new Node(nextV, a.len+1));
                visited[nextV] = true;
            }
        }
    }
}