package test;

import java.util.Stack;

/*
* Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.


*
* */
public class Test32 {
    public static void main(String[] args){
        String s = "()()()(((()))()(((";
        System.out.println(new Test32().longestValidParentheses(s));
    }
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] chs = s.toCharArray();
        boolean [] matches = new boolean[chs.length];
        int maxLength = 0;
        int length = 0;
        for(int i = 0; i < chs.length; i++){
            if(chs[i] == '(')
                stack.push(i);
            else if(!stack.isEmpty())
                matches[i] = matches[stack.pop()] = true;
        }
        for(boolean b : matches)
            if(b) length ++;
            else{
                if(maxLength < length)
                    maxLength = length;
                length = 0;
            }

        return Math.max(maxLength, length);
    }
}
