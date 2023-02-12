package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test699 {
    public List<Integer> fallingSquares(int[][] positions) {
        int len = positions.length;
        Integer[] hs = new Integer[len];
        int maxH = 0;
        ArrayList<Integer> result = new ArrayList<>(len);
        for (int i = 0; i < len; i ++) {
            int hi = positions[i][1];
            int li = positions[i][0];
            int ri = li + hi;
            for (int j = i - 1; j >= 0; j --) {
                int lj = positions[j][0];
                int rj = lj + positions[j][1];
                if (!(ri <= lj || li >= rj)) {
                    hi = Math.max(hi, hs[j] + positions[i][1]);
                }
            }
            maxH = Math.max(maxH, hi);
            hs[i] = hi;
            result.add(maxH);
        }
        return result;
    }

    public static void main(String[] args) {
        Test699 test = new Test699();
        int[][] pos = {{1, 3}, {8, 1}, {5, 3}, {4, 1}};

        List<Integer> result = test.fallingSquares(pos);
        System.out.println("result:" + result);
    }


}
