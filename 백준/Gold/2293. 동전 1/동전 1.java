import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coin;
    static int[] D = new int[10001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N+1];
        for (int i=1; i<=N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        D[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = coin[i]; j <= K; j++) {
                D[j] = D[j] + D[j - coin[i]];
            }
        }
        System.out.println(D[K]);
    }
}