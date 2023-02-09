package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test689_2 {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] subSums = new int[len - k + 1];

        for (int i = 0; i < k; i ++) {
            subSums[0] += nums[i];
        }
        for (int i = 1; i < len - k + 1; i ++) {
            subSums[i] = subSums[i - 1] + nums[i + k - 1] - nums[i - 1];
        }

        int len2 = subSums.length;
        int[] leftMaxIndexes = new int[len2];
        int[] rightMaxIndexes = new int[len2];
        leftMaxIndexes[0] = 0;
        rightMaxIndexes[len2 - 1] = len2 - 1;

        for (int i = 1; i < len2; i ++) {
//            System.out.println("i = " + i);
            leftMaxIndexes[i] = (subSums[i] > subSums[leftMaxIndexes[i - 1]]) ? i: leftMaxIndexes[i - 1];
            rightMaxIndexes[len2 - i - 1] = subSums[len2 - i - 1] >= subSums[rightMaxIndexes[len2 - i]] ? len2 - i - 1: rightMaxIndexes[len2 - i];
        }

        int maxV = -1;
        int[] maxIndex = {-1, -1, -1};
        for (int i = k; i + k < subSums.length; i ++) {
            int i0 = leftMaxIndexes[i - k], i1 = rightMaxIndexes[i + k];
            int s = subSums[i0] + subSums[i] + subSums[i1];
            if (s > maxV) {
                maxV = s;
                maxIndex[0] = i0;
                maxIndex[1] = i;
                maxIndex[2] = i1;
            }
        }
        return maxIndex;
    }

    static int[] readFromFile(int len){
        String path = "/media/roke/HDD1/mywork/workspace/leetcode/src/other/data0.txt";
        int N = 2048;
        char[] chs = new char[N];
        int[] nums = new int[len];
        int index = 0;

        try {
            FileReader in = new FileReader(path);
            int c;
            boolean flag = false;
            int num = 0;
            while ((c = in.read(chs)) > 0) {
                flag = true;
                for (int i = 0; i < c; i ++) {
                    if (',' == chs[i]) {
                        nums[index ++] = num;
                        num = 0;
                        continue;
                    }
                    num *= 10;
                    num += (chs[i] - '0');
                }
            }
            if (flag)
                nums[index++] = num;
            System.out.println("read len " + index);
            return nums;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Test689_2 test = new Test689_2();
//        int[] nums = {7, 8, 1, 2, 8, 9, 9, 2, 1, 13, 3, 4};
//        int k = 3;

//        int[] nums = {7, 8, 1, 2, 8};
//        int k = 1;

//        int[] nums = {7, 8, 1, 2, 8, 9, 9, 2, 1, 13, 3, 4};
//        int k = 4;

//        int[] nums =
//                {17, 9, 3, 2, 7, 10, 20, 1, 13, 4, 5, 16, 4, 1, 17, 6, 4, 19, 8, 3};
////        // 3 8 14 : 39 38
//        int k = 4;

        int k = 2859;
//        System.out.println("length: " + nums.length);
//        int N = 940240;
//        int[] nums = new int[N];
//        Random rd = new Random();
//        for (int i = 0; i < N; i ++) {
//            nums[i] = rd.nextInt(N * 10);
//        }

        int[] nums = readFromFile(20000); // exp:[763,7486,16045], actual:763, 14263, 17131
        long start = System.currentTimeMillis();
        int[] result = test.maxSumOfThreeSubarrays(nums, k);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + ", result:" + result[0] + ", " + result[1] + ", " + result[2]);
    }
}
