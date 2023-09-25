import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
	static int N, M, max = Integer.MIN_VALUE;
	static int zero = 0;
	static int[][] map;
	static List<Point> list = new ArrayList<>();
	static List<Point> virus = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					list.add(new Point(i,j));
					zero++;
				}
				if (map[i][j] == 2) {
					virus.add(new Point(i,j));
				}
			}
		}
		
		comb(0,0);
		System.out.println(max);
	}
	
	static int[] selected = new int[3];
	static int tmpCnt;
	
	private static void comb(int cnt, int start) {
		if(cnt == 3) {
			run();
			return;
		}
		
		for (int i=start; i<list.size(); i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	static boolean visited[][];

	private static void run() {
		for (int i=0; i<selected.length; i++) {
			map[list.get(selected[i]).x][list.get(selected[i]).y] = 1;
		}
		
		tmpCnt = zero-3;
		visited = new boolean[N][M];
		for (int i=0; i<virus.size(); i++) {
			bfs(virus.get(i));			
		}
		
		max = Math.max(max, tmpCnt);
		
		for (int i=0; i<selected.length; i++) {
			map[list.get(selected[i]).x][list.get(selected[i]).y] = 0;
		}
	}
	
	static int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private static void bfs(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(start);
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i=0; i<size; i++) {
				Point tmp = q.poll();
				for (int d=0; d<4; d++) {
					int nx = tmp.x + dxy[d][0];
					int ny = tmp.y + dxy[d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M 
							|| visited[nx][ny] || map[nx][ny] == 1 || map[nx][ny] == 2) continue;
					visited[nx][ny] = true;
					tmpCnt--;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
}
