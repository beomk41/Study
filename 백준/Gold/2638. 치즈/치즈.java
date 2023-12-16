import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {

    static int N, M, time = 0, count = 0;
    static int[][] map;
    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    points.add(new Point(i, j));
                    count++;
                }
            }
        }

        while(count != 0) {
            simul();
        }
        System.out.println(time);
    }

    private static void simul() {
        BFS(-1, 0);
        search();
        BFS(0, -1);
        time++;
    }

    static int[][] dxy = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    private static void search() {
        ArrayList<Point> remove = new ArrayList<>();

        for (Point tmp : points) {
            int air = 0;

            for (int d = 0; d < 4; d++) {
                int dx = tmp.x + dxy[d][0];
                int dy = tmp.y + dxy[d][1];

                if (dx < 0 || dy < 0 || dx >= N || dy >= M) continue;
                if (map[dx][dy] == -1) air++;
            }

            if (air >= 2) {
                remove.add(tmp);
            }
        }

        for (Point tmp : remove) {
            map[tmp.x][tmp.y] = 0;
            count--;
            for (int i=0; i< points.size(); i++){
                Point tmp2 = points.get(i);
                if(tmp.x == tmp2.x && tmp.y == tmp2.y){
                    points.remove(i);
                    break;
                }
            }
        }
    }

    private static void BFS(int num, int check) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0));

        while(!q.isEmpty()) {
            Point tmp = q.poll();

            for (int d=0; d<4; d++){
                int dx = tmp.x + dxy[d][0];
                int dy = tmp.y + dxy[d][1];

                if(dx < 0 || dy < 0 || dx >= N || dy >= M || visited[dx][dy] || map[dx][dy] != check) continue;
                visited[dx][dy] = true;
                map[dx][dy] = num;
                q.offer(new Point(dx,dy));
            }
        }
    }
}