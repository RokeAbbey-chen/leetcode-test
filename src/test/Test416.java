package test;

import java.util.Arrays;

/**
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

        Note:

        Each of the array element will not exceed 100.
        The array size will not exceed 200.

        Example 1:

        Input: [1, 5, 11, 5]

        Output: true

        Explanation: The array can be partitioned as [1, 5, 5] and [11].

        Example 2:

        Input: [1, 2, 3, 5]

        Output: false

        Explanation: The array cannot be partitioned into equal sum subsets.
**/

public class Test416 {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int n : nums){ sum += n; }
        if ((sum & 1) == 1){ return false; }
        /* flags[i] = true 代表第 i 个元素已经被使用了*/
        boolean[] flags = new boolean[nums.length];
        flags[flags.length - 1] = true;
        return help(nums, flags, (sum >> 1) - nums[nums.length - 1]);
    }

    private boolean help(int[] nums, boolean[] flags, int target){
        if (target == 0){ return true; }
        int index = Arrays.binarySearch(nums, target);
        if (index < 0){
            index = -index - 2;
        }
        for (int i = index; i >= 0; i --){
            if (!flags[i]){
                flags[i] = true;
                if(help(nums, flags, target - nums[i])){ return true; }
                flags[i] = false;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Test416 t = new Test416();
        int[] nums = {1, 3, 5, 5, 5, 5};
        System.out.println(t.canPartition(nums));
    }
}
