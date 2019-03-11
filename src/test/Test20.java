package test;

import java.util.Stack;

/*
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.


*
*
* */
public class Test20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chs = s.toCharArray();
        for(char c : chs){
            if(c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if(c == ')' || c == ']' || c == '}')
                if(stack.isEmpty() || !isMatches(stack.pop(), c))
                    return false;

        }
        return stack.isEmpty();
    }

    private static boolean isMatches(char c, char c2){
        return c == '(' && c2 == ')'
                || c == '[' && c2 == ']'
                || c == '{' && c2 == '}';
    }
}
