import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] map = new int[9][9];
    static List<Point> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<9; i++) {
        	String tmp = br.readLine();
        	for (int j=0; j<9; j++) {
        		map[i][j] = Integer.parseInt(tmp.charAt(j)+"");
        		if(map[i][j] == 0) {
        			list.add(new Point(i,j));
        		}
        	}
        }
        
        sudoku(0);
        
        for (int i=0; i<9; i++) {
        	for (int j=0; j<9; j++) {
        		System.out.print(map[i][j]);
        	}
        	System.out.println();
        }
    }
    static boolean check = false;

    public static boolean sudoku(int count) {
    	if(count == list.size()) return true;
    	
    	int tmpx = list.get(count).x;
    	int tmpy = list.get(count).y;
    	
    	for (int i=1; i<=9; i++) {
    		if(checkC(tmpx, tmpy, i) && checkR(tmpx, tmpy, i) && checkB(tmpx, tmpy, i)) {
    			map[tmpx][tmpy] = i;
    			check = sudoku(count+1);
    		}
    		if (check) return true;
    	}
    	map[tmpx][tmpy] = 0;
    	return false;
    }

	private static boolean checkC(int x, int y, int k) {
		for (int i=0; i<9; i++) {
			if(map[i][y] == k) return false;
		}
		return true;
	}

	private static boolean checkR(int x, int y, int k) {
		for (int i=0; i<9; i++) {
			if(map[x][i] == k) return false;
		}
		return true;
	}

	private static boolean checkB(int x, int y, int k) {
		int tmpx = 0;
		int tmpy = 0;
		
		if (x < 3) tmpx = 0;
		else if(x < 6) tmpx = 3;
		else if(x < 9) tmpx = 6;
		
		if (y < 3) tmpy = 0;
		else if(y < 6) tmpy = 3;
		else if(y < 9) tmpy = 6;
		
		for (int i=tmpx; i<tmpx+3; i++) {
			for (int j=tmpy; j<tmpy+3; j++) {
				if(map[i][j] == k) return false;
			}
		}
		return true;
	}
}