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

    }

    public int remove(int[] boxes, boolean[] bUsed, byte[][] colorWhere, byte start, byte end) {
        if (end < start) return 0;
        if (end == start) return 1;
        int maxSum = 0;
        for (byte i = start; i <= end; i ++) {
            byte color = (byte)boxes[i];
            if (bUsed[color]) continue;
            bUsed[color] = true;
            int sum = 0;
            byte j;
            for (j = 0; j < colorWhere[color].length && colorWhere[color][j] < start; j ++);

            byte j0 = (byte)(start - 1);
            int count = j;
            for (;j < colorWhere[color].length && colorWhere[color][j] <= end; j ++) {
                byte j1 = colorWhere[color][j];
                sum += remove(boxes, bUsed, colorWhere, (byte)(j0 + 1), (byte) (j1 - 1));
                j0 = j1;
            }
            count = j - count + 1;
            sum += count * count;
            maxSum = Math.max(maxSum, sum);
            bUsed[color] = false;
        }
        return maxSum;


    }
}
