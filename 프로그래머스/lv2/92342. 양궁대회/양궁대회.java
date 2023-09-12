import java.util.*;

class Solution {
    static int max = 0;
    static int[] ans = new int[11];
    static int[] selected;
    static int[] fail = new int[]{-1};
    
    public int[] solution(int n, int[] info) {
        
        selected = new int[n];
        perm(0, 0, n, info);
        
        if(max == 0) return fail;
        return ans;
    }
    
    public static void perm(int start, int cnt, int n, int[] info) {
        if(cnt == n) {
            cal(info);
            return;
        }
        
        for (int i = start; i < 11; i++){
            selected[cnt] = i;
            perm(i, cnt+1, n, info);
        }
    }
    
    public static void cal(int[] info) {
        int[] copy = new int[11];
        for (int s : selected) {
            copy[10 - s]++;
        }
        
        int rScore = 0;
        int pScore = 0;
        
        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            
            if (copy[i] > info[i]) rScore += score;
            else if (copy[i] < info[i]) pScore += score;
            else if (info[i] != 0 && copy[i] == info[i]) pScore += score;
        }
        
        if(rScore - pScore > max) {
            max = rScore - pScore;
            ans = copy;
        }
        return;
    }
}