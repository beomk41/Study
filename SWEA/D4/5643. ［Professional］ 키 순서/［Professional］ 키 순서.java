import java.util.*;
import java.io.*;

public class Solution {
	static int result, N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a-1][b-1] = -1;
				map[b-1][a-1] = 1;
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
			
//			for (int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			result = checkResult();
			
			bw.write("#" + tc + " " + result+"\n");
		}
		bw.flush();
	}
	
	// 1 = 진입, -1 = 진출
	static boolean flag = false;
	
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
