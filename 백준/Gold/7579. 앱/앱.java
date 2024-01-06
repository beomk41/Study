import java.io.*;
import java.util.*;

public class Main {
    static int N, M, sum, min;
    static int[] memory, cost;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sum += cost[i];
        }

        DP = new int[N+1][sum+1];

        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= sum; j++)
            {
                if (j - cost[i] >= 0)
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - cost[i]] + memory[i]);

                DP[i][j] = Math.max(DP[i][j], DP[i - 1][j]);
            }
        }

        for (int i = 0; i <= sum; i++)
        {
            if (DP[N][i] >= M)
            {
                System.out.println(i);
                break;
            }
        }
    }
}