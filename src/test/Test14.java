package test;


/*
* Write a function to find the longest common prefix string amongst an array of strings.
*
* */
public class Test14 {
    public static void main(String[] args){
        Test14 t = new Test14();
        String[] strs = {"abcdefg", "abcdabc", "abddabc", "abcdefh"};
        System.out.println(t.longestCommonPrefix(strs));
    }
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        if(strs.length == 1)
            return strs[0];

        int min = strs[0].length();
        for(int i = 1; i < strs.length; i++)
        {
            int minSize = Math.min(strs[0].length(), strs[i].length());
            int j = 0;
            for(j = 0; j < minSize && strs[i].charAt(j)  == strs[0].charAt(j); j ++);
            if(min > j)
                min = j;
        }
        return strs[0].substring(0, min);
    }
}
