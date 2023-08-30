import java.util.*;
import java.awt.Point;
import java.io.*;

public class Solution {
	static int N;
	static Point house, office, p[];
	static int min;
	static boolean[] isSelected;
	static Point[] select;
	static Point start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String result = "";
		int t = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= t; testCase++) {
			N = Integer.parseInt(br.readLine());
			isSelected = new boolean[N];
			select = new Point[N];
			p = new Point[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			office = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			min = Integer.MAX_VALUE;
			permutation(0);
			bw.write(result + "#" + testCase + " " + min + "\n");

		}
		bw.flush();
	}

	private static void permutation(int cnt) {
		if(cnt == N) {
			start = office;
			calDistance();
			return;
		}
		
		for (int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			select[cnt] = p[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
		
	}

	private static void calDistance() {
		int sum = 0;
		
		for (int i=0; i<N; i++) {
			sum += Math.abs(start.x - select[i].x) + Math.abs(start.y - select[i].y);
			start = select[i];
		}
		sum += Math.abs(start.x - house.x) + Math.abs(start.y - house.y);
		
		if(sum < min) min = sum;
	}
}