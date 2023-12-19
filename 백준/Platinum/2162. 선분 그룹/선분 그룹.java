import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Line[] lines = new Line[n]; // 입력으로 들어온 선분
        parent = new int[n];        // 부모를 가리키는 배열

        for(int i = 0 ; i < n ; i ++) {
            // 입력
            lines[i] = new Line(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

            // 부모 초기화
            parent[i] = i;
        }

        // Union
        for(int i = 0 ; i < n - 1 ; i ++) {
            for(int j = i + 1 ; j < n ; j ++) {

                // CCW를 사용해 lines[i], lines[j]가 만나는지 확인
                if( meet(lines[i], lines[j]) ) {
                    // 만나면 union 과정 진행
                    int iParent = getParent(i);
                    int jParent = getParent(j);

                    if(iParent != jParent) {
                        // 최종 부모가 다르면 두 그룹 합침
                        parent[iParent] = jParent;
                    }
                }
            }
        }

        // 그룹 카운트 및 최대값 구하기
        Map<Integer, Integer> group = new HashMap<>();
        int maxSize = 1;

        for(int i = 0 ; i < n ; i ++) {
            int iParent = getParent(parent[i]);

            if( group.containsKey(iParent) ) {
                // 이미 전에 그룹이 추가되어 었으면 + 1
                group.put(iParent, group.get(iParent) + 1);

                // 최대값 갱신
                if(maxSize < group.get(iParent)) {
                    maxSize = group.get(iParent);
                }
            } else {
                // 그룹에 추가되어 있지 않으면 새로 생성
                group.put(iParent, 1);
            }
        }

        System.out.println(group.size());
        System.out.println(maxSize);
    }

    static int getParent(int idx) {
        if(parent[idx] == idx) return idx;

        // 부모의 부모를 찾는 과정에서 부모 갱신도 같이 진행
        parent[idx] = getParent(parent[idx]);
        return parent[idx];
    }

    static boolean meet(Line l1, Line l2) {
        int res1 = ccw(l1, l2.x1, l2.y1) * ccw(l1, l2.x2, l2.y2);
        int res2 = ccw(l2, l1.x1, l1.y1) * ccw(l2, l1.x2, l1.y2);

        if(res1 == 0 && res2 == 0) {
            // 일직선인 상황인데 겹치는지 안겹치는지 재확인 필요
            if(Math.min(l1.x1, l1.x2) <= Math.max(l2.x1, l2.x2) && Math.min(l2.x1, l2.x2) <= Math.max(l1.x1, l1.x2)
            && Math.min(l1.y1, l1.y2) <= Math.max(l2.y1, l2.y2) && Math.min(l2.y1, l2.y2) <= Math.max(l1.y1, l1.y2)) {
                return true;
            }
            else return false;
        } else if(res1 <= 0 && res2 <= 0) {
            return true;
        } else {
            return false;
        }
    }

    static int ccw(Line l1, int x3, int y3) {
        int x1 = l1.x1;
        int y1 = l1.y1;
        int x2 = l1.x2;
        int y2 = l1.y2;

        int result = ( x1*y2 + x2*y3 + x3*y1 ) - ( x1*y3 + x2*y1 + x3*y2 );

        if(result == 0) return 0;       // 일직선
        else if(result > 0) return 1;   // 반시계 방향
        else return -1;                 // 시계 방향
    }

    static class Line {
        int x1, y1, x2, y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}