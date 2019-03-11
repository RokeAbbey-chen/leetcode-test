package test;

import java.text.SimpleDateFormat;

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

        if (matrix.length > matrix[0].length ){
            matrix = reverse(matrix);
        }

        int[] colSum = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i ++){
            for (int j = 0; j < matrix[0].length; j ++){
                colSum[j] += matrix[i][j];
            }
        }

        int[] colSum2 = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i ++){
            for (int t = matrix.length - 1; t > i; t --) {
                if (t != 0) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        colSum2[j] += matrix[i][j];
                    }
                }
                int max = colSum[0] - colSum2[0];
                max = max > k ? Integer.MIN_VALUE: max;
                if (max == k){ return max; }
                int temp;
                for (int j = 1; j < matrix[0].length; j ++){
                    temp = colSum[j] - colSum2[j];
                    if (temp < k){
                        if (temp < 0) {
                            max = Math.max(temp, max);
                        }
                        else {
//                            temp
                        }
                    }
                }
            }
        }

        return 0;

    }
    private int[][] reverse(int[][] matrix){
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i ++){
            for (int j = 0; j < result.length; j ++){
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
