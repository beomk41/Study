import java.io.*;
import java.util.*;

public class Main {
	static int N, K, magnet[][], current[];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		magnet = new int[N+1][8]; // 0 번 화살표 , 2번 뒤 자석과 비교, 6번 앞 자석과 비교

		for (int i = 1; i < N+1; i++) { // 자석 입력 1번~4번
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				magnet[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) { // 자석 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			visited = new boolean[N+1];
			run(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		System.out.println(cal());
	}

	private static void run(int num, int fn) {
		visited[num] = true;
		int pre = num - 1;
		int next = num + 1;

		if (pre >= 1 && !visited[pre]) { // 앞쪽에 자석이 있는 경우
			if (magnet[pre][2] != magnet[num][6]) {
				run(pre, fn * -1);
			}
		}

		if (next < N+1 && !visited[next]) { // 뒤쪽에 자석이 있는 경우
			if (magnet[next][6] != magnet[num][2]) {
				run(next, fn * -1);
			}
		}

		if (fn == 1) {
			int tmp = magnet[num][7];
			for (int i = 7; i >= 1; i--) {
				magnet[num][i] = magnet[num][i - 1];
			}
			magnet[num][0] = tmp;
		}

		else {
			int tmp = magnet[num][0];
			for (int i = 0; i < 7; i++) {
				magnet[num][i] = magnet[num][i + 1];
			}
			magnet[num][7] = tmp;
		}
	}

	private static int cal() {
		int sum = 0;
		for (int i = 1; i < N+1; i++) {
			if (magnet[i][0] == 1)
				sum++;
		}
		return sum;
	}
}