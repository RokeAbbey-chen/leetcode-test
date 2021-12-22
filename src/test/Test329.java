package test;

import java.util.HashMap;

public class Test329 {
    public int longestIncreasingPath(int[][] matrix) {
        HashMap<Integer, Integer> mem = new HashMap<>();
        int maxLen = -1;
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[i].length; j ++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, mem));
            }
        }
        return maxLen;
    }

    public int dfs(int[][] matrix, int row, int col, HashMap<Integer, Integer> mem) {
        Integer pathLen = mem.get(row * 1000 + col);
        if (pathLen != null) {
            return pathLen;
        }
        int maxLength = 0;
        if (col >= 1 && matrix[row][col - 1] > matrix[row][col]) {
            pathLen = dfs(matrix, row, col - 1, mem);
            maxLength = Math.max(maxLength, pathLen);
        }
        if (col + 1 < matrix[row].length && matrix[row][col + 1] > matrix[row][col]) {
            pathLen = dfs(matrix, row, col + 1, mem);
            maxLength = Math.max(maxLength, pathLen);
        }

        if (row >= 1 && matrix[row - 1][col] > matrix[row][col]) {
            pathLen = dfs(matrix, row - 1, col, mem);
            maxLength = Math.max(maxLength, pathLen);
        }

        if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            pathLen = dfs(matrix, row + 1, col, mem);
            maxLength = Math.max(maxLength, pathLen);
        }
        mem.put(row * 1000 + col, maxLength + 1);
        return maxLength + 1;
    }
}
