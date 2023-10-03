import java.io.*;
import java.util.*;

class Solution {
    static char[][] map;
    static int N, M, K , R, C;
    static String answer = "impossible";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    
        N = n;
        M = m;
        K = k;
        R = r-1;
        C = c-1;
        
        map = new char[n][m];
        
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if(i == x-1 && j == y-1) map[i][j] = 'S';
                else if(i == r-1 && j == c-1) map[i][j] = 'E';
                else map[i][j] = '.';
            }
        }
        
        search(x-1, y-1, 0, "");
    
        return answer;
    }
    
    static int[][] dxy = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static boolean check = false;
    // [ 0, 1, 2, 3 ] -> { d, l, r, u }
    
    public static void search(int x, int y, int count, String res) {
        int dis = Math.abs(R-x)+Math.abs(C-y);
        if (dis > K-count) return;
        else if(Math.abs(dis - (K-count))% 2 != 0) return;
        
        if(count == K){
            if(map[x][y] == 'E') {
                answer = res;
                check = true;
            }
            return;
        }
        
        for (int d=0; d<4; d++){
            if (check) break;
            int nx = x + dxy[d][0];
            int ny = y + dxy[d][1];
            
            if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
            
            if(d == 0) search(nx, ny, count + 1, res+"d");
            else if(d == 1) search(nx, ny, count + 1, res+"l");
            else if(d == 2) search(nx, ny, count + 1, res+"r");
            else if(d == 3) search(nx, ny, count + 1, res+"u");
        }
    }
}