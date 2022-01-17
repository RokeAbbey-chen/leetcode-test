package test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TreeSet;

/*
* Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
*
* Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
* */
public class Test363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length < matrix[0].length) {
            int[][] m2 = new int[matrix[0].length][matrix.length];
            for (int i = 0; i < m2.length; i ++) {
                for (int j = 0; j < m2[0].length; j ++) {
                    m2[i][j] = matrix[j][i];
                }
            }
            return maxSumSubmatrix0(m2, k);
        }
        return maxSumSubmatrix0(matrix, k);
    }

    public int maxSumSubmatrix0(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                int v0 = i - 1 >= 0 ? sum[i - 1][j] : 0;
                int v1 = j - 1 >= 0 ? sum[i][j - 1] : 0;
                int v2 = i - 1 >= 0 && j - 1 >= 0 ? sum[i - 1][j - 1]: 0;
                sum[i][j] = matrix[i][j] + v0 + v1 - v2;
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i0 = 0; i0 < n; i0 ++) {
            for (int i1 = i0; i1 < n; i1 ++) {
                TreeSet<Integer> tree = new TreeSet<>();
                for (int j = 0; j < m; j ++) {
                    Integer v0 = i0 - 1 >= 0 ? sum[j][i1] - sum[j][i0 - 1] : sum[j][i1];
                    if (v0 == k) return k;
                    if (v0 < k) result = Math.max(v0, result);
                    Integer ceil;
                    ceil = tree.ceiling(v0 - k);
                    if (null != ceil) {
                        result = Math.max(v0 - ceil, result);
                    }
                    tree.add(v0);
                }

            }
        }
        return result;
    }

    public static void main1(String[] args) {
        Test363 t = new Test363();
        int[][] matrix = new int[][] {
                {1, 2, 3, 4, -1, -4, 1},
                {11, 2, 3, 4, -1, 4, -2},
                {8, 2, -3, 4, -12, -7, -2},
                {1, 12, 3, 4, -8, -4, 2},
                {2, 2, 3, 4, -1, 4, -2},
        };

        int[][] matrix2 = new int[][] {
                {1, 0, 1},
                {0, -2, 3}
        };
        int[][] matrix3 = new int[][] {
                {2, 2, -1}
        };
        int[][] matrix4 = new int[][] {
                { 5,-4,-3, 4},
                {-3,-4, 4, 5},
                { 5, 1, 5,-4}};

        int k = 8;
        int result = t.maxSumSubmatrix(matrix4, k);
        System.out.println("result : " + result);
    }

    public static void main2(String[] args) {
        TreeSet<Integer> a = new TreeSet<>();
        Integer num = new Integer(1);
        a.add(num);
        a.add(new Integer(1));
        System.out.println(a.ceiling(num) == num);

    }

    public static void main(String[] args) {
        main1(args);
    }


}
