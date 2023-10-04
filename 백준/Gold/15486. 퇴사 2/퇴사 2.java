import java.util.*;
import java.io.*;

public class Main {
	static int N, dMax = 0, max = Integer.MIN_VALUE;
	static int[] time, price, D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N + 1];
		price = new int[N + 1];
		D = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i <= N; i++) {
			dMax = Math.max(dMax, D[i]);
			if (i + time[i] > N+1) continue;
			D[i + time[i]] = Math.max(D[i + time[i]], dMax + price[i]);
			max = Math.max(max, D[i + time[i]]);
		}
		System.out.println(max);
	}
}