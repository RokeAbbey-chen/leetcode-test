package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test828 {
    // 超时
    public int uniqueLetterString(String s) {
        if (null == s || s.length() == 0) { return 0; }
        char[] chs = s.toCharArray();
        int[][] dp = new int[2][chs.length];
        int[][] flags = new int[chs.length][26];
        int sum = 1;
        dp[0][0] = 1;
        flags[0][(int)(chs[0] - 'A')] = 1;
        for (int i = 1; i < chs.length; i++) {
            int charIndex = (chs[i] - 'A');
            dp[i & 1][i] = 1;
            sum ++;
            for (int j = 0; j < 26; j ++) { flags[i][j] = flags[i - 1][j]; }
            flags[i][charIndex]++;
            for (int j = i - 1; j >= 0; j--) {
                // 注意j - 1的下标
                int existNum = flags[i - 1][charIndex];// - flags[j - 1][charIndex];
                if (j - 1 >= 0) { existNum -= flags[j - 1][charIndex]; }
                // System.out.println("i = " + i + ", j = " + j + ", existNnm = " + existNum);
                if (existNum == 1) {
                    // 当chs[i] 在 [j, i - 1] 区间内已经出现了一次的情况下
                    dp[i & 1][j] = dp[(i - 1) & 1][j] - 1;
                } else if (existNum >= 2){
                    // 当chs[i] 在 [j, i - 1] 区间内已经出现了两次的情况下，不需要减去重复的，因为之前已经减过了
                    dp[i & 1][j] = dp[(i - 1) & 1][j];
                } else {
                    // existNum <= 0的情况下
                    dp[i & 1][j] = dp[(i - 1) & 1][j] + 1;
                }
                sum += dp[i & 1][j];
            }
        }
        return sum;
    }


    public int uniqueLetterString3(String s) {
        // 该思路是uniqueLetterString1的优化版本。
        if (null == s || s.length() <= 0) { return 0; }
        char[] chs = s.toCharArray();
        ArrayList<Integer>[] flags = new ArrayList[26];
        for (int i = 0; i < 26; i ++) { flags[i] = new ArrayList<>(); }
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < chs.length; i ++) {
            int charIndex = chs[i] - 'A';
            int size = flags[charIndex].size();
            if (size == 0) {
                // 当之前没有chs[i]出现的时候，所有[j, i], j = 0, 1, 2, ... i的子串的“唯一字符数量”都加1, j的取值一共有i + 1项，所以 sum1 += i + 1
                sum1 += i + 1;
            } else if (size >= 1) {
                // 当之前已经出现过至少一次chs[i]的时候， 在区间[j, i], j = last + 1, last + 2, ..., i 的子串的“唯一字符数量”都加1, j的取值一共是 i - last 项 所以 sum += i - last
                // 在区间[j, i], j = last2 + 1, last2 + 2, .... , last 的子串"唯一字符数量"都要-1, 因为原本唯一的字符因为当前字符的加入而变得不唯一， 鉴于j的取值一共是last - last2项
                // 所以 sum -= last - last2;
                // 在区间[j, i], j = 0, 1, 2, ..., last2 的字串 因为与chs[i]这个字符本身已经出现了两次以上，该减的都减过了，所以啥都不用管。
                int last = flags[charIndex].get(size - 1);
                int last2 = -1;
                if (size >= 2) {
                    last2 = flags[charIndex].get(size - 2);
                }
                sum1 += i - last;
                sum1 -= last - last2;
            }
            flags[charIndex].add(i);
            sum0 += sum1;
        }
        return sum0;
    }

    public static void main(String[] args) {
        Test828 t = new Test828();
        String s;
        s = "LEETCODE";
//        s = "ABCDEFG";
//        s = "AAA";
        System.out.println(t.uniqueLetterString3(s));
    }
}
