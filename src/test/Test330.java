package test;

public class Test330 {
    public int minPatches(int[] nums, int n) {
        int ans = 0;
        int index = 0;
        long n0 = 1;
        for (;n0 <= n;) {
            if (index < nums.length && nums[index] <= n0) {
                n0 += nums[index];index ++;
            } else {
                ans ++;n0 <<= 1;
            }
        }
        return ans;
    }
}
