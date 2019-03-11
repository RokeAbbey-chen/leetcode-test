package test;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
Credits:
Special thanks to @hpplayer for adding this problem and creating all test cases.

*
* */
public class Test301_2 {
    public static void main(String[] args){
//        HashSet<String> set = new HashSet<>();
//        String s1 = "abc";
//        String s2 = new String(s1);
//        set.add(s1);
//        System.out.println(set.contains(s2));
        String s = "(((())))";
        s = "";
        s = "()())()";
        s = "))";
        s = "()())()";
        s = ")(";
        s = "(()(";
        s = "))()()p";
        s = "))))ab))c))d))e()fg";
        s = "()())()";
        System.out.println(new Test301_2().removeInvalidParentheses(s));
    }
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> sset = new HashSet<>();
        List<String>  res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Pattern p = Pattern.compile("^\\)*(\\w*)");
        Matcher m = p.matcher(s);
        m.find();
        StringBuffer head = new StringBuffer();
        do{
            s = s.substring(m.end());
            System.out.println("m.group(1) = " + m.group(1));
            head.append(m.group(1));
            System.out.println(head);
            m = p.matcher(s);
        }while(m.find() && m.end() != 0);
        String h = head.toString();
        if(isValid(s))
            return Arrays.asList(h+s);

        queue.add(s.substring(m.end()));
        int maxValidLength = -1;
        while(!queue.isEmpty()){
            String tempS = queue.poll();
            char[] chs = tempS.toCharArray();
            for(int i = 0; i < chs.length; i++){
                if(chs[i] != '(' && chs[i] != ')')
                    continue;
                while(i != 0 && i < chs.length && chs[i] == chs[i - 1]) i++;
                if(i >= chs.length)
                    break;
                String newString = tempS.substring(0, i) + tempS.substring(i + 1, chs.length);
                boolean flag = !sset.contains(newString);
                System.out.println(i + ", newString : " + newString + ", flag = " + flag + ", valid = " + isValid(newString)
                            + ", maxLength = " + maxValidLength + ", str.len = " + newString.length());
                if(flag && isValid(newString)) {
                    if(maxValidLength == -1)
                        maxValidLength = newString.length();
                    if(newString.length() == maxValidLength){
                        res.add(h + newString);
                        sset.add(newString);
                    }
                }
                else if(flag && maxValidLength <= newString.length()){
                    sset.add(newString);
                    queue.add(newString);
//                    System.out.println(sset);
                }
            }
        }
//        res.forEach(st->System.out.println(st.hashCode()));
        if(res.isEmpty())
            res.add(h);
        return res;
    }

    private static boolean isValid(String s){
        char[] chs = s.toCharArray();
        int n = 0;
        for (char ch : chs) {
            if(ch == '(') n ++;
            else if(ch == ')' && n -- <= 0) return false;

        }
        return n == 0;
    }

}
