import java.io.*;
import java.util.*;

public class Main {
    static int N, totalCnt;
    static int[][] visited;
    static char[][] map;
    static char tmp; // 현재 그룹의 색상
    static int max_count1 = 1;
    static int max_count2 = 1;
    static int cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j=0; j<N; j++){
                map[i][j] = str.charAt(j);
            }
        }

        totalCnt = 1;
        visited = new int[N][N]; // 방문배열 초기화
        tmp = map[0][0];
        visited[0][0] = 1;
        DFS1(0,0,cnt); // (시작x, 시작y, 탐색횟수)

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(visited[i][j] == 0){
                    tmp = map[i][j];
                    visited[i][j] = ++cnt;
                    totalCnt++;
                    if(cnt>max_count1) max_count1 = cnt;
                    DFS1(i,j,cnt);
                }
            }
        }

        cnt = 1;
        totalCnt = 1;
        visited = new int[N][N]; // 방문배열 초기화
        tmp = map[0][0];
        visited[0][0] = 1;
        DFS2(0,0,cnt); // (시작x, 시작y, 탐색횟수)

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(visited[i][j] == 0){
                    tmp = map[i][j];
                    visited[i][j] = ++cnt;
                    totalCnt++;
                    if(cnt>max_count2) max_count2 = cnt;
                    DFS2(i,j,cnt);
                }
            }
        }

        System.out.println(max_count1 + " " + max_count2);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    private static void DFS1(int x, int y, int cnt) { // 색약 아닌 사람
        if(totalCnt == N*N) return;

        for (int d=0; d<4; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny] != 0 || map[nx][ny] != tmp) continue;
            visited[nx][ny] = cnt;
            totalCnt++;
            DFS1(nx, ny, cnt);
        }
    }
    private static void DFS2(int x, int y, int cnt) { // 색약인 사람
        if(totalCnt == N*N) return;

        for (int d=0; d<4; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny] != 0 ) continue;

            if (tmp == 'B'){
                if(map[nx][ny] == 'B'){
                    visited[nx][ny] = cnt;
                    totalCnt++;
                    DFS2(nx, ny, cnt);
                }
            }
            else {
                if(map[nx][ny] != 'B'){
                    visited[nx][ny] = cnt;
                    totalCnt++;
                    DFS2(nx, ny, cnt);
                }
            }
        }
    }
}