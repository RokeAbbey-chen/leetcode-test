package test;

public class Test546_5 {

    public int removeBoxes(int[] boxes) {
        final int n = boxes.length;
        int[][][] result = new int[n + 1][n + 1][n + 1];
        // start-end-colorCount include

        int maxColor = -1;
        for (int i = 0; i < n; i ++) {
            maxColor = Math.max(maxColor, boxes[i]);
        }
        int[][] colorCount = new int[maxColor + 1][n + 1];
        // color-end include

        int[] curColorCount = new int[maxColor + 1];

        int maxScores = 0;
        for (int i = 0; i < n; i ++) {
            int color = boxes[i];
            colorCount[color][n] = colorCount[color][i] = ++curColorCount[color];
            for (int j = i - 1; j >= 0 && 0 == colorCount[color][j]; j --) {
                colorCount[color][j] = colorCount[color][i] - 1;
            }

            for (int k = 1; k <= colorCount[color][i]; k ++) {
                maxScores = Math.max(result[i][i][k] = k * k, maxScores);
            }
        }

        for (int i = 1; i < colorCount.length; i ++) {
            if (colorCount[i][n] == 0) continue;
            for (int j = n - 1; j >= 0 && 0 == colorCount[i][j]; j --) {
                colorCount[i][j] = colorCount[i][n];
            }
        }


        for (int i = n - 1; i >= 0; i --) {
            int color = boxes[i];
            for (int j = i + 1; j < n; j ++) {
                for (int p = i + 1; p <= j; p ++) {
                    if (boxes[p] == color) {
                        for (int k = 1; k <= colorCount[color][i]; k++) {
                            result[i][j][k] = Math.max(result[i][j][k], result[i + 1][p - 1][1] + result[p][j][k + 1]);
                            maxScores = Math.max(result[i][j][k], maxScores);
                        }
                    } else {
                        for (int k = 1; k <= colorCount[color][i]; k++) {
                            result[i][j][k] = Math.max(result[i][j][k], k * k + result[i + 1][j][1]);
                            maxScores = Math.max(result[i][j][k], maxScores);
                        }
                    }
                }
            }
        }
        return maxScores;
    }

    public static void main(String[] args) {

        Test546_4 t = new Test546_4();
//        int[] boxes = {1, 3, 2, 2, 3, 2, 4, 5, 5, 4, 4, 4, 3, 2, 1, 1};
//        1 + 1 + 1 +  4 + 16 + 16 + 9
//        int[] boxes = {1, 3, 2, 2, 3, 2};
//        int[] boxes = {1, 2};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2 ,1,2,1,2, 2};// ,2};//,2,2};//,1,2,1,2,2,1,1,1,2,2};//,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1, 1, 1, 1, 1};
//        int[] boxes = {1,1, 2,2,1,2,2,2,1};
//        int[] boxes = {1,2,3,4,5,6,7,8,9};
//        int[] boxes = {1,3,2,2,2,3,4,3,1};
        int[] boxes = {2,5,10,9,4,8,6,9,9,1};
//        int[] boxes = {8,2,2,9,6,4,3,10,2,10,10,1,10,2,9,5,2,9,7,4,10,2,3,8,3,6,10,9,7,10,9,7,5,3,4,1,3,10,2,6,1,1,1,2,5,5,10,8,9,9};
//        int[] boxes = new int[1];
//        for (int i = 1; i <= 1; i ++) {
//            boxes[i - 1] = i;
//        }
        long start = System.currentTimeMillis();
        int result = t.removeBoxes(boxes);
        long end = System.currentTimeMillis();
        System.out.println("use time = " + (end - start));
        System.out.println(boxes.length);
        System.out.println("result : " + result);

    }
}
