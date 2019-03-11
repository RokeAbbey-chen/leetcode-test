package test;

import java.util.HashSet;
import java.util.Objects;

/*
*
* Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.
Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
* */
// 超时
public class Test467_2 {
    public int findSubstringInWraproundString(String p) {
        if (p.length() <= 1){ return p.length(); }
        char[] chp = p.toCharArray();
        int[] dp = new int[2];
        dp[0] = 1;
        int result = 1;
        int[] dulplicate = new int[26];
        dulplicate[chp[0] - 'a'] = 1;
        for (int i = 1; i < chp.length; i ++){
            if (check(chp[i - 1], chp[i])){
                dp[i & 1] = dp[(i - 1) & 1] + 1;
            }
            else { dp[i & 1] = 1; }
            if (dulplicate[chp[i] - 'a'] < dp[i & 1]){
                result += dp[i & 1] - dulplicate[chp[i] - 'a'];
                dulplicate[chp[i] - 'a'] = dp[i & 1];
            }
        }
        return result;
    }

    private boolean check(char a, char b){
        return b - a == 1 || b == 'a' && a == 'z';
    }

    public static void main(String[] args) {
        Test467_2 t = new Test467_2();
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++){ sb.append(c); }
        sb.append(sb);
//        s = "abcdefg";
//        s = "cac";
//        sb.append('a');
//        HashMap map = new HashMap();
        String s = sb.toString();
        System.out.println(s);
        System.out.println(t.findSubstringInWraproundString(s));
    }
}
