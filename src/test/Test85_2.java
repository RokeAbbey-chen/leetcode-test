package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test85_2 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int[][] heights = new int[2][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j ++) {
            heights[0][j] = matrix[0][j];
        }

        int res = 0;
        for (int i = 1; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i & 1].length; j ++) {
                if ('1' == matrix[i & 1][j])
                    heights[i & 1][j] = heights[(i - 1) & 1][j] + 1;
                else heights[i & 1][j] = 0;
            }
//            res = Math.max(res, largestRectangleArea(heights[i & 1]));
        }
        return res;
    }

    public int getMaxArea(int[] heights) {
        int[] stack = new int[heights.length];
        int ids = 0;
        int res = 0;
        for (int i = 0; i < heights.length; i ++) {
            while (ids > 0 && heights[stack[ids - 1]] > heights[i]) {
                res = Math.max(res, (i - stack[ids - 1] + 1) * heights[i]);
                ids --;
            }
            stack[ids ++ ] = i;

        }
        return res;
    }

}
