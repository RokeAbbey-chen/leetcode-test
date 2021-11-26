package test;

import java.util.ArrayList;
import java.util.Arrays;

public class Test164 {
    public int maximumGap(int[] nums) {
        if (nums.length <= 1) return 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i ++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int bucketSize  = (max - min) / nums.length + 1;
        int bucketCount = (max - min) / bucketSize  + 1;
        if (1 == bucketCount) return max - min;
        int[] bucketMax = new int[bucketCount];
        int[] bucketMin = new int[bucketCount];

        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);

        for (int i = 0; i < nums.length; i ++) {
            int idx = (nums[i] - min) / bucketSize;
            bucketMax[idx] = Math.max(nums[i], bucketMax[idx]);
            bucketMin[idx] = Math.min(nums[i], bucketMin[idx]);
        }

        int maxGap  = Integer.MIN_VALUE;
        int curMin  = Integer.MAX_VALUE;
        int lastMax = bucketMax[0];
        for (int i = 1; i < bucketCount; i ++) {
            curMin = bucketMin[i];
            if (Integer.MAX_VALUE == curMin) continue;
            maxGap = Math.max(maxGap, curMin - lastMax);
            lastMax = Math.max(bucketMax[i], Integer.MIN_VALUE);
        }
        return maxGap;
    }


    public static void main(String[] args) {
        Test164 t = new Test164();
//        int[] nums = new int[]{8, 1, 2, 8, 12, 7};
//        int[] nums = new int[]{8};
        int[] nums = new int[]{3, 6, 9, 100, 97, 10, 297, 12681, 8737, 1821, 4000, 0};
                            // 0  3  6  9    10  97  100  297    1821  4000  8737  12681
                            // 0  0  0  0    0   0   0    11     8     1     3     0
        int gap = t.maximumGap(nums);
        System.out.println("gap:" + gap);
    }

}
