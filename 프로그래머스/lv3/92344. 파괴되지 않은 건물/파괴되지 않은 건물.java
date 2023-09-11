import java.util.Arrays;
import java.awt.Point;

class Solution {
    static int[][] map;
    static int answer = 0;
    
    public int solution(int[][] board, int[][] skill) {
        map = new int[board.length+1][board[0].length+1];
        
        for (int i=0; i<skill.length; i++){
            int type = skill[i][0];
            Point start = new Point(skill[i][1],skill[i][2]);
            Point end = new Point(skill[i][3],skill[i][4]);
            int degree = skill[i][5];
            sum(type, start, end, degree);    
        }
        
        cal(board);
        
        return answer;
        
    }
    
    public static void sum(int type, Point start, Point end, int degree) {
        if(type == 1) degree *= -1;
        
        map[start.x][start.y] += degree;
        map[start.x][end.y + 1] -= degree;
        map[end.x + 1][start.y] -= degree;
        map[end.x + 1][end.y + 1] += degree;
    }
    
    public static void cal(int[][] board) {
        
        
        for(int i=0; i<=board.length; i++){
            for (int j=1; j<=board[0].length; j++){
                map[i][j] += map[i][j-1];
            }
        }
        
        for(int i=0; i<=board[0].length; i++){
            for (int j=1; j<=board.length; j++){
                map[j][i] += map[j-1][i];
            }
        }
        
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                board[i][j] += map[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
    }
    
}