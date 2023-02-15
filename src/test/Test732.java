package test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test732 {
    public static class MyCalendarThree {
//        private TreeMap<Integer, Integer> tree = new TreeMap<>();
        private int[][] tree = new int[401][];
        private int max = 0;
        private int len = 0;
        public MyCalendarThree() {
            int len = 0;
        }

        public int book(int startTime, int endTime) {

            int p = findParent(startTime, endTime);
            if (-1 == p) {
                tree[p] = new int[]{startTime, endTime, 1};
                max = 1;
                return 1;
            }



        }

        public int findParent(int t0, int t1) {
            int p = -1;
            for (int i = 0; i < len; i ++) {
                int tt0 = tree[i][0], tt1 = tree[i][1];
                if (t0 < tt0 || t0 == tt0 && t1 < tt1) {
                    p = i;
                    i = (i << 1) + 1;
                } else if (t0 > tt0 || t0 == tt0 && t1 > tt1) {
                    p = i;
                    i = (i << 1) + 2;
                } else {
                    return i;
                }
            }
            return p;
        }

    }

}
