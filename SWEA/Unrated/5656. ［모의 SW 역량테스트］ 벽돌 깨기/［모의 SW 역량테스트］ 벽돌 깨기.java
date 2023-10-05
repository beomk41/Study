import java.util.*;
import java.io.*;

public class Solution {
	static int N, W, H, min;
	static int[][] map, copyMap;
	static int[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			select = new int[N];
			perm(0);

			bw.write("#" + tc + " " + min + "\n");
		}
		bw.flush();
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			copyMap = new int[H][W];
			for (int i=0; i<H; i++) {
				copyMap[i] = Arrays.copyOf(map[i], W);
			}
			
			for (int i=0; i<N; i++) {
				getPoint(select[i]);
				move();
			}
			
			min = Math.min(min, check());
			return;
		}
		
		for (int i=0; i<W; i++) {
			select[cnt] = i;
			perm(cnt+1);
		}
	}

	private static int check() {
		int count = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if(copyMap[i][j] != 0) count++;
			}
		}
		
		return count;
	}

	private static void getPoint(int idx) {
		for (int i=0; i<H; i++) {
			if (copyMap[i][idx] != 0) {
				shoot(i, idx, copyMap[i][idx]);
				break;
			}
		}
	}

	private static void shoot(int x, int y, int range) { // 발사
		for (int i = y - range + 1; i < y + range; i++) {
			if (i  >= 0 && i < W && copyMap[x][i] != 0) {
				int tmp = copyMap[x][i];
				copyMap[x][i] = 0;
				shoot(x, i, tmp);
			}
		}
		
		for (int i = x - range + 1; i < x + range; i++) {
			if (i >= 0 && i < H && copyMap[i][y] != 0) {
				int tmp = copyMap[i][y];
				copyMap[i][y] = 0;
				shoot(i, y, tmp);
			}
		}
	}

	private static void move() { // 빈칸 아래로 이동
		int[][] tmp = new int[H][W];
		
		for (int i=0; i<W; i++) {
			int height = H-1;
			for (int j=H-1; j>=0; j--) {
				if(copyMap[j][i] != 0) tmp[height--][i] = copyMap[j][i];
			}
		}
		
		for (int i=0; i<H; i++) {
			copyMap[i] = Arrays.copyOf(tmp[i], W);
		}
	}
}
