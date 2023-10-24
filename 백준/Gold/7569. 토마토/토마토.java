import java.io.*;
import java.util.*;

public class Main {
    static int[][] dxy = { {1,0,0},{0,1,0},{-1,0,0},{0,-1,0},{0,0,1},{0,0,-1} }; // 6방향
    static int m, n, h;

    static int[][][] map;

    static Queue<tmt> q = new ArrayDeque<>();
    static class tmt {
        int x;
        int y;
        int z;

        public tmt(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken()); // 열 m
        n = Integer.parseInt(st.nextToken()); // 행 n
        h = Integer.parseInt(st.nextToken()); // 행 n

        map = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1)
                        q.add(new tmt(i, j, k));
                }
            }
        }
        
        bfs();
        int result = search();
        System.out.println(result);
    }

    private static int search() {
        int max = 0;
        for (int i=0; i<h; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<m; k++) {
                    if(map[i][j][k] == 0)
                        return -1;
                    else if (map[i][j][k] > 0)
                        max = Math.max(max, map[i][j][k]);
                }
            }
        }
        return max-1;
    }

    private static void bfs() {
        while(!q.isEmpty()){
            int size = q.size();
            for (int i=0; i<size; i++){
                tmt t = q.poll();
                int x = t.x;
                int y = t.y;
                int z = t.z;
                for (int d=0; d<6; d++){
                    int nx = x + dxy[d][0];
                    int ny = y + dxy[d][1];
                    int nz = z + dxy[d][2];
                    if(nx >= h || ny >= n || nz >= m || nx < 0 || ny < 0 || nz < 0
                            || map[nx][ny][nz] != 0) continue;
                    q.add(new tmt(nx, ny, nz));
                    map[nx][ny][nz] = map[x][y][z] + 1;
                }
            }            
//            for (int i = 0; i < h; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.println(Arrays.toString(map[i][j]));
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }
}