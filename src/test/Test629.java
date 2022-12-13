package test;

public class Test629 {

    public int kInversePairs(int n, int k) {

    }


    int help(int n, int k, int[][] map) {
        if (n <= 0) return 0;
        if (0 == k) return 1;

        if (map[n][k] > 0) return map[n][k];
        int sum = 0;
        for (int i = 1; i < k; i ++) {
            if (k - i >= 0) {
                sum += help(n - 1, k - i, map);
            }

        }
        map[n][k] = sum;
        return sum;
    }
}
