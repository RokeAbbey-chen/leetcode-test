package test;

import java.util.HashSet;

/**
 * Given several boxes with different colors represented by different positive numbers.
 You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 Find the maximum points you can get.

 Example 1:
 Input:

 [1, 3, 2, 2, 2, 3, 4, 3, 1]

 Output:

 23

 Explanation:

 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 ----> [1, 3, 3, 3, 1] (1*1=1 points)
 ----> [1, 1] (3*3=9 points)
 ----> [] (2*2=4 points)

 Note: The number of boxes n would not exceed 100.

 膜拜fun4leetcode大神的题解
 先不做了 等日后再做；
 */
public class Test546 {

    public int removeBoxes(int[] boxes) {

        int n = boxes.length;
        byte[][] colorWhere = new byte[101][n + 1];
        for (int i = 0; i < boxes.length; i ++) {
            byte color = (byte)boxes[i];
            colorWhere[color][colorWhere[color][n]++] = (byte)i;
        }
        boolean[] bUsed = new boolean[101];
        int result = 0;
        for (int i = 0; i < colorWhere.length; i ++) {
            if (!bUsed[i] && allContinuous(colorWhere[i], n)) {
                bUsed[i] = true;
                result += colorWhere[i][n] * colorWhere[i][n];
            }
        }



        return result + remove(boxes, bUsed, colorWhere, (byte)0, (byte)(boxes.length - 1));
    }

    public boolean allContinuous(byte[] cw, int n) {
        for (int i = 1; i < cw[n]; i ++) {
            if (cw[i] - 1 != cw[i - 1])
                return false;
        }
        return true;
    }

    public int[] copyNoUsed(int[] boxes, boolean[] bUsed, byte start0, byte end0, byte start1, byte end1) {
        int len = 0;
        for (int i = start0; i <= end0; i ++) {
            if (!bUsed[boxes[i]]) len ++;
        }

        for (int i = start1; i <= end1; i ++) {
            if (!bUsed[boxes[i]]) len ++;
        }

        int[] boxes2 = new int[len];
        int j = 0;
        for (int i = start0; i <= end0; i ++) {
            if (!bUsed[boxes[i]]) boxes2[j++] = boxes[i];
        }

        for (int i = start1; i <= end1; i ++) {
            if (!bUsed[boxes[i]]) boxes2[j++] = boxes[i];
        }

        return boxes2;

    }
    public int remove(int[] boxes, boolean[] bUsed, byte[][] colorWhere, byte start, byte end) {
//        System.out.println("start = " + start + ", end = " + end);
        if (end < start) return 0;
        if (end == start) {
            if (start < boxes.length && !bUsed[boxes[start]]) {
                return 1;
            }
            return 0;
        }
//        if (end == start && start < boxes.length && !bUsed[boxes[start]]) return 1;
        int maxSum = 0, n = boxes.length;

        for (byte i = start; i <= end; i ++) {
            byte color = (byte)boxes[i];
            if (bUsed[color]) continue;
            bUsed[color] = true;
            int sum = 0;
            byte j;
            for (j = 0; j < colorWhere[color][n]&& colorWhere[color][j] < start; j ++);

            byte j0 = (byte)(start - 1);
            int count = j, j3 = j;
            byte j1 = end;
            for (;j < colorWhere[color][n] && colorWhere[color][j] <= end; j ++) {
                j1 = colorWhere[color][j];
                if (count != j)
                    sum += remove(boxes, bUsed, colorWhere, (byte)(j0 + 1), (byte) (j1 - 1));
                j0 = j1;
            }
            if (colorWhere[color][n] > 0) {
                int[] boxes2 = copyNoUsed(boxes, bUsed, start, (byte)(colorWhere[color][j3] - 1), (byte)(j1 + 1), end);
//                System.out.println("boxes2.len = " + boxes2.length);
                if (boxes2.length >= 1) {
                    sum += removeBoxes(boxes2);
                }
            }
//            sum += remove(boxes, bUsed, colorWhere, (byte)(j1 + 1), end);

            count = j - count;
            sum += count * count;
            maxSum = Math.max(maxSum, sum);
            bUsed[color] = false;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Test546 t = new Test546();
//        int[] boxes = {1, 3, 2, 2, 3, 2, 4, 5, 5, 4, 4, 4, 3, 2, 1, 1};
//        int[] boxes = {1, 3, 2, 2, 3, 2};
//        int[] boxes = {1, 2};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1,2,3,4,5,6,7,8,9};
//        int[] boxes = {1,3,2,2,2,3,4,3,1};
//        int[] boxes = {2,5,10,9,4,8,6,9,9,1};
        int[] boxes = {8,2,2,9,6,4,3,10,2,10,10,1,10,2,9,5,2,9,7,4,10,2,3,8,3,6,10,9,7,10,9,7,5,3,4,1,3,10,2,6,1,1,1,2,5,5,10,8,9,9};
        int result = t.removeBoxes(boxes);
        System.out.println("result : " + result);
    }
}
