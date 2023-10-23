import java.io.*;
import java.util.*;

public class Main {
    static int n, max = 0;
    static int[] arr;
    static int[] DL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        DL = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        LIS();
        check();

        System.out.println(max);
    }

    private static void check() {
        for (int i=0; i<n; i++){
            max = Math.max(max, DL[i]);
        }
    }

    static void LIS() {
        for (int i = 0; i < n; i++) {
            DL[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) DL[i] = Math.max(DL[i], DL[j] + 1);
            }
        }
    }

}