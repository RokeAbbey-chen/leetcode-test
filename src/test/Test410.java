package test;

/**
 * 410. Split Array Largest Sum
 Hard

 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:

 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:

 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.

 超时
 */
public class Test410 {

    private int minMax;
    public int splitArray(int[] nums, int m) {
        if (m <= 0){ return 0; }
        minMax = Integer.MAX_VALUE;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i ++){
            sums[i] = sums[i - 1] + nums[i];
        }
        help(nums, m, 0, 0, sums);
        return minMax;
    }

    private void help(int[] nums, int m, int index, int tmpSum, int[] sums){
        int reference = index > 0 ? sums[index - 1] : 0;
        if (m <= 1) {
            minMax = Math.min(Math.max(tmpSum, sums[sums.length - 1] - reference), minMax);
        } else {
            for (int i = index; i <= nums.length - m; i++) {
                help(nums, m - 1, i + 1, Math.max(tmpSum, sums[i] - reference), sums);
            }
        }

    }

    public static void main(String[] args) {

    }
}
