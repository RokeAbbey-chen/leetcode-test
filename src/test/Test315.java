package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test315 {

    public List<Integer> countSmaller(int[] nums) {
        int[] counts = new int[nums.length];
        calculateCounts(nums, counts, 0, nums.length - 1);
        ArrayList<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < counts.length; i++) {
            result.add(counts[i]);
        }
        return result;
    }

    private int[] calculateCounts(int[] nums, int[] counts, int left, int right) {
        if (left == right) {
            return new int[]{left};
        }
        int mid = (left + right) >> 1;
        int[] indexesLeft = calculateCounts(nums, counts, left, mid);
        int[] indexesRight = calculateCounts(nums, counts, mid + 1, right);
        int[] newIndexes = new int[right - left + 1];
        int i = 0, j = 0, k = 0;
        while (i < indexesLeft.length && j < indexesRight.length) {
            if (nums[indexesLeft[i]] <= nums[indexesRight[j]]) {
                newIndexes[k] = indexesLeft[i];
                counts[newIndexes[k]] += k - i;
                k ++; i ++;
            } else {
                newIndexes[k] = indexesRight[j];
//                counts[newIndexes[k]] += k - j;
                k ++; j ++;
            }
        }

        for (; i < indexesLeft.length; k++, i++) {
            newIndexes[k] = indexesLeft[i];
            counts[newIndexes[k]] += k - i;
        }

        for (; j < indexesRight.length; k++, j++) {
            newIndexes[k] = indexesRight[j];
//            counts[newIndexes[k]] += k - j;
        }

        return newIndexes;
    }


    public static void main(String[] args) {
        Test315 t = new Test315();
//        int[] nums = new int[]{3, 10, 2, 1, 5, 4};
//        int[] nums = new int[]{3, 10, 2, 1, 5};
        int[] nums = new int[]{1, 4, 3, 2, 1, 6, 9, 10, 2, 3, 2, 1, 5};

        List<Integer> result = t.countSmaller(nums);
        System.out.println(result);
    }
}
