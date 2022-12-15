package test;

public class Test629 {

    public int kInversePairs(int n, int k) {
        int[][] map = new int[n + 1][k + 1];
        return help(n, k, map);
    }


    int help(int n, int k, int[][] map) {
//        System.out.println("n = " + n + ", k = " + k);
        if (n <= 0 || k < 0) return 0;
        if (0 == k) return 1;
        if (1 == k) return n - 1;
        if (map[n][k] > 0) return map[n][k];
        long sum = 0;
        for (int i = 1; i <= k; i ++) {
            for (int j = 0; j < n; j ++) {
//                System.out.println("i = " + i + ", j = " + j + ", j - i = " + (j - i) + ", j + i = " + (j + i) + ", f0 = " + (n - j + i - 1) + ", f1 = " + (n - j - i - 1) + ", k - i = " + (k - i));
                int g = 0, g0 = 0, g1 = 0;
                if (j - i >= 0) {
                    g += help(n - j + i - 1, k - i, map);
                    sum += g;
//                System.out.println("g0 = " + g0 + ", g1 = " + g1 + ", g = " + g + ", sum = " + sum);
//                System.out.println("-");
                }
            }

        }
//        System.out.println("sum = " + sum + " ----");
        map[n][k] = (int)sum;
        return (int)sum;
    }

    public static void main(String[] args) {
        Test629 test = new Test629();
//        int result = test.kInversePairs(1000, 400);
        int result = test.kInversePairs(100, 4);
        System.out.println("result = " + result);
    }
}
