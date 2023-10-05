import java.io.*;
import java.util.*;

class Solution {
    static int[][] map;
    
    public boolean solution(int[][] key, int[][] lock) {
        map = new int[key.length*2 + lock.length][key.length*2 + lock.length]; // N+2M * N+2M 크기의 맵 생성
        
        for (int i=key.length; i<key.length + lock.length; i++) { // map에 자물쇠 복사
            for (int j=key.length; j<key.length + lock.length; j++) {
                map[i][j] = lock[i-key.length][j-key.length];
            }
        }
        
        for (int r=0; r<4; r++){
            rotate(key); // 키 돌리기
            
            for (int i= 0; i<=key.length + lock.length; i++) {
                for (int j= 0; j<=key.length + lock.length; j++) {
                    attach(key, i, j, 1);
                    if(check(key.length, lock.length)) return true; // 탐색시 true 리턴
                    attach(key, i, j, -1);
                }
            }
        }
        
        return false;
    }
    
    public static void rotate(int[][] k) { // 90 도 배열 돌리기
        int[][] tmp = new int[k.length][k.length];
        
        for (int i=0; i<tmp.length; i++) {
            for (int j=0; j<tmp.length; j++) {
                tmp[i][j] = k[j][tmp.length-1-i];
            }      
        }
        
        for (int i=0; i<tmp.length; i++){
            k[i] = Arrays.copyOf(tmp[i], tmp.length);
        }
    }
    
    public static void attach(int[][] k, int x, int y, int type) { // 부착, // 제거
        for (int i=x; i<x+k.length; i++){
            for (int j=y; j<y+k.length; j++){
                map[i][j] += k[i-x][j-y] * type;
            }    
        }
    }
    
    public static boolean check(int start, int size) { // 확인
        for (int i=start; i<start+size; i++){
            for (int j=start; j<start+size; j++){
                if(map[i][j] == 0 || map[i][j] == 2) return false;
            }    
        }
        return true;
    }
}