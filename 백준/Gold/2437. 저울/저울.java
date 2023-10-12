import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] input;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		int cnt = 0;
		int result = 0;
		
		for (int i=0; i<N-1; i++) {
			cnt += input[i];
			
			if (cnt+1 < input[i+1]) {
				result = cnt+1;
				break;
			}
		}
		
		if(input[0] > 1) result= 1;
		else if (result == 0) result = cnt + input[N-1] + 1;

		System.out.println(result);
	}
}
