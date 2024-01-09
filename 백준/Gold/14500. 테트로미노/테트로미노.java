import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check();

        System.out.println(max);
    }

    static int[][] type = {
            {1,0},{2,0},{3,0},
            {0,1},{0,2},{0,3},
            {0,1},{1,0},{1,1},
            {1,0},{2,0},{2,1},
            {0,1},{0,2},{-1,2},
            {0,1},{1,1},{2,1},
            {1,0},{0,1},{0,2},
            {0,1},{-1,1},{-2,1},
            {1,0},{1,1},{1,2},
            {1,0},{0,1},{2,0},
            {0,1},{0,2},{1,2},
            {1,0},{1,1},{2,1},
            {0,1},{-1,1},{-1,2},
            {0,1},{1,1},{1,2},
            {1,0},{1,-1},{2,-1},
            {0,1},{0,2},{1,1},
            {0,1},{-1,1},{0,2},
            {1,0},{2,0},{1,1},
            {1,0},{1,-1},{2,0},
    };
    private static void check() {
        boolean flag = true;
        int sum = 0;
        int count = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int t=0; t<57; t++) {
                    if (t % 3 == 0){
                        sum = 0;
                        flag = true;
                        count = 0;
                    }
                    count++;
                    if(!flag) continue;

                    int nx = i + type[t][0];
                    int ny = j + type[t][1];


                    if (nx >= N || ny >= M || nx < 0 || ny < 0) {
                        flag = false;
                        continue;
                    }

                    sum += map[nx][ny];

                    if (count == 3) {
                        if (!flag) continue;
                        max = Math.max(max, sum + map[i][j]);
                    }
                }
            }
        }
    }

}