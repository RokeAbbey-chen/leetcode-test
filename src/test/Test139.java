package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/*
*
*
*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
* */
public class Test139 {
    private HashSet<String> wrong;
    public boolean wordBreak(String s, List<String> wordDict) {
        wrong = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>(wordDict);
        return test(s, treeSet);
    }

    private boolean test(String str, TreeSet<String> set){
        if (set.contains(str)){ return true; }
        if (wrong.contains(str)){ return false; }
        String s = str;
        while ((s = set.lower(s) )!= null){
            if (str.startsWith(s)){
                String substr;
                if (test(substr = str.substring(s.length()), set)){
//                    set.add(substr);//只有一个字符串不需要记忆
                    return true;
                }
                else{
                    wrong.add(substr);
                }
            }
            else if (s.charAt(0) == str.charAt(0)){ continue; }
            else {
                return false;
            }
        }
        return false;
    }
}
