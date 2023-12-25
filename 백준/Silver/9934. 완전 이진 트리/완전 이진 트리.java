import java.util.*;
import java.io.*;

public class Main {
	static int K; // 노드 깊이
	static int[] arr; // 노드 방문 순서
	static List<ArrayList<Integer>> list; // 노드 깊이별 노드 번호를 담아줄 공간
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); 
		
		// 문제에 나온 노드의 갯수 구하는 공식으로 배열 크기 생성
		arr = new int[(int)Math.pow(2, K)-1]; // pow는 double type?
		
           // 노드 순서 삽입
		for(int i=0; i<arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
	   
		list = new ArrayList<>();
		for(int i=0; i<K; i++) list.add(new ArrayList<>()); // 노드의 깊이만큼 list안에 Arraylist 생성
		
		dfs(0, arr.length-1, 0);
		
		for(int i=0; i<K; i++) {
			for(int j : list.get(i)) sb.append(j).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int start, int end, int depth) {
		
		// 제일 하위 노드 도달 시
		if(depth == K) return;
			
		// 상위 노드 배열 번호
		int middle = (start + end) / 2;
		
		// depth별 상위 노드 삽입
		list.get(depth).add(arr[middle]);
		
		// 현재 노드에서 왼쪽 노드 탐색
		dfs(start, middle-1,depth+1);
		
		// 현재 노드에서 오른쪽 노드 탐색
		dfs(middle+1, end, depth+1);
	}
}