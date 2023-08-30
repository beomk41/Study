import java.io.*;
import java.util.*;

public class Main {
	static long map[][], D[][][];
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new long[N + 2][N + 2];
		D = new long[3][N + 2][N + 2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		D[0][1][1] = 1; // - 추가 (이전 상태가 -, \ 일때 가능)
		D[0][1][2] = 1;
		
		search();

		System.out.println(D[0][N][N] + D[1][N][N] + D[2][N][N]);
	}

	private static void search() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 1 || (i == 1 && (j == 1 || j == 2)))
					continue;
				
				D[0][i][j] = D[0][i][j-1] + D[2][i][j-1];
				if(map[i][j] != 1 && map[i+1][j] != 1 && map[i+1][j-1] != 1) {
					D[2][i+1][j] = D[0][i][j-1] + D[1][i][j-1] + D[2][i][j-1];				
				}
				D[1][i][j] = D[1][i-1][j] + D[2][i-1][j];
			}
		}
	}
}