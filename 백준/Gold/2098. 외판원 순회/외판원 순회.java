import java.util.*;
import java.io.*;

public class Main {
	public static int n, adj[][], memo[][];
	public static int max = 10000001;
	
	public static int dfs(int now, int cnt, int visited) {
		if(memo[now][visited] > 0) return memo[now][visited];
		if(cnt == n) return adj[now][0] == 0 ? max : adj[now][0];
		
		int result = max;

		for(int next = 0; next < n; next++) {
			if(adj[now][next] == 0 || (visited & (1 << next)) != 0) continue;
			result = Math.min(result, adj[now][next] + dfs(next, cnt + 1, visited | (1 << next)));
		}
		
		return memo[now][visited] = result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		n = Integer.parseInt(br.readLine());
		adj = new int[n][n];
		memo = new int[n][1 << n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) adj[i][j] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dfs(0, 1, 1 << 0));
	}
}
