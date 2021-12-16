package test;

public class Test239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[Math.max(nums.length - k + 1, 1)];
        int maxIndex = argMax(nums, 0, Math.min(nums.length, k) - 1, false);
        result[0] = nums[maxIndex];


        if (needReverse(nums)) {
            if (nums.length - k >= 0) {
                maxIndex = argMax(nums, nums.length - k, nums.length - 1, false);
                result[nums.length - k] = nums[maxIndex];
            }

            for (int i = nums.length - k; i >= 0; i --) {
                if (Math.abs(i - maxIndex) >= k) {
                    maxIndex = argMax(nums, i, i + k - 1, false);
                }
                else if (nums[i] >= nums[maxIndex]) { maxIndex = i;}

                result[i] = nums[maxIndex];
            }
        } else {
            for (int i = 0; i + k <= nums.length; i ++) {
                if (Math.abs(i + k - 1 - maxIndex) >= k) {
                    maxIndex = argMax(nums, i, i + k - 1, true);
                }
                if (nums[i + k - 1] >= nums[maxIndex]) maxIndex = i + k - 1;
                result[i] = nums[maxIndex];
            }
        }
        return result;
    }

    public boolean needReverse(int[] nums) {
        int incCount = 0;
        int decCount = 0;

        for (int i = 0; i < nums.length; i ++) {
            if (i > 0 && nums[i] >= nums[i - 1]) incCount ++;

            if (i < nums.length - 1 && nums[i ] >= nums[i + 1]) decCount ++;
        }
        return decCount > incCount;
    }

    public int argMax(int[] nums, int start, int end, boolean eq) {
        int mx = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start; i <= end; i ++) {
            if (eq && nums[i] >= mx || nums[i] > mx) {
                mx = nums[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Test239 t = new Test239();
        int[] nums = {5, 3, 2, 1, 7, 8, 9, 10, 5, 4, 3, 5, 3, 6};
        int k = 4;

        nums = new int[] {19, 22, 37, 66, 28, 45, 98, 33, 21, 65, 43, 6, 6, 6, 3, 1, 4, 10, 8, 7, 6, 8, 7, 6, 8, 5, 4, 3, 2, 1};
        // 66 66 66 98 98 98 98 65 65 65 43 6 6 6 10 10 10 10 8 8 8 8 8 8 8 5 4
        // 66 66 66 98 98 98 98 65 65 65 43 6 6 6 10 10 10 10 8 8 8 8 8 8 8 5 4

        nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19, 22, 37, 66, 28, 45, 98, 33, 21, 65, 43, 6, 6, 6, 3, 1, 4, 10, 8, 7, 6, 8, 7, 6, 8, 5, 4, 3, 2, 1};
//        nums = new int[] {6, 5, 4, 3, 2, 1};
        int[] result = t.maxSlidingWindow(nums, nums.length - 1);
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i]);
            System.out.print(" ");
        }
        System.out.println();
    }
}

