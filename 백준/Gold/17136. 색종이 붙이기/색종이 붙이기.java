import java.io.*;
import java.util.*;

public class Main {
	static int[][] map = new int[15][15];
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0,0,0); 

		if (result != Integer.MAX_VALUE)
			System.out.println(result);
		else
			System.out.println("-1");
	}

	private static void DFS(int x, int y, int cnt) {
		if(x >= 9 && y>9) {
			result = Math.min(result, cnt);
			return;
		}
		
		if(cnt > result) return;
		
		if(y > 9) {
			DFS(x+1, 0, cnt);
			return;
		}
		
		if(map[x][y] == 1) {
			for (int i=5; i>=1; i--) {
				if(paper[i] != 0 && check(x,y,i)) {
					run(x,y,i,0);
					paper[i]--;
					DFS(x,y+1,cnt+1);
					run(x,y,i,1);
					paper[i]++;
				}
			}
		}
		else {
			DFS(x,y+1,cnt);
		}
	}
	
	

	private static void run(int x, int y, int num, int p) {
		for (int i=0; i<num; i++) {
			for (int j=0; j<num; j++) {
				map[x+i][y+j] = p;
			}
		}
	}

	private static boolean check(int x, int y, int num) {
		for (int i=0; i<num; i++) {
			for (int j=0; j<num; j++) {
				if(map[x+i][y+j] == 0) return false;
			}
		}
		return true;
	}

}