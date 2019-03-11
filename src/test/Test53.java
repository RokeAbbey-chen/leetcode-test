package test;

/*
* Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*
* */
public class Test53 {
    public static void main (String[] args){
        int[] nums = {-2, 1, -1, 4, -1, 2, 1, -5, 4};
        Test53 t = new Test53();
        System.out.println(t.maxSubArray(nums));
    }
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int i = 0;
        for(; i < nums.length && nums[i] <= 0; i++) { maxSum = Math.max(maxSum, nums[i]); }
        for(; i < nums.length; i ++){
            if(sum <= 0 ) {
                if(nums[i] <= 0) {continue;}
                else {sum = nums[i];}
            } else {sum += nums[i];}

            if(sum > maxSum) { maxSum = sum; }
        }
        return maxSum;
    }
}
