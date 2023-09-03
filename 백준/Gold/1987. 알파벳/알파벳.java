import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int max=1;
	static int[] xOffset=new int[] {0,0,1,-1};
	static int[] yOffset=new int[] {1,-1,0,0};
	static int R,C;
	static char[][] map;
	static boolean visited[][];
	static HashSet<Character> hashSet;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		hashSet=new HashSet<Character>();
		map=new char[R][C];
		visited=new boolean[R][C];
		for(int i=0;i<R;i++) {
			String line=br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=line.charAt(j);
			}
		}
//		if(R==1 && C==1) System.out.println(1);
		cnt++;
		hashSet.add(map[0][0]);
		dfs(0,0);
		System.out.println(max);
		
		
	}
	public static void dfs(int x,int y) {
//		if(max == R*C) return;
		for(int i=0;i<4;i++) {
			
			int nx=x+xOffset[i];
			int ny=y+yOffset[i];

			if (nx<0 || nx>=R || ny<0 || ny>=C || hashSet.contains(map[nx][ny])) continue;
			hashSet.add(map[nx][ny]);
			cnt++;
			dfs(nx,ny);
			max=Math.max(max,cnt);
			cnt--;
			hashSet.remove(map[nx][ny]);
		}
	}

}