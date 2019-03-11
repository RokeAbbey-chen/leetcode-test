package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

/*
* Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
* */
/*
*
* */
public class Test3 {
    public static void main(String[] args){
        String s = "abcbdefdhijkl";
        System.out.println(new Test3().lengthOfLongestSubstring(s));
    }
    public int lengthOfLongestSubstring(String s) {

        int[] array1 = new int[128]; //用来记录各个字母最后一次(在当前时刻之前)出现的位置
        int[] lastAppearanceIndex = new int[s.length()]; //用来记录当前下标对应字母上一次出现的位置

        for(int i = 0; i < 128; i ++)
            array1[i] = -1;

        char[] chs = s.toCharArray();
        int start = 0;
        int max = 0;
        for(int i = 0; i < chs.length; i++){
            lastAppearanceIndex[i] = array1[chs[i]];
            array1[chs[i]] = i;
            if(lastAppearanceIndex[i] >= start){
                max = Math.max(i - start, max);
                start = lastAppearanceIndex[i] + 1;
            }
        }

        max = Math.max(max, chs.length - start);
        return max;
    }
}
