import java.io.*;
import java.util.*;

public class Main {
    static int N, start, end;
    static long[] arr;
    static long resA, resB, resC, min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        
        boolean flag = false;
        for(int i=0; i<arr.length; i++) {
        	long tmp = arr[i];
        	start = i+1;
        	end = arr.length-1;
        	
        	while (start < end){
                long sum = arr[start] + arr[end] + tmp;

                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    resA = tmp;
                    resB = arr[start];
                    resC = arr[end];
                    
                    if (sum == 0) {
                    	System.out.println(resA + " " + resB + " " + resC);
                    	return;
                    }
                }

                if (sum < 0) start++;
                else end--;
            }
        	if(flag) break;
        }
        
        System.out.println(resA + " " + resB + " " + resC);
    }
}