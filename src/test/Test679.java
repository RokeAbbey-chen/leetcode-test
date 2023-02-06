package test;

import java.util.Arrays;

public class Test679 {

    private static final int[][] A4 = product(4);

    public boolean judgePoint24(int[] cards) {

        for (int i = 0; i < A4.length; i++) {
            double[] result = calc(A4[i], cards, 0, 3);
            for (int j = 0; j < result.length; j++) {
                if (2400 ==  Math.round(result[j] * 100)) {
                    return true;
                }
            }
        }
        return false;
    }

    double[] empty = new double[0];

    public double[] calc(int[] indexes, int[] cards, int start, int end) {
        if (start == end)
            return new double[]{cards[indexes[start]]};
//        if (start > end)
//            return empty;

        double[] its0 = calc(indexes, cards, start + 1, end);

        double[] its1 = empty;
        if (start + 1 <= end - 1)
            its1 = calc(indexes, cards, start + 1, end - 1);

        double[] its2 = calc(indexes, cards, start, end - 1);

        double[] its3 = empty;
        double[] its4 = empty;
        if (0 == start && 3 == end) {
            its3 = calc(indexes, cards, 0, 1);
            its4 = calc(indexes, cards, 2, 3);
        }
        int len = its0.length * 4
                + its1.length * 16
                + its2.length * 4
                + (its3.length * its4.length * 4);
        double[] result = new double[len];


        int head = 0;
        for (int j = 0; j < its0.length; j++) {
            result[head++] = cards[indexes[start]] + its0[j];
            result[head++] = cards[indexes[start]] - its0[j];
            result[head++] = cards[indexes[start]] * its0[j];
            result[head++] = cards[indexes[start]] / its0[j];
        }

        for (int j = 0; j < its1.length; j++) {
            result[head++] = cards[indexes[start]] + its1[j] + cards[indexes[end]];
            result[head++] = cards[indexes[start]] + its1[j] - cards[indexes[end]];
            result[head++] = cards[indexes[start]] + its1[j] * cards[indexes[end]];
            result[head++] = cards[indexes[start]] + its1[j] / cards[indexes[end]];

            result[head++] = cards[indexes[start]] - its1[j] + cards[indexes[end]];
            result[head++] = cards[indexes[start]] - its1[j] - cards[indexes[end]];
            result[head++] = cards[indexes[start]] - its1[j] * cards[indexes[end]];
            result[head++] = cards[indexes[start]] - its1[j] / cards[indexes[end]];

            result[head++] = cards[indexes[start]] * its1[j] + cards[indexes[end]];
            result[head++] = cards[indexes[start]] * its1[j] - cards[indexes[end]];
            result[head++] = cards[indexes[start]] * its1[j] * cards[indexes[end]];
            result[head++] = cards[indexes[start]] * its1[j] / cards[indexes[end]];

            result[head++] = cards[indexes[start]] / its1[j] + cards[indexes[end]];
            result[head++] = cards[indexes[start]] / its1[j] - cards[indexes[end]];
            result[head++] = cards[indexes[start]] / its1[j] * cards[indexes[end]];
            result[head++] = cards[indexes[start]] / its1[j] / cards[indexes[end]];
        }

        for (int j = 0; j < its2.length; j++) {

            result[head++] = its2[j] + cards[indexes[end]];
            result[head++] = its2[j] - cards[indexes[end]];
            result[head++] = its2[j] * cards[indexes[end]];
            result[head++] = its2[j] / cards[indexes[end]];
        }

        for (int i = 0; i < its3.length; i++) {
            for (int j = 0; j < its4.length; j++) {
                result[head++] = its3[i] + its4[j];
                result[head++] = its3[i] - its4[j];
                result[head++] = its3[i] * its4[j];
                result[head++] = its3[i] / its4[j];
            }
        }
        return result;
    }

    public static int[][] product(int n) {
        int len = 1;
        for (int i = 1; i <= n; i++) {
            len *= i;
        }
        int[][] indexes = new int[len][n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < len; i++) {
            Arrays.fill(indexes[i], -1);
        }
        product2(n, indexes, used, 0, 0);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < n; j++) {
                if (indexes[i][j] != -1) continue;
                indexes[i][j] = indexes[i - 1][j];
            }
        }
        return indexes;
    }

    private static int product2(int n, int[][] indexes, boolean[] used, int row, int col) {
        if (col >= n) return 1;
        int oldRow = row;
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            indexes[row][col] = i;
            int dr = product2(n, indexes, used, row, col + 1);
            used[i] = false;
            row += dr;
        }
        return row - oldRow;
    }

    public static void main0(String[] args) {
        int[][] indexes = product(4);
        for (int i = 0; i < indexes.length; i++) {
            System.out.print("[");
            for (int j = 0; j < indexes[0].length; j++) {
                System.out.print(indexes[i][j] + ", ");
            }
            System.out.println("],");
        }
    }

    public static void main(String[] args) {
        Test679 test = new Test679();
//        int[] cards = {1, 1, 8, 1};
//        int[] cards = {1, 5, 9, 1};
//        int[] cards = {1, 9, 1, 2};
        int[] cards = {3, 4, 6, 7};

        boolean result = test.judgePoint24(cards);
        System.out.println(result);
    }
}
