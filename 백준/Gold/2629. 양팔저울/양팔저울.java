import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static List<Integer> li = new ArrayList<>();
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static String result = "";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int size = li.size();
			for (int j=0; j<size; j++) {
				int plus = li.get(j)+num;
				int minus = Math.abs(li.get(j)-num);
				
				if(map.get(plus) == null) li.add(plus);
				if(map.get(minus) == null) li.add(minus);

				map.put(plus, 0);
				map.put(minus, 0);
			}
			if(map.get(num) == null) li.add(num);
		}
		
//		for (int j=0; j<li.size(); j++) {
//			System.out.print(li.get(j) + " ");
//		}
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int num = Integer.parseInt(st.nextToken());
			boolean flag = false;
			for (int j=0; j<li.size(); j++) {
				if(li.get(j) == num) {
					result += "Y ";
					flag = true;
					break;
				}
			}
			if (!flag) result += "N ";
		}
		
		System.out.println(result);
	}
}
