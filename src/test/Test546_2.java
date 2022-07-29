package test;

import java.util.*;

public class Test546_2 {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        byte[][] colorWhere = getColorWhere(boxes);
        int[][][] dp = new int[n][n][5];
        for (int i = 0; i < n; i ++) {
            dp[i][i][0] = 1;
            dp[i][i][1] = 1;
            for (int j = i - 1; j >= 0; j --) {
                solve(boxes, j, i, dp, colorWhere);
//                System.out.println("dp[j][i][0] = " + dp[j][i][0] + ", j = " + j + ", i = " + i);
//                System.out.println("------------------------------");
            }
        }
        return dp[0][n - 1][0];
    }

    public byte[][] getColorWhere(int[] boxes) {
        byte maxColor = 0;
        byte[] colorCounts = new byte[101];
        for (int i = 0; i < boxes.length; i ++) {
            colorCounts[boxes[i]] ++;
            maxColor = (byte)Math.max(boxes[i], maxColor);
        }
        byte[][] colorWhere = new byte[maxColor + 1][];
        for (int i = 0; i <= maxColor; i ++) {
            int len = colorCounts[i];
            colorWhere[i] = new byte[len + 1];
        }
        for (byte i = 0; i < boxes.length; i ++) {
            int n = colorWhere[boxes[i]].length - 1;
            colorWhere[boxes[i]][colorWhere[boxes[i]][n] ++] = i;
        }
        return colorWhere;
    }

    public byte calcColorCount(byte[] colorWhere, byte start, byte end ) {
        if (colorWhere.length <= 1) return 0;

        int i = Arrays.binarySearch(colorWhere, start);
        if (i < 0) i = -i - 1;
        int j = Arrays.binarySearch(colorWhere, end);
        if (j < 0) j = -j - 2;
        return (byte)(j - i + 1);
    }

    public int calcNewScore(int[][][] dp, byte start, byte end, byte[] colorWhere, int score1) {
        LinkedList<TreeSet<Byte>> queue = new LinkedList<>();
        queue.add(new TreeSet<>());
//        byte colorCount = calcColorCount(colorWhere, start, (byte)(end - 1));
//        int[][] scoreCount = new int[1 << colorCount][];
        int newScore = end >= 1 ? dp[start][end - 1][0] + 4 + score1: 4 + score1;
//        int count = 0;
        long startTime = System.currentTimeMillis();

        while (queue.size() > 0) {
            Set<Byte> set = queue.removeFirst();
//            System.out.println("old set = " + set);
            for (int i = 0; i < colorWhere.length && colorWhere[i] < end; i ++) {
                if (colorWhere[i] < start || set.contains(colorWhere[i])) continue;
                TreeSet<Byte> set2 = new TreeSet<>(set);
                set2.add(colorWhere[i]);
//                System.out.println("new set = " + set2);
                queue.addLast(set2);
//                if (set2.size() >= 9) System.exit(0);
                if (System.currentTimeMillis() - startTime >= 2000) System.exit(0);

                byte last = start;
                int score = 0;
                for (byte cur: set2) {
                    score += cur >= 1 ? dp[last][cur - 1][0]: 0;
                    last = (byte)(cur + 1);
                }
                score += dp[last][end - 1][0];
                score += (set2.size() + 2) * (set2.size() + 2) + score1;
                if (score > newScore)
                    newScore = score;
//                scoreCount[count] = new int[2]{};
            }
//            System.out.println("--");
        }
        return newScore;



    }

    public void solve(int[] boxes, int start, int end, int[][][] dp, byte[][] colorWhere) {

        dp[start][end][0] = dp[start][end - 1][0] + 1;
        dp[start][end][1] = 1;
        for (int i = end - 1; i >= start; i --) {

//            int newScore;
            if (boxes[end] == boxes[i]) {
//                System.out.println("start = " + start + ", i = " + i + ", end = " + end + ", oldScore = " + dp[start][end][0]);
                int newScore = calcNewScore(dp, (byte)start, (byte)i, colorWhere[boxes[i]], dp[i + 1][end - 1][0]);
//                System.out.println("newScore = " + newScore);
//                System.out.println("count0 = " + count0 + ", count0 + 1 = " + (count0 + 1));
//                System.out.println("start = " + start + ", i = " + i + ", end = " + end + ", oldScore = " + dp[start][end][0] + ", newScore = " + newScore + ", score0 = " + score0 + ", score1 = " + score1);
                if (newScore >= dp[start][end][0]) {
                    dp[start][end][0] = newScore;
                }
            }

        }

    }

    static class Key {

    }

    public static void main(String[] args) {
        Test546_2 t = new Test546_2();
//        int[] boxes = {1, 3, 2, 2, 3, 2, 4, 5, 5, 4, 4, 4, 3, 2, 1, 1};
//        int[] boxes = {1, 3, 2, 2, 3, 2};
//        int[] boxes = {1, 2};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2 ,1,2,1,2, 2};// ,2};//,2,2};//,1,2,1,2,2,1,1,1,2,2};//,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1, 1, 1, 1, 1};
//        int[] boxes = {1,1, 2,2,1,2,2,2,1};
//        int[] boxes = {1,2,3,4,5,6,7,8,9};
//        int[] boxes = {1,3,2,2,2,3,4,3,1};
//        int[] boxes = {2,5,10,9,4,8,6,9,9,1};
        int[] boxes = {8,2,2,9,6,4,3,10,2,10,10,1,10,2,9,5,2,9,7,4,10,2,3,8,3,6,10,9,7,10,9,7,5,3,4,1,3,10,2,6,1,1,1,2,5,5,10,8,9,9};
        long start = System.currentTimeMillis();
        int result = t.removeBoxes(boxes);
        long end = System.currentTimeMillis();
        System.out.println("use time = " + (end - start));
        System.out.println(boxes.length);
        System.out.println("result : " + result);
    }
}
