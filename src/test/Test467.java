package test;

import java.util.Collections;
import java.util.HashMap;
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
public class Test467 {
    public int findSubstringInWraproundString(String p) {
        char[] chp = p.toCharArray();
        if (chp.length <= 0){ return 0; }
        if( chp.length == 1 ){
            if ( chp[0] >= 'a' && chp[0] <= 'z') { return 1; }
            else { return 0; }
        }

        int[] dp = new int[2];
        dp[0] = 1;
        HashSet<Node> set = new HashSet<>();
        set.add(new Node(chp[0], chp[0], 1));
        int maxLength = 1;
        for (int i = 1; i < chp.length; i ++){
            if (check(chp[i - 1], chp[i])){
                dp[i & 1] = dp[(i - 1) & 1] + 1;
                maxLength = Math.max(maxLength, dp[i & 1]);
            }
            else { dp[i & 1] = 1; }
            for (int j = 1; j <= Math.min(dp[i & 1], 26) && dp[i & 1] >= maxLength - 26; j ++){
                set.add(new Node(chp[i - dp[i & 1] + j], chp[i], dp[i & 1] - j + 1));
            }
        }
        return set.size();
    }

    private boolean check(char a, char b){
        return b - a == 1 || b == 'a' && a == 'z';
    }

    private static class Node{
        private char from;
        private char end;
        private int length;

        public Node() {
        }

        public Node(char from, char end, int length) {
            this.from = from;
            this.end = end;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o == null || getClass() != o.getClass()){ return false;}
            Node node = (Node) o;
            return from == node.from &&
                    end == node.end &&
                    length == node.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, end, length);
        }
    }
    public static void main(String[] args) {
        Test467 t = new Test467();
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
