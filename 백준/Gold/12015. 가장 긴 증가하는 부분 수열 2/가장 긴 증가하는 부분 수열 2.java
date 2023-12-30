import java.io.*;
import java.util.*;

public class Main {
    static int n, max = 0;
    static int[] arr;
    static int[] list;
    static int len = 1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        list = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS();

        System.out.println(len);
    }

    static void LIS() {
        list[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if(arr[i] < list[0]) {
                list[0] = arr[i];
            }
            else if (arr[i] > list[len-1]) {
                list[len++] = arr[i];
            }
            else {
                int idx = Arrays.binarySearch(list, 0, len, arr[i]);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                list[idx] = arr[i];
            }

        }
    }

}