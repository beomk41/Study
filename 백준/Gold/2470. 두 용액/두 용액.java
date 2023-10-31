import java.io.*;
import java.util.*;

public class Main {
    static int N, start, end;
    static long[] arr;
    static long resA, resB, min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        start = 0;
        end = N-1;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        while (start < end){
            long sum = arr[start] + arr[end];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                resA = arr[start];
                resB = arr[end];

                if (sum == 0)
                    break;
            }

            if (sum < 0) start++;
            else end--;
        }


        System.out.println(resA + " " + resB);
    }

}