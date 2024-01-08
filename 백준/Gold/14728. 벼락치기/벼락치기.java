import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[] time, score;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        time = new int[N+1];
        score = new int[N+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());
        }

        DP = new int[N+1][T+1];

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=T; j++) {
                if (j >= time[i]) {
                    DP[i][j] = Math.max(score[i] + DP[i-1][j-time[i]], DP[i-1][j]);
                }
                else {
                    DP[i][j] = DP[i-1][j];
                }
            }
        }

        System.out.println(DP[N][T]);
    }
}