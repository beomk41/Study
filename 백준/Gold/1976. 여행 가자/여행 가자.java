import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map; // 경로, 갈 수 있는 곳
    static int[] plan;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N][N];
        plan = new int[M];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i==j) map[i][j] = 1;
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<M; i++){
            plan[i] = Integer.parseInt(st.nextToken()) -1;
        }
        for (int g=0; g<N; g++) {
	        for (int i=0; i<N; i++){
	            for (int j=0; j<N; j++){
	                if (map[i][j] == 1) {
	                    for (int k=0; k<N; k++){
	                        if(map[j][k] == 1) map[i][k] = 1;
	                    }
	                }
	            }
	        }
        }
        boolean check = true;
        int start = plan[0];

        for (int i=1; i<M; i++){
            if(map[start][plan[i]] != 1){
                check = false;
                break;
            }
            start = plan[i];
        }

        if(!check) System.out.println("NO");
        else System.out.println("YES");
    }
}