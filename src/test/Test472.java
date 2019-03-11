package test;

import java.util.*;

/*
* Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
*
* */
public class Test472 {
    private HashSet<String> mem;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TreeSet<String> set = new TreeSet<>();
        ArrayList<String> result = new ArrayList<>();
        mem = new HashSet<>();
        for (String s : words){
            if (!s.isEmpty()) { set.add(s); }
        }
        for (String s : set){
            if (helper(set, s, false)){ result.add(s); }
        }
        return result;
    }

    public boolean helper(TreeSet<String> set, String str, boolean flag){
        if ((mem.contains(str) || set.contains(str)) && flag){ //false时代表并非由两个以上字符串组成
            return true;
        }
        String s = str;
        boolean jump = false;
        while (jump && s != null || (s = set.lower(s)) != null){
            jump = false;
            if (str.startsWith(s)){
                String subStr;
                if (helper(set, subStr = str.substring(s.length()), true)){
                    mem.add(subStr);
                    return true;
                }
            }
            else {
                s = match(s, str);
                if (s == null) { return false; }
                else {
                    jump = true;
                    s = set.floor(s);
                }
            }
        }
        return false;
    }

    private String match(String s1, String s2){
        int len = Math.min(s1.length(), s2.length());
        int i;
        for (i = 0; i < len && s1.charAt(i) == s2.charAt(i); i ++){ }
        return i == 0 ? null : s1.substring(0, i);
    }
    public static void main(String[] args) {
        Test472 t = new Test472();
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        words = new String[]{"g", "f", "v", "e", "gfve"};
        words = new String[]{
        };
        System.out.println(t.findAllConcatenatedWordsInADict(words));
    }


}
