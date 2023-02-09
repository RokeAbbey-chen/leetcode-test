package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Test689 {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[][] subSums = new int[len - k + 1][6];

        for (int i = 0; i < k; i ++) {
            subSums[0][0] += nums[i];
        }
        subSums[0][1] = 0;
        for (int i = 1; i < len - k + 1; i ++) {
            subSums[i][0] = subSums[i - 1][0] + nums[i + k - 1] - nums[i - 1];
            subSums[i][1] = i;
            subSums[i][2] = subSums[i][0];
        }

        Arrays.sort(subSums, (o0, o1)-> {
            if (o0[0] < o1[0]) return 1;
            if (o0[0] > o1[0]) return -1;
            if (o0[1] < o1[1]) return -1;
            if (o0[1] > o1[1]) return 1;
            return 0;
        });

        for (int i = 0; i < Math.min(3 * (k + 1), len); i ++) {
            for (int j = 0; j < Math.min(i, 2 * (k + 1)); j ++) {
                int indexI = subSums[i][1];
                int indexJ = subSums[j][1];
                if (check(i, j, k, subSums)) continue;
                int partnerCount = subSums[j][5];
                if (partnerCount > 0) {
                    int t = subSums[j][3];
                    int indexT = subSums[t][1];
                    if (!check(i, t, k, subSums))
                        return wrapReturn(indexT, indexJ, indexI);
                    t = find(j - 1, k, i, j, subSums);
                    if (t >= 0) {
                        indexT = subSums[t][1];
                        return wrapReturn(indexT, indexJ, indexI);
                    }
                }
                if (subSums[j][0] + subSums[i][0] > subSums[i][2]) {
                    subSums[i][3] = j;
                    subSums[i][5] = 1;
                } else if (subSums[j][0] + subSums[i][0] == subSums[i][2]
                        && subSums[i][5] >= 1
                        && subSums[subSums[i][3]][1] > indexJ) {
                    subSums[i][3] = j;
                    subSums[i][5] = 1;
                }

            }

        }
        return null;

    }

    private int find(int end, int k, int i, int j, int[][] subSums) {
        for (int t = 0; t <= end; t ++) {
            if (!check(t, i, k, subSums) && !check(t, j, k, subSums)) {
                return t;
            }
        }
        return -1;
    }

    private boolean check(int i, int j, int k, int[][] subSums) {
        int indexI = subSums[i][1];
        int indexJ = subSums[j][1];
        if (!(indexJ + k <= indexI || indexI + k <= indexJ))
            return true;
        return false;
    }

    private int[] wrapReturn(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        return arr;
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
        Test689 test = new Test689();
//        int[] nums = {7, 8, 1, 2, 8, 9, 9, 2, 1, 13, 3, 4};
//        int k = 3;

//        int[] nums = {7, 8, 1, 2, 8};
//        int k = 1;

//        int[] nums = {7, 8, 1, 2, 8, 9, 9, 2, 1, 13, 3, 4};
//        int k = 4;

//        int[] nums =
//                {17,9,3,2,7,10,20,1,13,4,5,16,4,1,17,6,4,19,8,3};
//        // 3 8 14 : 39 38
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
