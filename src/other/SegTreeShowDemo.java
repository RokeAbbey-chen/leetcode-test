package other;

/**
 * 线段树分享的demo
 */
public class SegTreeShowDemo {

    /**
     * 方法1
     */
    private static class Demo1 {
        public int sumRange1(int[] nums, int left, int right) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }
            return sum;
        }
    }

    /**
     * 方法2
     */
    private static class Demo2 {

        private int[] sums;

        Demo2(int[] nums) {
            sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i ++) {
                sums[i + 1] += sums[i] + nums[i];
            }
        }

        public int sumRange2(int left, int right) {
            return sums[right + 1] - sums[left];
        }
    }

    /**
     * 方法3 树状数组
     * 有兴趣可以扩展
     */
    private static class Demo3 {

    }

    /**
     * 方法4 线段树 请看题目307
     */
    private static class Demo4 {

    }

}
