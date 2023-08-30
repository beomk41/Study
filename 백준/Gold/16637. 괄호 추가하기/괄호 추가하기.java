import java.io.*;
import java.util.*;

public class Main {
	static List<String> op, num, copy; // 숫자 기호 넣을 리스트
	static int N, max_size;
	static long max = Long.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		max_size = N/2;
		String str = br.readLine();
		
		num = new ArrayList<>();
		op = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				num.add(str.charAt(i)+"");
			}
			else op.add(str.charAt(i)+"");
		}
		if(N==1) {
			System.out.println(num.get(0));
			return;
		}
		int size = 1;
		while(size <= max_size) {
			selected = new int[size];
			comb(0,num.size(),size++);
		}
		
		System.out.println(max);
	}
	
	static int[] selected;
	
	private static void comb(int cnt, int start, int size) {
		if(cnt == size) {
			copy = new ArrayList<>();
			for (int i=0; i<num.size(); i++) {
				copy.add(num.get(i));
			}
			appends();
			appendop();
			calFirst(); // 괄호 처리
			max = Math.max(max, cal());
			return;
		}
		
		for (int i=start; i >= 2; i--) {
			selected[cnt] = i;
			comb(cnt+1, i-2, size);
		}
	}

	private static void calFirst() {
		for (int i=0; i<copy.size(); i++) {
			int a, b, tmp = 0;
			String oper;
			
			if(copy.get(i).equals("(")) {
				a = Integer.parseInt(copy.get(i+1)+"");
				oper = copy.get(i+2);
				b = Integer.parseInt(copy.get(i+3)+"");
				
				if(oper.equals("+")) {
					tmp = a+b;
				}
				else if(oper.equals("*")) {
					tmp = a*b;
				}
				else if(oper.equals("-")) {
					tmp = a-b;
				}
				
				copy.remove(i+4); // )
				copy.remove(i+3); // b
				copy.remove(i+2); // oper
				copy.remove(i+1); // a
				copy.set(i, Integer.toString(tmp));
			}
		}
	}

	private static long cal() { // 왼쪽부터 순차 계산
		long sum = Long.parseLong(copy.get(0));
		
		for (int i=1; i<copy.size(); i++) {
			if(copy.get(i).equals("+")) {
				sum += Long.parseLong(copy.get(i+1));
			}
			else if(copy.get(i).equals("-")) {
				sum -= Long.parseLong(copy.get(i+1));
			}
			else if(copy.get(i).equals("*")) {
				sum *= Long.parseLong(copy.get(i+1));
			}
		}
		
		return sum;
	}

	private static void appendop() {
		int cur = 0;
		for (int i=0; i<copy.size(); i++) {
			if(!copy.get(i).equals("+") && !copy.get(i).equals("-") &&
					!copy.get(i).equals("*") && !copy.get(i).equals("(") &&
					cur < op.size()) {
				if(i+1 < copy.size() && !copy.get(i+1).equals(")"))
				copy.add(i+1, op.get(cur++));
			}
		}
	}

	private static void appends() {
		for (int i=0; i<selected.length; i++) {
			copy.add(selected[i],")");
			copy.add(selected[i]-2,"(");
		}
	}
}