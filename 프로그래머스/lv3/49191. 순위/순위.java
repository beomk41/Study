import java.io.*;
import java.util.*;

class Solution {
    static int[][] map;
    static int N, len;
    static boolean flag = true;
    
    public int solution(int n, int[][] results) {
        N = n;
        len = results.length;
        map = new int[n][n];
        
        for (int i=0; i<len; i++){
            map[results[i][0]-1][results[i][1]-1] = 1;
            map[results[i][1]-1][results[i][0]-1] = -1;
        }
        
        for (int i=0; i<N; i++) {
				int[] tmp = new int[N];
				boolean[] check = new boolean[N];
				tmp = compare(i, tmp, check, 1);
				tmp = compare(i, tmp, check, -1);
				
				for (int j=0; j<N; j++) {
					if(tmp[j] != 0) map[i][j] = tmp[j];
				}
			}
        
        return checkResult();
    }
    
    private static int[] compare(int start, int[] tmp, boolean[] check, int c) {
		for (int i=0; i<N; i++) {
			if(map[start][i] == c) {
				flag = true;
			}
		}
		
		if (!flag) return tmp;
		
		for (int i=0; i<N; i++) {
			flag = false;
			if(map[start][i] == c && !check[i]) {
				tmp[i] = c;
				check[i] = true;
				tmp = compare(i, tmp, check, c);
			}
		}
		
		return tmp;
	}


	private static int checkResult() {
		
		int res = 0;
		boolean flag = true;
		
		for (int i=0; i<N; i++) {
			flag = true;
			for (int j=0; j<N; j++) {
				if(map[i][j] == 0 && i != j) {
					flag = false;
					break;
				}
			}
			if(flag) res++;
		}
		
		return res;
	}
}