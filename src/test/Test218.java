package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Arrays.sort(buildings, Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> result = new ArrayList<>();
        result.add(Arrays.asList(buildings[0][0], buildings[0][2]));
        int startI = 1;
        for (int i = startI; i < buildings.length; i ++) {
            if (buildings[i - 1][1] >= buildings[i][0] && buildings[i - 1][2] < buildings[i][2]) {
                if (buildings[i - 1][1] >= buildings[i][1]) {
                    // 在横坐标上，前一个把本个完全包括这种情况
                } else {
                    // 在横坐标上，前一个与本个有交集，但是不包含的情况
                }
            } else if (buildings[i - 1][1] >= buildings[i][0]) {
                // 在高度上，当前个比前一个矮的情况
                if (buildings[i - 1][1] < buildings[i][1]) {
                    // 虽然比较矮，但是本个的右边界比前一个的右边界要右的情况。
                }
            }

        }
        return null;
    }
}
