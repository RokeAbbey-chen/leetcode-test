package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Test329_2 {
 /*更慢*/
    public int longestIncreasingPath(int[][] matrix) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[][] pathLen = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row ++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(isLocalMin(matrix, row, col)) queue.add(row * 1000 + col);
            }
        }
        int[][] neighborOffset = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxLen = 0;
        while (!queue.isEmpty()) {
            Integer rc = queue.removeFirst();
            int row = rc / 1000, col = rc % 1000;
            int pLen = pathLen[row][col];

            for (int i = 0; i < neighborOffset.length; i ++) {
                int neiRow = row + neighborOffset[i][0], neiCol = col + neighborOffset[i][1];
                if (neiRow >= 0 && neiRow < matrix.length && neiCol >= 0 && neiCol < matrix[row].length
                        && matrix[neiRow][neiCol] > matrix[row][col]
                        && pathLen[neiRow][neiCol] < pLen + 1) {
                    pathLen[neiRow][neiCol] = pLen + 1;
                    maxLen = Math.max(maxLen, pLen + 1);
                    queue.add(neiRow * 1000 + neiCol);
                }
            }
        }
        return maxLen + 1;
    }

    public boolean isLocalMin(int[][] matrix, int row, int col) {
        int num = matrix[row][col];
        int[] neighbors = new int[]{-1, -1, -1, -1};
        if (row - 1 >= 0) neighbors[0] = matrix[row - 1][col];
        if (row + 1 < matrix.length) neighbors[1] = matrix[row + 1][col];
        if (col - 1 >= 0) neighbors[2] = matrix[row][col - 1];
        if (col + 1 < matrix[row].length) neighbors[3] = matrix[row][col + 1];
        boolean anyBigger = false;
        for (int i = 0; i < neighbors.length; i ++) {
            if (neighbors[i] > num) anyBigger = true;
            if (neighbors[i] >= 0 && neighbors[i] < num) return false;
        }
        return anyBigger;
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                System.out.printf("%4d(%2d,%2d), ", matrix[i][j], i, j);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Test329 t0 = new Test329();
        Test329_2 t1 = new Test329_2();

        for (int k = 0; k < 10; k ++) {
            Random random = new Random(k);
            int[][] matrix = new int[1000][1000];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = random.nextInt(2000);
                }
            }

//        System.out.println("matrix:");
//        printMatrix(matrix);
            long start0 = System.currentTimeMillis();
            int result0 = t0.longestIncreasingPath(matrix);
            long end0 = System.currentTimeMillis();
            int result1 = t1.longestIncreasingPath(matrix);
            long end1 = System.currentTimeMillis();
            System.out.println("result0:" + result0 + ", result1:" + result1);
            System.out.println("use time0: " + (end0 - start0) + ", use time1:" + (end1 - end0));
        }
    }

}
