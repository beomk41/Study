import java.io.*;
import java.util.*;

public class Main {
	static int N, count;
	static int[][] map;
	static int shark = 2;
	static int eat = 0;
	static Point babyShark;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static boolean[][] visited;
	static boolean flag = true;
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			if(this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					babyShark = new Point(i,j);
				}
			}
		}
		while(flag) {
			BFS(babyShark.x, babyShark.y);
		}
		
		System.out.println(count);
	}

	static PriorityQueue<Point> pq;
	
	static void BFS(int x, int y) {
		visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		pq = new PriorityQueue<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;
		
		int level = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i=0; i<size; i++) {
				Point tmp = q.poll();
				
				if (map[tmp.x][tmp.y] != 0 && map[tmp.x][tmp.y] < shark) { // 먹음
					pq.offer(new Point(tmp.x, tmp.y));
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = tmp.x + dx[d];
					int ny = tmp.y + dy[d];
					if(nx >= N || ny >= N || nx < 0 || ny < 0 || visited[nx][ny] || map[nx][ny] > shark) continue;
					visited[nx][ny] = true;
					q.offer(new Point(nx,ny));
				}
			}
			if(!pq.isEmpty()) {
				move(level);
				return;
			}
			level++;
		}
		flag = false;
	}

	private static void move(int level) {
		Point tmp = pq.poll();
		map[tmp.x][tmp.y] = 9;
		map[babyShark.x][babyShark.y] = 0;
		babyShark.x = tmp.x;
		babyShark.y = tmp.y;
		if(++eat == shark) { // 상어 사이즈 증가
			eat = 0;
			shark++;
		}
		count += level;
		return;
	}
}