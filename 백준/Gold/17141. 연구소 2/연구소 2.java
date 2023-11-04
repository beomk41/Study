import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	static int N, M, min = Integer.MAX_VALUE;
	static int zeroTmp, zero = 0;
	static int[][] map, copy;
	static int[] selected;
	static boolean flag = false;
	static List<Point> virus = new ArrayList<>();
	static Queue<Point> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		selected = new int[M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) zero++;
				else if (map[i][j] == 1) map[i][j] = -1;
				else if (map[i][j] == 2) {
					virus.add(new Point(i,j));
					zero++;
					map[i][j] = 0;
				}
			}
		}
		
		zero = zero - M;
		comb(0,0);
		
		// 벽 -1 바이러스 1로 변경 0개수 저장
		bfs();
		
		if(min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
	}

	private static void comb(int cnt, int start) {
		if(cnt == M) {
			
			q = new ArrayDeque<>();
			zeroTmp = zero;
			copy = new int[N][N];
			flag = false;
			for (int i=0; i<N; i++) {
				copy[i] = Arrays.copyOf(map[i], N);
			}
			for (int i=0; i<M; i++) {
				Point tmp = virus.get(selected[i]);
				q.offer(tmp);
				copy[tmp.x][tmp.y] = 1;
			}
			bfs();
			if(flag) {
				min = Math.min(min, check());
			}
			
			return;
		}
		
		for (int i=start; i<virus.size(); i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}

	static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1}};
	
	private static void bfs() {
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i=0; i<size; i++) {
				Point tmp = q.poll();
				int x = tmp.x;
				int y = tmp.y;
				
				for (int d=0; d<4; d++) {
					int nx = x + dxy[d][0];
					int ny = y + dxy[d][1];
					
					if (nx >= N || ny >= N || nx < 0 || ny < 0 || copy[nx][ny] != 0) continue;
					copy[nx][ny] = copy[x][y] + 1;
					zeroTmp--;
					q.offer(new Point(nx,ny));
				}
			}
		}
		if (zeroTmp == 0) flag = true;
	}
	
	private static int check() {
		int max = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				max = Math.max(max, copy[i][j]);
			}
		}
		return max -1;
	}
}