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
        int res = 0;
        int tmpRes = 0;
        for (int j = 0; j < matrix[0].length; j ++) {
            heights[0][j] = '1' == matrix[0][j] ? 1 : 0;
            tmpRes = heights[0][j] == 1 ? heights[0][j] + tmpRes : 0;
            res = Math.max(res, tmpRes);
        }

        for (int i = 1; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i & 1].length; j ++) {
                if ('1' == matrix[i][j])
                    heights[i & 1][j] = heights[(i - 1) & 1][j] + 1;
                else heights[i & 1][j] = 0;
            }

            res = Math.max(res, getMaxArea(heights[i & 1]));
        }
        return res;
    }

    public int getMaxArea(int[] heights) {
        int[] stack = new int[heights.length];
        int size = 0;
        int res = 0;
        for (int i = 0; i < heights.length; i ++) {
            while (size > 0 && heights[stack[size - 1]] >= heights[i]) {
                size --;
            }
            if (0 == size) {
                res = Math.max(res, (i + 1) * heights[i]);
            } else {
                res = Math.max(res, (i - stack[size - 1]) * heights[i]);
            }
            stack[size ++ ] = i;

            for (int size2 = size; size2 > 0; size2 --) {
                if (size2 - 2 >= 0) {
                    res = Math.max(res, (i - stack[size2 - 2]) * heights[stack[size2 - 1]]);
                } else {
                    res = Math.max(res, (i + 1) * heights[stack[size2 - 1]]);
                }
            }
        }
        return res;
    }

    public static void main1(String[] args) {
        Test85_2 test = new Test85_2();
//        int[] heights = {3, 2, 1, 2, 3, 7, 8, 5, 3, 2, 8, 3, 8, 2, 9};
        int[] heights = {3, 1, 2, 9, 4, 2, 1, 2, 5, 7, 7 , 12, 9, 97, 88, 54, 32, 12};
//        int[] heights = {3, 2, 1, 2, 3, 7};
        int res = test.getMaxArea(heights);

        System.out.println("res = " + res);
    }

    public static void main2(String[] args) {
        Test85_2 test = new Test85_2();
//        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        char[][] matrix = {{'1'}};
        char[][] matrix = {{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}};
        int res = test.maximalRectangle(matrix);
        System.out.println("res = " + res);
    }

    public static void main(String[] args) {
        main1(args);
    }

}
