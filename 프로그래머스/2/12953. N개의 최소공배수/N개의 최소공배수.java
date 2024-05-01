import java.util.*;
import java.io.*;

class Solution {
    static int[] list = new int[101];
    
    public int solution(int[] arr) {
        int answer = 1;
        
        for (int i=0; i<arr.length; i++) {
            int[] getList = new int[101];
            int num = arr[i];
            
            while (num != 1) {
                for (int j=2; j<=num; j++) {
                    if(num % j == 0) {
                        getList[j]++;
                        num /= j;
                        break;
                    }
                }
            }
            
            for (int j=0; j<101; j++) {
                list[j] = Math.max(list[j], getList[j]);
            }
        }
        
        for (int i=0; i<101; i++) {
            if(list[i] != 0) {
                System.out.println(i + " " + list[i]);
                answer *= Math.pow(i,list[i]);
            }
        }
        
        return answer;
    }
}