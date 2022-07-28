package test;

public class Test546_2 {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][5];
        for (int i = 0; i < n; i ++) {
            dp[i][i][0] = 1;
            dp[i][i][1] = 1;
            for (int j = i - 1; j >= 0; j --) {
                solve(boxes, j, i, dp);
                System.out.println("dp[j][i][0] = " + dp[j][i][0] + ", j = " + j + ", i = " + i);
                System.out.println("------------------------------");
            }
        }
        return dp[0][n - 1][0];
    }

    public void solve(int[] boxes, int start, int end, int[][][] dp) {

        dp[start][end][0] = dp[start][end - 1][0] + 1;
        dp[start][end][1] = 1;
        for (int i = end - 1; i >= start; i --) {

            int newScore;
            if (boxes[end] == boxes[i]) {
                int score0 = dp[start][i][0];
                int count0 = dp[start][i][1];
                int score1 = dp[i + 1][end - 1][0];
                newScore = score0 + score1 - count0 * count0 + (count0 + 1) * (count0 + 1);
                System.out.println("count0 = " + count0 + ", count0 + 1 = " + (count0 + 1));
                System.out.println("start = " + start + ", i = " + i + ", end = " + end + ", oldScore = " + dp[start][end][0] + ", newScore = " + newScore + ", score0 = " + score0 + ", score1 = " + score1);
                if (newScore >= dp[start][end][0]) {
                    dp[start][end][0] = newScore;
                    dp[start][end][1] = count0 + 1;
                }
            }

        }

    }

    public static void main(String[] args) {
        Test546_2 t = new Test546_2();
//        int[] boxes = {1, 3, 2, 2, 3, 2, 4, 5, 5, 4, 4, 4, 3, 2, 1, 1};
//        int[] boxes = {1, 3, 2, 2, 3, 2};
//        int[] boxes = {1, 2};
//        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
        int[] boxes = {1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2 ,1,2,1,2, 2 ,2};//,2,2};//,1,2,1,2,2,1,1,1,2,2};//,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1};
//        int[] boxes = {1, 1, 1, 1, 1};
//        int[] boxes = {1,2,2,1,2,2,2,1};
//        int[] boxes = {1,2,3,4,5,6,7,8,9};
//        int[] boxes = {1,3,2,2,2,3,4,3,1};
//        int[] boxes = {2,5,10,9,4,8,6,9,9,1};
//        int[] boxes = {8,2,2,9,6,4,3,10,2,10,10,1,10,2,9,5,2,9,7,4,10,2,3,8,3,6,10,9,7,10,9,7,5,3,4,1,3,10,2,6,1,1,1,2,5,5,10,8,9,9};
        int result = t.removeBoxes(boxes);
        System.out.println(boxes.length);
        System.out.println("result : " + result);
    }
}
