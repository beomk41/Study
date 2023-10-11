import java.io.*;
import java.util.*;

public class Main {
	static int N, max=0;
	static int[][] map, copy;
	static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected = new int[5];
		perm(0);
		move();
		System.out.println(max);
	}
	
	
	private static void perm(int cnt) {
		if (cnt == 5) {
			copy = new int[N][N];
			for (int i=0; i<N; i++) {
				copy[i] = Arrays.copyOf(map[i], N);
			}
			
			move();
			search();
			return;
		}
		
		for (int i=0; i<4; i++) {
			selected[cnt] = i;
			perm(cnt+1);
		}
	}
	
	static boolean[] check;
	static int[] tmp;

	private static void move() {
		for (int i=0; i<5; i++) {
			int type = selected[i];
		
			if(type == 0) { // 상
				for (int j=0; j<N; j++) {
					tmp = new int[N];
					for (int k=0; k<N; k++) {
						tmp[k] = copy[k][j];
						copy[k][j] = 0;
					}
					tmp = sum(tmp);
					int index = 0;
					for (int k=0; k<N; k++) {
						if(tmp[k] != 0) {
							copy[index++][j] = tmp[k];
						}
					}
				}
			}
			
			else if(type == 1) { // 하 -> 수정
				for (int j=0; j<N; j++) {
					tmp = new int[N];
					int l = 0;
					for (int k=N-1; k>=0; k--) {
						tmp[l++] = copy[k][j];
						copy[k][j] = 0;
					}
					tmp = sum(tmp);

					int index = N-1;
					for (int k=0; k<N; k++) {
						if(tmp[k] != 0) {
							copy[index--][j] = tmp[k];
						}
					}
				}
			}
			
			else if(type == 2) { // 좌
				for (int j=0; j<N; j++) { // 가로
					tmp = new int[N];
					for (int k=0; k<N; k++) {
						tmp[k] = copy[j][k];
						copy[j][k] = 0;
					}
					tmp = sum(tmp);
					int index = 0;
					for (int k=0; k<N; k++) {
						if(tmp[k] != 0) {
							copy[j][index++] = tmp[k];
						}
					}
				}		
			}
			
			else if(type == 3) { // 우 -> 수정
				for (int j=0; j<N; j++) { // 가로
					tmp = new int[N];
					int l = 0;
					for (int k=N-1; k>=0; k--) {
						tmp[l++] = copy[j][k];
						copy[j][k] = 0;
					}
					tmp = sum(tmp);
					int index = N-1;
					for (int k=0; k<N; k++) {
						if(tmp[k] != 0) {
							copy[j][index--] = tmp[k];
						}
					}
				}
			}

		}
	}

	private static int[] sum(int[] list) {
		for (int i=0; i<list.length; i++) {
			if (list[i] == 0) continue;
			
			for (int j=1; j<list.length; j++) {
				if (i+j < list.length) {
					if (list[i+j] == 0) continue;
					else if(list[i] == list[i+j]) {
						list[i] *= 2;
						list[i+j] = 0;
						break;
					}
					else if(list[i] != list[i+j]) {
						break;
					}
				}
			}

		}
		return list;
	}


	public static void search() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				max = Math.max(max, copy[i][j]);

			}
		}
	}
}
