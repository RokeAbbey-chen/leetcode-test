package test;

import java.util.LinkedList;
import java.util.List;

/*
* Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*
*
* */
public class Test131 {
    public static void main(String[] args){
        Test131 t = new Test131();
        String s = "aab";
        s = "";
        s = "abcdcbab";
        System.out.println(t.partition(s));
    }
    private int[] tempRadius;
    public List<List<String>> partition(String s) {
        char[] tempChs = s.toCharArray();
        char[] chs = new char[(tempChs.length << 1) + 1];
        char TAG = '#';
        for(int i = 0; i < chs.length; i ++){ chs[i] = (i & 1) == 0 ? TAG : tempChs[i >> 1]; }
        tempRadius = getPalindromeRadius(chs);
        List<List<String>> result = new LinkedList<>();
        LinkedList<String> l = new LinkedList<>();
        generateResult(l, result, s, 0);
        return result;
    }
    private void generateResult(LinkedList<String> l, List<List<String>> result,
                                    String s, int start){
        int length = s.length();
        for(int i = 0; start + i + 1 <= length; i ++){
            boolean flag = isParindrome(start, start + i);
            if (flag) {
                l.addLast(s.substring(start, start + i + 1));
                if (start + i + 1 >= length) {
                    result.add((LinkedList<String>) l.clone());
                    l.removeLast();
                    return;
                } else{
                    generateResult(l, result, s, start + i + 1);
                    l.removeLast();
                }
            }
        }
    }

    private boolean isParindrome(int start, int end){ //包括end
        int tempStart = (start << 1) + 1;
        int tempEnd = (end << 1) + 1;
        return (tempRadius[(tempStart + tempEnd) >> 1] << 1) + 1 >= tempEnd - tempStart + 1;
    }
    private int[] getPalindromeRadius(char[] chs){
        int[] radius = new int[chs.length];
//        int k = 1;
        int j;
        for(int i = 1; i < chs.length; i += j){
            int r = 0;
            while(i - 1 - r >= 0
                    && i + 1 + r < chs.length
                    && chs[i - 1 - r] == chs[i + 1 + r]) { r++; }
            radius[i] = r;
            for(j = 1;
                j <= radius[i] && i - j >= 0 && i + j < chs.length && i - j - radius[i - j] != i - radius[i];
                j ++){ radius[i + j ] = Math.min(radius[i - j], radius[i] - j); }

        }
        return radius;
    }
}
