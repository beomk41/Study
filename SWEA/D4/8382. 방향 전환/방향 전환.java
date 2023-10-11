import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int dix = Math.abs(x2-x1);
			int diy = Math.abs(y2-y1);
			int max = Math.max(dix, diy);
			int min = Math.min(dix, diy);
			int result = 0;
			
			if (dix == diy) result = dix*2;
			else if (max % 2 == 0 && min % 2 == 0) result = max*2;
			else if (max % 2 == 1 && min % 2 == 1) result = max*2;
			else if (max % 2 == 1 && min % 2 == 0) result = max*2-1;
			else if (max % 2 == 0 && min % 2 == 1) result = max*2-1;
			
			bw.write("#" + tc + " " + result +"\n");
		}
		bw.flush();
	}
}