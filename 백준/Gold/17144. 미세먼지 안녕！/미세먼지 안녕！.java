import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
    static int R,C,T;
    static int[][] map, tmpMap;
    static Point cleaner;
    static boolean check = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (!check && map[i][j] == -1) {
                    check = true;
                    cleaner = new Point(i,j);
                }
            }
        }

        for (int i=0; i<T; i++) {
            diffusion();
            pure();
        }
        System.out.println(check());
    }

    static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1}};

    private static void diffusion() {
        tmpMap = new int[R][C];
        for (int i=0; i<R; i++) {
            tmpMap[i] = Arrays.copyOf(map[i], C);
        }

        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if(map[i][j] > 0) {
                    int tmp = map[i][j] / 5;
                    for (int d=0; d<4; d++) {
                        int nx = i+dxy[d][0];
                        int ny = j+dxy[d][1];

                        if (nx >= R || ny >= C || nx < 0 || ny < 0 || map[nx][ny] == -1) continue;

                        tmpMap[nx][ny] += tmp;
                        tmpMap[i][j] -= tmp;
                    }
                }
            }
        }

        for (int i=0; i<R; i++) {
            map[i] = Arrays.copyOf(tmpMap[i], C);
        }
    }

    private static void pure() {

        int top = cleaner.x;
        int down = cleaner.x + 1;

        for (int i = top - 1; i > 0; i--)
            map[i][0] = map[i-1][0];

        for (int i = 0; i < C - 1; i++)
            map[0][i] = map[0][i+1];

        for (int i = 0; i < top; i++)
            map[i][C - 1] = map[i + 1][C - 1];

        for (int i = C - 1; i > 1; i--)
            map[top][i] = map[top][i-1];

        map[top][1] = 0;

        for (int i = down + 1; i < R - 1; i++)
            map[i][0] = map[i + 1][0];

        for (int i = 0; i < C - 1; i++)
            map[R - 1][i] = map[R - 1][i + 1];

        for (int i = R - 1; i > down; i--)
            map[i][C - 1] = map[i - 1][C - 1];

        for (int i = C - 1; i > 1; i--)
            map[down][i] = map[down][i - 1];
        map[down][1] = 0;
    }

    private static int check() {
        int count = 0;

        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if(map[i][j] > 0) count += map[i][j];
            }
        }

        return count;
    }
}