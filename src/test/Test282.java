package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Test282 {
    /**
     * 超时
     * 返回的表达式要求没有前导0 且 最开始不会出现负号。
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(num);
        HashSet<String> noRepeat = new HashSet<>();
        noRepeat.add(num);
        char[] ops = {'+', '-', '*'};
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String str = queue.removeFirst();
//            System.out.println("str:" + str + ", cal:" + calculate(str) + ", eq:" + (calculate(str) == target));
            if (!checkLeadingZero(str) && calculate(str) == target) {
                result.add(str);
            }
            int length = str.length();
            for (int i = 1; i < length; i ++) {
                for (char o: ops) {
                    String newStr;
                    if (length >= 2 && i >= 1 && (charIsNum(str.charAt(i - 1)) && charIsNum(str.charAt(i)))) {
                        String left = str.substring(0, i);
                        String right = str.substring(i);
//                        if ('*' == o) {
//                            int calLeft = calculate(left);
//                            if (calLeft != 0 && target % calLeft != 0) {
//                                /**遇到乘法时，如果根本除不尽，则直接跳过*/
//                                continue;
//                            }
//                        }

                        newStr = left + o + right;
                        if (noRepeat.contains(newStr)) continue;
                        queue.addLast(newStr);
                        noRepeat.add(newStr);
                    }
                }
            }
        }
        return result;
    }

    public boolean charIsNum(char c) {
        return '0' <= c && c <= '9';
    }

    public boolean checkLeadingZero(String exp) {
        int length = exp.length();
        if (length <= 1) return false;
        for (int i = 0; i + 1 < length; i ++) {
            if (i == 0 && exp.charAt(i) == '0' && charIsNum(exp.charAt(i + 1))
                || i >= 1 && !charIsNum(exp.charAt(i - 1)) && '0' == exp.charAt(i) && charIsNum(exp.charAt(i + 1))) {
                return true;
            }
        }
        return false;
    }

    public int calculate(String expression) {
        char[] chs = expression.toCharArray();
        int numCount = 0; //
        for (int i = 0; i < chs.length; i ++) {
            if (chs[i] < '0' || chs[i] > '9') {
                numCount ++; // 符号数<=整数数量 + 1, 所以统计符号数也是一样的。
            }
        }
        numCount ++;
        Object[] stack = new Object[(numCount << 1) + 1];

        int len = 0;
        for (int i = 0; i < chs.length; i ++) {
            len = resolveChar(chs[i], stack, len);
        }
        resolveChar((char)0, stack, len);
        return (int)stack[0];
    }

    public int resolveChar(char c, Object[] stack, int len) {
        if (charIsNum(c)) {
            if (len >= 1 && stack[len - 1] instanceof Integer) {
                stack[len - 1] = 10 * (Integer)stack[len - 1]+ (c - '0');
            } else {
                stack[len ++] = (Integer)(c - '0');
            }
        } else {
            if (len >= 2) {
                Integer lllast = len >= 3 ? (Integer)stack[len - 3] : 0;
                if (stack[len - 2].equals('-') && c != '*') {
                    stack[Math.max(len - 3, 0)] = lllast - (Integer)stack[len - 1];
                    len -= len >= 3 ? 2: 1;
                } else if (stack[len - 2].equals('+') && c != '*') {
                    stack[Math.max(len - 3, 0)] = lllast + (Integer)stack[len - 1];
                    len -= 2;
                } else if (stack[len - 2].equals('*')) {
                    assert len >= 3;
                    stack[Math.max(len - 3, 0)] = lllast * (Integer)stack[len - 1];
                    len -= 2;
                    if (c != '*') {
                        len = resolveChar((char) 0, stack, len);
                    }
                }

            }
            if (0 != c) {
                stack[len++] = c;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        Test282 t = new Test282();
//        String exp = "1+2*3+4*5*6-10";
//        String exp = "111";
//        System.out.println("1234".substring(3));
//        System.out.println(t.calculate(exp));
//        String str = "12345";
//        int target = -6;
//        String str = "124372893";
//        int target = 123;
//        String str = "999999999";
        String str = "123456789";
        int target = 80;
        long start = System.currentTimeMillis();
        List<String> result = t.addOperators(str, target);
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
        System.out.println(result);
    }

}
