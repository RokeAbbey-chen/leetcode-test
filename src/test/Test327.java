package test;

import java.util.HashMap;
import java.util.Map;

public class Test327 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        HashMap<Long, Integer>[] maps = new HashMap[]{new HashMap(), new HashMap()};
        int count = 0;
        for (int i = 0; i < nums.length; i ++) {
            long numI = nums[i];
            maps[i & 1].clear();
            for (Map.Entry<Long, Integer> entry: maps[(i - 1) & 1].entrySet()) {
                long k = entry.getKey();
                int v = entry.getValue();
                long new_k = k + numI;
                if (new_k >= lower && new_k <= upper) {
                    count += v;
                }
                maps[i & 1].put(new_k, v);
            }
            Integer dulpCount = maps[i & 1].get(numI);
            if (null == dulpCount) { dulpCount = 0; }
            maps[i & 1].put(numI, dulpCount + 1);
            if (numI >= lower && numI <= upper) {
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Test327 test = new Test327();
//        int[] nums = {-2, 5, -1};
//        int lower = -2;
//        int upper = 2;

//        int[] nums = {0};
//        int lower = 0, upper = 0;

//        int[] nums = {-3, 1, 2, -2, 2, -1};
//        int lower = -3, upper = -1;

//        int[] nums = {-2147483647,0,-2147483647,2147483647};
//        int lower = -564, upper = 3864;

        int[] nums = {0, 0};
        int lower = 0, upper = 0;


        int result = test.countRangeSum(nums, lower, upper);
        System.out.println("result:" + result);

    }
}
