import java.io.*;
import java.util.*;

public class Solution {
    static int[][] D = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String A = st.nextToken();
            String B = st.nextToken();

            for (int i=1; i<=A.length(); i++){
                for (int j=1; j<=B.length(); j++){
                    if(A.charAt(i-1) == B.charAt(j-1)) D[i][j] = D[i-1][j-1] + 1;
                    else {
                        D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
                    }

                }
            }
            System.out.println("#" + tc + " " + D[A.length()][B.length()]);
        }
    }
}