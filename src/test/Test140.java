package test;

import java.util.*;

/*
*
* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*方法1
*
* */
public class Test140 {

    HashSet<String> wrong;
    public List<String> wordBreak(String s, List<String> wordDict) {
        TreeSet<String> treeSet = new TreeSet<>(wordDict);
        wrong = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        wordBreak(s, sb, result, treeSet);
        return result;
    }

    private boolean wordBreak(String str, StringBuilder sb, List<String> list, TreeSet<String> set){
        if (str.isEmpty()){ return true; }
        if (wrong.contains(str)){ return false; }
        boolean wrongFlag = true;
        if (set.contains(str)){
            sb.append(str);
            list.add(sb.toString());
            sb.delete(sb.length() - str.length(), sb.length());
            wrongFlag = false;
        }
        String s = str;
        while((s = set.lower(s)) != null){
            if (str.startsWith(s)){
                sb.append(s);
                sb.append(' ');
                wrongFlag = !wordBreak(str.substring(s.length()), sb, list, set) && wrongFlag;
                sb.delete(sb.length() - s.length() - 1, sb.length());
            }
            else if (s.charAt(0) == str.charAt(0)){ continue; }
            else { break; }
        }
        if (wrongFlag){
            wrong.add(str);
        }
        return !wrongFlag;
    }

    public static void main(String[] args) {

        Test140 t = new Test140();
        String str = "catsanddog";
        List<String> dict = Arrays.asList("cat","cats","and","sand","dog");

//        str = "aaaaaaa";
//        dict = Arrays.asList("aaaa","aa","a");
//        str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        dict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(t.wordBreak(str, dict));

    }
}
