import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K, map[][], min = Integer.MAX_VALUE;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for(int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        bfs(0, 0, 0);

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Info extends Point {
        int cnt;

        public Info(int x, int y, int cnt) {
            super(x, y);
            this.cnt = cnt;
        }
    }

    private static void bfs(int x, int y, int cnt) {
        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(x,y,cnt));
        visited[x][y][cnt] = true;

        int level = 1;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                Info tmp = q.poll();

                if(tmp.x == N-1 && tmp.y == M-1) {
                    min = level;
                    return;
                }

                for(int d = 0; d < 4; d++) {
                    int nx = tmp.x + dx[d];
                    int ny = tmp.y + dy[d];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    if(map[nx][ny] == 0 && !visited[nx][ny][tmp.cnt]) {
                        visited[nx][ny][tmp.cnt] = true;
                        q.offer(new Info(nx,ny,tmp.cnt));
                    }

                    if(map[nx][ny] == 1 && tmp.cnt < K && !visited[nx][ny][tmp.cnt+1] ) {
                        visited[nx][ny][tmp.cnt+1] = true;
                        q.offer(new Info(nx,ny,tmp.cnt+1));
                    }
                }
            }
            level++;
        }
    }
}