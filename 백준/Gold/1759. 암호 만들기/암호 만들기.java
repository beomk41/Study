import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static List<Character> c = new ArrayList<>(); // 자음 리스트
	static List<Character> v = new ArrayList<>(); // 모음 리스트
	static int cCount, vCount; // 사용 할 자음 모음 개수 
	static char[] getC, getV; // 선택된 자음 모음
	static List<String> res = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<C; i++) {
			char tmp = st.nextToken().charAt(0);
			
			if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u') v.add(tmp);
			else c.add(tmp);
		}
		
		cCount = L-1;
		if(cCount > c.size()) {
			cCount = c.size();
		}
		
		vCount = L-cCount;
		
		makePW();
		Collections.sort(res);
		for (int i=0; i<res.size(); i++) {
			System.out.println(res.get(i));
		}
	}

	private static void makePW() throws Exception {
		while(cCount>1) {
			getC = new char[cCount];
			getV = new char[vCount];
			combC(0,0);
			cCount--;
			vCount++;
		}
	}

	private static void combC(int cnt, int start) throws Exception {
		if(cnt == cCount) {
			combV(0,0);
			return;
		}
		
		for (int i=start; i<c.size(); i++) {
			getC[cnt] = c.get(i);
			combC(cnt+1,i+1);
		}
	}

	private static void combV(int cnt, int start) throws Exception {
		if(cnt == vCount) {
			List<Character> result = new ArrayList<>();
			for (int i=0; i<getC.length;i++) {
				result.add(getC[i]);
			}
			for (int i=0; i<getV.length;i++) {
				result.add(getV[i]);
			}
			Collections.sort(result);
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<result.size(); i++) {
				sb.append(result.get(i));
			}
			
			res.add(sb.toString());
			return;
		}
		
		for (int i=start; i<v.size(); i++) {
			getV[cnt] = v.get(i);
			combV(cnt+1,i+1);
		}
	}
}