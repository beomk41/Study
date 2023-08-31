import java.io.*;
import java.util.*;

public class Main {
	
	static int N, P[], min = Integer.MAX_VALUE;
	static boolean[] selected;
	static int aCount, bCount; // a그룹의 수와 b그룹의 수
	static int[][] map;
	static int sumA, sumB;
	static boolean check;
	static boolean[] visitedA, visitedB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역 개수
		P = new int[N+1]; // 인구 배열
		map = new int[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 2번째 줄 인구 수 
		for (int i=1; i<N+1; i++) { // 시작 1부터 N까지
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<N+1; i++) { // 구역과 인접 구역 정보
			// 인접한 구역 수 -> 인접 한 구역 번호들 입력
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<n; j++) {
				int end = Integer.parseInt(st.nextToken());
				map[i][end] = 1;
				map[end][i] = 1;
			}
		}
		
//		for (int i=1; i<N+1; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		aCount = N-1; // N-1개 부터 내려가면서 수행
		bCount = 1;
		
		if(N==2) {
			System.out.println(Math.abs(P[1]-P[2]));
			return;
		}
		
		while(true) {
			if(aCount == 0) break;
			selectInd = new int[aCount];
			comb(0,1);
			aCount--;
			bCount++;
		}
		
		if(check) System.out.println(min);
		else System.out.println("-1");
	}
	
	static int[] selectInd;

	private static void comb(int cnt, int start) {
		if (cnt == aCount) {
			selected = new boolean[N+1];
			for (int i=0; i<selectInd.length; i++) {
				selected[selectInd[i]] = true;
			}
//			System.out.println(Arrays.toString(selected));
			sumA = 0;
			sumB = 0;
			checkA();
			return;
		}
		for (int i=start; i<N+1; i++) { // 시작 1부터
			selectInd[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	private static void checkA() { // A 그룹 확인
		boolean flag = true;
		// 연결 확인
		visitedA = new boolean[N+1];
		flag = BFSA();
		
		if(flag) {
			for (int i=1; i<N+1; i++) {
				if(selected[i]) sumA += P[i];
			}
			checkB();
		}
	}
	
	private static boolean BFSA() {
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<N+1; i++) {
			if(selected[i]) {
				q.offer(i);
				visitedA[i] = true;
				break;
			}
		}
		
		int count = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int j=0; j<size; j++) {
				int tmp = q.poll();
				
				for(int i=1; i<N+1; i++) {
					if(selected[i] && !visitedA[i] && map[tmp][i] == 1) {
						q.offer(i);
						visitedA[i] = true;
						count++;
						if(count == aCount) return true;
					}
				}
			}
		}
		return false;
	}
	
	private static void checkB() { // B 그룹 확인
		boolean flag = true;
		// 연결 확인
		visitedB = new boolean[N+1];
		flag = BFSB();
		
		if(flag) {
			for (int i=1; i<N+1; i++) {
				if(!selected[i]) sumB += P[i];
			}
			min = Math.min(min, Math.abs(sumA - sumB));
			check = true;
		}
//		System.out.println(sumA + " " + sumB);
	}
	private static boolean BFSB() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<N+1; i++) {
			if(!selected[i]) {
				q.offer(i);
				visitedB[i] = true;
				break;
			}
		}
		
		int count = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int j=0; j<size; j++) {
				int tmp = q.poll();
				
				for(int i=1; i<N+1; i++) {
					if(!selected[i] && !visitedB[i] && map[tmp][i] == 1) {
						q.offer(i);
						visitedB[i] = true;
						count++;
					}
				}
			}
		}
		if(count == bCount) return true;
		return false;
	}
}