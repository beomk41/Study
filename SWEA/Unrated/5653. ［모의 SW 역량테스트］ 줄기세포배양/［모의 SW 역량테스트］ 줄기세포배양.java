import java.awt.*;
import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K;
    static int[][] map, status;
    static boolean[][] visited, visited2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String result = "";
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N + 2 * K][M + 2 * K]; // [N+2K] 최대 번식 [M+2k] 0,0 -> K,K
            status = new int[N + 2 * K][M + 2 * K];
            visited = new boolean[N + 2 * K][ + 2 * K];
            visited2 = new boolean[N + 2 * K][ + 2 * K];
            for (int i = K; i < K + N; i++) { // 세포 초기 위치
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = K; j < K + M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0) {
                    	status[i][j] = map[i][j];
                    	visited2[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < K; i++) { // K시간 동안 번식
                run();
            }

            bw.write(result + "#" + tc + " " + cal() + "\n");
        }
        bw.flush();
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Point> q;
    static int time = 0;

    private static void run() {
        q = new ArrayDeque<>();

        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[0].length; j++) {
                if (status[i][j] == -1 && !visited[i][j]) { // -1 되면 활성화
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[0].length; j++) {
                if (status[i][j] > 0) {
                    status[i][j]--;
                    if (status[i][j] == 0) { // 감소 시켰을때 0 되면 -1로 변경
                        status[i][j] = -1;
                    }
                }
            }
        }
        

        int size = q.size();

        for (int i = 0; i < size; i++) { // 번식
            Point tmp = q.poll();
            int tmpNum = map[tmp.x][tmp.y];
            for (int d = 0; d < 4; d++) {
                int nx = tmp.x + dx[d];
                int ny = tmp.y + dy[d];
                if (nx >= map.length || nx < 0 || ny >= map[0].length || ny < 0) continue;
                if (!visited2[nx][ny] && !visited[nx][ny]) {
                    status[nx][ny] = Math.max(status[nx][ny], tmpNum);
                    map[nx][ny] = Math.max(map[nx][ny], tmpNum);
                }
            }
        }
        
        for (int i = 0; i < visited.length; i++) {
        	for (int j = 0; j < visited[0].length; j++) {
        		if(map[i][j] != 0) visited2[i][j] = true;
        	}
        }

        for (int i = 0; i < visited.length; i++) {
        	for (int j = 0; j < visited[0].length; j++) {
        		if (visited[i][j] && map[i][j] > 0) { // 번식 했고, 0 이상이면 --
        			map[i][j]--;
        		}
        	}
        }

//        System.out.println(++time);
//        for (int i = 0; i < map.length; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println("----------------------------------");

    }

    private static int cal() {
    	int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) sum++;
            }
        }
        return sum;
    }
}