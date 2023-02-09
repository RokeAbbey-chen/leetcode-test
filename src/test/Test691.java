package test;

import java.util.Arrays;

public class Test691 {

    public int minStickers(String[] stickers, String target) {
        int NA = 26;
        int N = 1000 + 1;
        int len = stickers.length;
        int[][] chsDict = new int[len][NA];
        for (int i = 0; i < len; i ++) {
            int size = stickers[i].length();
            for (int j = 0; j < size; j ++)
                chsDict[i][(stickers[i].charAt(j) - 'a')] ++;
        }

        int[] tDict = new int[NA];
        int size = target.length();
        for (int i = 0; i < size; i ++) {
            tDict[target.charAt(i) - 'a'] ++;
        }


        boolean[][] dp = new boolean[NA][N];
        int[][] counts = new int[NA][N];
        int[][] parents = new int[NA][N];
        int[][] owner = new int[NA][N];
        int[] leftBound = new int[NA];
        final int INF = 0x7FFF;
        Arrays.fill(leftBound, INF);
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < NA; j ++) {
                for (int k = N - 1; ; k --) {
                    int l = k - chsDict[i][j];
                    dp[j][l] = dp[j][k];
                    if (dp[j][l]) {
                        if (counts[j][l] == 0 || counts[j][l] > counts[j][k] + 1) {
                            parents[j][l] = k;
                            owner[j][l] = i;
                            leftBound[j] = Math.min(leftBound[j], l);
                        }
                    }
                    if (l <= N - 1 - tDict[j]) {
                        break;
                    }
                }
            }
        }
        return 1;

    }
}
