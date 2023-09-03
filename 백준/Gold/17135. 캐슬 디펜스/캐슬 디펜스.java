import java.util.*;
import java.io.*;

public class Main {
    static int N, M, D, runCnt, kill;
    static int[][] map;
    static int[][] copy;
    static int max = Integer.MIN_VALUE;
    static int[] select;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M];
        copy = new int[N + 1][M];
        select = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(max);
    }

    public static void comb(int cnt, int start) {
        if (cnt == 3) {
            kill = 0;
            runCnt = 0;
            copyMap();
            start();
            if (kill > max)
                max = kill;
            return;
        }

        for (int i = start; i < M; i++) {
            select[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void copyMap() {
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], map[i].length);
        }
        copy[N] = new int[M];
        for (int i = 0; i < 3; i++) {
            copy[N][select[i]] = -1;
        }
    }

    public static void start() {
        while (runCnt < N) { // runCnt가 N보다 작을 동안 계속 반복
            shoot();
            move();
            runCnt++;
        }
    }

    public static void move() {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = copy[i - 1][j];
            } // 한 칸씩 내리기
        }
        for (int j = 0; j < M; j++) {
            copy[0][j] = 0;
        } // 가장 윗 칸 다 0으로 채우기
    }

    static int[] dx = { 0, -1, 0 }; // 아래쪽 x 3방향
    static int[] dy = { 1, 0, -1 };
    static boolean[][] visited;
    static Queue<Point> q;
    static int range;

    public static void shoot() {
        for (int i = 0; i < 3; i++) {
            q = new ArrayDeque<>();
            int y = select[i];
            int x = N-1;
            q.add(new Point(x, y));
            visited= new boolean[N+1][M];
            BFS();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == -1) {
                    kill++;
                    copy[i][j] = 0;
                }
            }
        }
    }

    private static void BFS() {
        range = 1;
        Point close = null; // 가장 가까운 적을 저장할 변수

        while (!q.isEmpty() && range <= D) {
            int qSize = q.size();
            for (int qCnt = 0; qCnt < qSize; qCnt++) {
                Point tmp = q.poll();

                if (copy[tmp.x][tmp.y] != 0) {
                    if (close == null || tmp.y < close.y) {
                        close = tmp; // 왼쪽에 있는 적을 우선으로 선택
                    }
                } else {
                    visited[tmp.x][tmp.y] = true;
                    for (int d = 0; d < 3; d++) {
                        int nx = tmp.x + dx[d];
                        int ny = tmp.y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                        q.add(new Point(nx, ny));
                    }
                }
            }
            if (close != null) {
                copy[close.x][close.y] = -1; // 가장 가까운 적을 사격 대상으로 선택
                return;
            }
            range++;
        }
    }
}