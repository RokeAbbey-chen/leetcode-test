package test;

import java.util.Arrays;
import java.util.HashMap;

/*
*
* Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
* */
public class Test523 {

    public boolean checkSubarraySum(int[] nums, int k) {

        if (nums.length < 2){ return false; }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 && nums[i - 1] == 0){ return true; }
        }
        if (k == 0) {
            return false;
        }

    // 其实对k取绝对值并且重新赋值给k这样用数组hash会更快一点
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        HashMap<Integer, Integer>  map = new HashMap<>();
        map.put(sum[0] % k, 0);
        for (int i = 1; i < nums.length; i ++){
            sum[i] = sum[i - 1] + nums[i];
            Integer n = map.get(sum[i] % k) ;

            if (sum[i] % k == 0 || n != null && i - n >= 1){
                return true;
            }
            else if (n == null ){
                map.put(sum[i] % k, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
