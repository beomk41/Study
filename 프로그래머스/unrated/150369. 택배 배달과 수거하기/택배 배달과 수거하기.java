import java.io.*;
import java.util.*;

class Solution {
    static int d=0, p=0, cnt=0;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for (int i = n-1; i >= 0; i--) {
            int cnt = 0;
            
            if (deliveries[i] > 0 || pickups[i] > 0) {
                while (d < deliveries[i] || p < pickups[i]) {
                    d += cap;
                    p += cap;
                    cnt += 1;
                }
                
                d -= deliveries[i];
                p -= pickups[i];
                answer += (i+1) * cnt * 2;
            }
        }
        
        return answer;
    }
}