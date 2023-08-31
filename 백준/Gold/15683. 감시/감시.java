import java.io.*;
import java.util.*;

public class Main {
	static class Cam {
		char num; // 카메라 정보
		int x; // x좌표
		int y; // y좌표
		int d; // 카메라 방향 -> 시계방향 0123

		public Cam(char num, int x, int y, int d) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int N, M, min = Integer.MAX_VALUE;
	static char[][] map;
	static int[] dx = { -1, 0, 1, 0 }; // 시계방향
	static int[] dy = { 0, 1, 0, -1 };
	static List<Cam> cam = new ArrayList<>();
	static int[] selected;
	static char[][] copy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] >= 49 && map[i][j] <= 53) { // '1' ~ '5' 사이일 경우
					cam.add(new Cam(map[i][j], i, j, 0));
				}
			}
		}
		selected = new int[cam.size()];
		perm(0);

		System.out.println(min);
	}

	private static void perm(int cnt) { // 조합으로 카메라 방향 설정
		if (cnt == cam.size()) {
			for (int i = 0; i < cam.size(); i++) { // 카메라 방향 설정
				Cam tmp = new Cam(cam.get(i).num, cam.get(i).x, cam.get(i).y, selected[i]);
				cam.set(i, tmp);
			}
			run();
			return;
		}
		for (int i = 0; i < 4; i++) {
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}

	private static void run() {
		copy = new char[map.length][map[0].length];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		for (int i = 0; i < cam.size(); i++) { // 카메라 개수만큼 범위 #으로 채우기
			Cam tmp = cam.get(i);
			if (tmp.num == '1') {
				fill(tmp.x, tmp.y, tmp.d, copy);
			} 
			else if (tmp.num == '2') {
				fill(tmp.x, tmp.y, tmp.d, copy);
				int dir = tmp.d + 2;
				if (dir > 3) dir -= 4;
				fill(tmp.x, tmp.y, dir, copy);
			} 
			else if (tmp.num == '3') {
				fill(tmp.x, tmp.y, tmp.d, copy);
				int dir = tmp.d + 1;
				if (dir > 3) dir -= 4;
				fill(tmp.x, tmp.y, dir, copy);
			} 
			else if (tmp.num == '4') {
				fill(tmp.x, tmp.y, tmp.d, copy);
				int dir1 = tmp.d - 1;
				int dir2 = tmp.d + 1;
				if (dir1 < 0) dir1 += 4;
				if (dir2 > 3) dir2 -= 4;
				fill(tmp.x, tmp.y, dir1, copy);
				fill(tmp.x, tmp.y, dir2, copy);
			}
			else if (tmp.num == '5') {
				fill(tmp.x, tmp.y, 0, copy);
				fill(tmp.x, tmp.y, 1, copy);
				fill(tmp.x, tmp.y, 2, copy);
				fill(tmp.x, tmp.y, 3, copy);
			}
		}

		min = Math.min(min, cal(copy));
	}

	static void fill(int x, int y, int d, char[][] Map) { // 한 방향 채우기
		for (int i = 1; i < Math.max(M, N); i++) {
			int nx = x + (dx[d] * i);
			int ny = y + (dy[d] * i);
			if (nx >= Map.length || ny >= Map[0].length || nx < 0 || ny < 0 || Map[nx][ny] == '6') break; // 벽 or 범위 밖이면 종료
			if (Map[nx][ny] == '0') Map[nx][ny] = '#';
		}
	}

	static int cal(char[][] Map) {
		int cnt = 0;
		for (int i = 0; i < Map.length; i++) {
			for (int j = 0; j < Map[0].length; j++) {
				if (Map[i][j] == '0') cnt++;
			}
		}
		return cnt;
	}
}