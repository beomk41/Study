import java.io.*;
import java.util.*;

public class Main {
    static int n, max = 0;
    static int[] arr;
    static int[] DL, DR;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        DL = new int[n];
        DR = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        L_LIS();
        R_LIS();
        check();

//        System.out.println(Arrays.toString(DL));
//        System.out.println(Arrays.toString(DR));

        System.out.println(max-1);
    }

    private static void check() {
        for (int i=0; i<n; i++){
            max = Math.max(max, DL[i]+DR[i]);
        }
    }

    static void L_LIS() {
        for (int i = 0; i < n; i++) {
            DL[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) DL[i] = Math.max(DL[i], DL[j] + 1);
            }
        }
    }

    static void R_LIS() {
        for (int i = n-1; i >= 0; i--) {
            DR[i] = 1;
            for (int j = n-1; j > i; j--) {
                if (arr[i] > arr[j]) DR[i] = Math.max(DR[i], DR[j] + 1);
            }
        }
    }
}