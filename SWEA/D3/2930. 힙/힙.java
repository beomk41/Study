import java.io.*;
import java.util.*;

public class Solution {

    static class MaxHeap {
        public int parent(int i) {
            return i >> 1;
        }

        public int left(int i) {
            return i << 1;
        }

        public int right(int i) {
            return i << 1 | 1;
        }

        int MAX_N = 100000;

        int[] data = new int[MAX_N + 1];
        int size = 0;

        public void push(int x) {
            data[++size] = x;
            for (int i = size; parent(i) != 0 && data[parent(i)] < data[i]; i >>= 1) {
                int tmp = data[i];
                data[i] = data[parent(i)];
                data[parent(i)] = tmp;

            }
        }

        public int peek() {
            if (size != 0) {
                return data[1];
            } else return -1;
        }

        public int pop() {
            int result = -1;

            if (size == 0) return result;

            else {
                result = data[1];
                data[1] = data[size--];

                for (int i = 1; left(i) <= size; ) {
                    if (left(i) == size || data[left(i)] > data[right(i)]) {
                        if (data[i] < data[left(i)]) {
                            int tmp = data[left(i)];
                            data[left(i)] = data[i];
                            data[i] = tmp;
                            i = left(i);
                        }
                        else break;
                    }
                    else {
                        if (data[i] < data[right(i)]) {
                            int tmp = data[right(i)];
                            data[right(i)] = data[i];
                            data[i] = tmp;
                            i = right(i);
                        }
                        else break;
                    }
                }
            } return result;
        }
    }

        static int[][] D = new int[1001][1001];

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String result = "";
            int T = Integer.parseInt(br.readLine());

            for (int tc = 1; tc <= T; tc++) {
                MaxHeap heap = new MaxHeap();
                bw.write(result + "#" + tc +" ");
                int N = Integer.parseInt(br.readLine());

                for (int i=0; i<N; i++){
                    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                    if(st.nextToken().equals("1")){
                        heap.push(Integer.parseInt(st.nextToken()));
                    }
                    else bw.write(result + heap.pop() + " ");
                }
                bw.write(result + "\n");
            }
            bw.flush();
        }
    }