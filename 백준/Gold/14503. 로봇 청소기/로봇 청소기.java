import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, d, count = 0;
    static int[][] map;
    static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simul();
        System.out.println(count);
    }

    private static void simul() {

        boolean bp = true;
        while (bp) {
            if (map[x][y] == 0) {
                map[x][y] = -1; // 청소
                count++;
            }

            boolean flag = false; // 탐색 플래그

            for (int i = 0; i < 4; i++) {
                int nx = x + dxy[i][0];
                int ny = y + dxy[i][1];

                if (nx >= N || ny >= M || nx < 0 || ny < 0) continue;

                if (map[nx][ny] == 0) { // 청소되지 않은 빈 칸 발견
                    flag = true;
                    break;
                }
            }

            if (flag){ // 청소되지 않은 빈 칸이 있는 경우
                d--;
                if (d < 0) d += 4; // 회전

                if (d == 0){ // 상
                    if (x-1 >= 0 && map[x-1][y] == 0) x--;
                }
                else if (d == 1){ // 좌
                    if (y+1 < M && map[x][y+1] == 0) y++;
                }
                else if (d ==2) { // 하
                    if (x+1 < N && map[x+1][y] == 0) x++;
                }
                else if (d == 3) { // 우
                    if (y-1 >= 0 && map[x][y-1] == 0) y--;
                }
            }
            else { // 청소되지 않은 빈 칸이 없는 경우
                if (d == 0){ // 상 -> x+1
                    if (x+1 >= N || map[x+1][y] == 1) bp = false;
                    else x++;
                }
                else if (d == 1){ // 좌 -> y-1
                    if (y-1 < 0 || map[x][y-1] == 1) bp = false;
                    else y--;
                }
                else if (d ==2) { // 하 -> x-1
                    if (x-1 < 0 || map[x-1][y] == 1) bp = false; // 범위 밖 or 벽
                    else x--;
                }
                else if (d == 3) { // 우 -> y+1
                    if (y+1 >= M || map[x][y+1] == 1) bp = false;
                    else y++;
                }
            }
//            System.out.println(bp);
        }
    }
}

