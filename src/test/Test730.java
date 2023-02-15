package test;

public class Test730 {
    public int countPalindromicSubsequences(String s) {
        final int len = s.length();
        char[] chs = s.toCharArray();
        int[][] mem = new int[len][len];
        return func0(chs, 0, len -1, mem);

    }

    private static int func0(char[] chs, int start, int end, int[][] mem) {
        if (start > end) return 0;
        if (start == end) return 1;
        if (mem[start][end] > 0) return mem[start][end];
        final int MOD = (int)(1e9 + 7);
        boolean[] flags = {false, false, false, false};
        int sum = 0;
        for (int i = start; i <= end; i ++) {
            char ci = chs[i];
            if (flags[ci - 'a']) continue;
            int j = find(chs, ci, end, i);
            sum += (func0(chs, i + 1, j - 1, mem) + (i == j ? 1: 2)) % MOD;
            sum %= MOD;
            flags[ci - 'a'] = true;
        }
        mem[start][end] = sum;
        return sum;
    }

    private static int find(char[] chs, char c, int start, int end) {
        for (int i = start; i >= end; i --) {
            if (chs[i] == c)
                return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        Test730 t = new Test730();
        String s = "abccdcadadacddabcabbbcccabcabdddcabbcacabbabcbd";
//        String s = "abccdcada";
//        String s = "ccdca";
//        String s = "ccdc";
//        String s = "ccd";
        int result = t.countPalindromicSubsequences(s);
        System.out.println("result:" + result);
    }
}
