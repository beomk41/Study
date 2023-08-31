import java.io.*;
import java.util.*;

public class Main {
	static int N, player[][] ,copy[][], select[];
	static int  max = Integer.MIN_VALUE;
	static boolean[] isSelected; // 순열 사용
	static boolean[] base; // 1,2,3루 -> 이닝마다 초기화
	static int outCount;
	static int[] hitter; // 타선
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		select = new int[8];
		isSelected = new boolean[10];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<9; j++) {				
				player[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		perm(0); // 타선 순열로 생성
		
		System.out.println(max);
	}
	static int count = 0;
	private static void perm(int cnt) { // 타순 결정
		if(cnt == 8) {
			
			hitter = new int[9];
			hitter[3] = 0; // 4번 타자는 1번으로 고정
			int count = 0;
			for(int i=0; i<9; i++) {
				if(i==3) continue;
				hitter[i] = select[count++];
			}
			
			max = Math.max(max, simul()); // 시뮬레이션 결과 값과 max 비교
			return;
		}
		
		for (int i=1; i<=8; i++) {
			if(isSelected[i]) continue;
			
			select[cnt] = i;
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
	
	private static int simul() {
		int score = 0; // 시뮬레이션 당 스코어
		int curPlayer = 0;
		
		for(int i=0; i<N; i++) { // N 이닝 반복
			base = new boolean[3]; // 1,2,3루 초기화
			outCount = 0; // 아웃 카운트 초기화
			
			while(outCount < 3) { // 아웃 카운트가 3 이하까지 돌리기
				if(player[i][hitter[curPlayer]] == 0) {
					outCount++;
				}
				else if(player[i][hitter[curPlayer]] == 1) {
					if(base[2]) {
						score++;
						base[2] = false;
					}
					if(base[1]) {
						base[2] = true;
						base[1] = false;
					}
					if(base[0]) {
						base[1] = true;
						base[0] = false;
					}
					
					base[0] = true;
				}
				else if(player[i][hitter[curPlayer]] == 2) {
					for(int j=2; j>=1; j--) {
						 if(base[j]) {
							 base[j] = false;
							 score++;
						 }
					 }
					if(base[0]) {
						base[0] = false;
						base[2] = true;
					}
					 base[1] = true;
				}
				else if(player[i][hitter[curPlayer]] == 3) { // 3루타
					for(int j=0; j<3; j++) {
						 if(base[j]) {
							 base[j] = false;
							 score++;
						 }
					 }
					 base[2] = true;
				}
				else if(player[i][hitter[curPlayer]] == 4) { // 홈런
					 for(int j=0; j<3; j++) {
						 if(base[j]) {
							 base[j] = false;
							 score++;
						 }
					 }
					 score++;
				}
				if(++curPlayer > 8) curPlayer -= 9;
			}
		}
		return score;
	}
}