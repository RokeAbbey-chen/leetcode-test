package test;

import java.util.Arrays;

public class Test480_2 {
    static class Window {

        final int[] arr;
        final int size;
        final int medianIndex;
        final boolean hasMedian;

        Window(int[] array) {
            this.arr = array;
            this.size = array.length;
            this.hasMedian = size % 2 == 1;
            this.medianIndex = (size - 1) / 2;
            Arrays.sort(this.arr);
        }

        double replace(int from, int to) {
            int index = Arrays.binarySearch(arr, from);
            if (index != size - 1) {
                System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
            }
            arr[size - 1] = Integer.MAX_VALUE;
            index = Arrays.binarySearch(arr, to);
            index = index >= 0 ? index : -index - 1;
            System.arraycopy(arr, index, arr, index + 1, size - 1 - index);
            arr[index] = to;
            return median();
        }

        double median() {
            if (hasMedian) {
                return arr[medianIndex];
            } else {
                return ((double) arr[medianIndex] + (double) arr[medianIndex + 1]) / 2;
            }
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int resultPtr = 0;

        int[] windowArr = new int[k];
        System.arraycopy(nums, 0, windowArr, 0, k);
        Window window = new Window(windowArr);
        result[resultPtr++] = window.median();
        for (int i = k; i < nums.length; i++) {
            result[resultPtr++] = window.replace(nums[i - k], nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Test480_2 t = new Test480_2();
        int[] nums = new int[(int)1e6];
        for (int i = 0; i < nums.length; i ++) {
            nums[i] = nums.length - i;
        }
        int k = nums.length >> 1;
        long start = System.currentTimeMillis();
        t.medianSlidingWindow(nums, k);
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
    }
}
