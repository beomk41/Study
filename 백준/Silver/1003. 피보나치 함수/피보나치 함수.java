import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int num1 = 0;
			int num2 = 1;
			
			for(int i = 0; i < N; i++) {
				if(i % 2 == 0) num2 += num1;
				else num1 += num2;
			}
			if(N == 0) System.out.println(1 + " " + 0);
			else if(N % 2 == 0) System.out.println(num2 + " " + num1);
			else System.out.println(num1 + " " + num2);
		}
	}
}