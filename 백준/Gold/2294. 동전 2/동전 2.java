import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coin;
    static int[] D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N+1];
        D = new int[100001];
        for (int i=1; i<=N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(D, 100001);
        D[0] = 0;

        for (int i=1; i<=N; i++) {
            D[coin[i]] = 1;
        }

        for (int i=1; i<=K; i++) {
            for (int j=1; j<=N; j++) {
                if(i - coin[j] >= 0)
                    D[i] = Math.min(D[i - coin[j]] + 1, D[i]);
            }
        }
        if (D[K] == 100001) System.out.println(-1);
        else System.out.println(D[K]);
    }
}