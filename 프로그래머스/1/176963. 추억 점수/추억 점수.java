class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        for (int i=0; i<photo.length; i++) {
            int sum = 0;
            for (int j=0; j<photo[i].length; j++) {
                String getName = photo[i][j];
                
                for (int k=0; k<name.length; k++) {
                    if (name[k].equals(getName)) {
                        sum += yearning[k];
                    }
                }
                
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}