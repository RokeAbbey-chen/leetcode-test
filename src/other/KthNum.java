package other;

import java.util.Arrays;

public class KthNum {
//    public int getTheKthNum(int[] nums){
//
//    }
    private static int getTheKthNum(int[] nums, int start, int end, int k){ // include start , end
        if (end - start + 1 <= 5){
            Arrays.sort(nums, start, end + 1);
            return nums[(start + end) >> 1];
        }

        int t = 0;
        int i2;
        for (int i = 5; i <= nums.length; i += 5 ){
            i2 = Math.min(i, nums.length);
            Arrays.sort(nums, i - 5, i2);
            exchange(nums, t++, (i - 5) + (k - 1));
        }
        int pivotIndex = (start + t) >> 1;
        getTheKthNum(nums, start, start + t - 1, pivotIndex);
        int m = partition(nums, start, start + t - 1, pivotIndex);
        int cur = m - start + 1;
        if(cur == k){
            return nums[k];
        }
        else if (k < cur){
            return getTheKthNum(nums, start, start + t, k);
        }
        else {
            return getTheKthNum(nums, start + t + 1, end, k - cur);
        }


    }

    private static void exchange(int[] nums, int index1, int index2){
        nums[index1] ^= nums[index2] ^= nums[index1] ^= nums[index2];
    }

    private static int partition(int[] nums, int start, int end, int pivotIndex){
        if (nums == null || nums.length <= 0) { return -1; }
        exchange(nums, end, pivotIndex);
        int l = start;
        for(int i = start; i <= end; i ++){
            if(nums[i] < nums[pivotIndex]){
                exchange(nums, l++, i);
            }
        }
        exchange(nums, l, end);
        return l;
    }
}
