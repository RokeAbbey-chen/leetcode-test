package test;

import java.util.Arrays;

/*
* Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
* target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
* */
public class Test16 {
    public static void main(String[] args){
        Test16 t = new Test16();
        int[] nums = {-1, 2, 1, -4};
        nums = new int[]{-1, 2, 1, -4};

        int target = 1;
//        target = 0;
        System.out.println(t.threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MIN_VALUE >> 1;
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            for(int left = i + 1, right = nums.length - 1; left < right; ){
                int idealValue = target - nums[i];
                int realValue = nums[left] + nums[right];
//                System.out.println("nums[" + i + "] = " + nums[i] + ", nums[" + left + "] = " + nums[left] + ", nums[" + right + "] = " + nums[right]
//                    + ", idealValue = " + idealValue + ", realValue = " + realValue);
                if(idealValue == realValue)
                    return target;
                else {
                    if(Math.abs(target - result) > Math.abs(realValue - idealValue))
                        result = nums[i] + nums[left] + nums[right];
                    if (realValue < idealValue) {
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                    }
                    if (realValue > idealValue) {
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                }


            }
        }
        if(result == Integer.MIN_VALUE >> 1)
            result = 0;
        return result;
    }
}
