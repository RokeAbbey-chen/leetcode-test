package test;

import java.util.*;

public class Test224 {

    private static final HashSet<Character> ops = new HashSet<>(Arrays.asList('(', '+', '-'));

    public int calculate(String s) {
        char[] chs = s.toCharArray();

        ArrayList<Object> units = new ArrayList<>();
        for (int i = 0; i < chs.length; i ++) {
            int num = -1;
            for (;i < chs.length && '0' <= chs[i] && chs[i] <= '9';i ++) {
                num = Math.max(num, 0);
                num = num * 10 + (chs[i] - '0');
            }

            if (num >= 0) units.add(num);

            if (i >= chs.length) break;
            if (' ' == chs[i]) continue;
            units.add(chs[i]);
        }

        ArrayList<Object> stack = new ArrayList<>();
        for (Object unit: units) {
            resolve(unit, stack);
        }
        return (int)stack.get(0);
    }


    public void resolve(Object unit, ArrayList<Object> stack) {
        int size = stack.size();
        if (size <= 0 || ops.contains(unit)) {
            stack.add(unit);
            return;
        }

        Object last = stack.remove(size - 1);
        if (last.equals('+')) {
            Integer llast = (Integer) stack.remove(size - 2);
            stack.add(llast + (Integer) unit);
            return;
        }

        if (last.equals('-')) {
            if (size - 2 >= 0) {
                Object llast =  stack.remove(size - 2);
                if (llast instanceof Integer) {
                    // 如果-前面是整数，才做减法
                    stack.add((Integer) llast - (Integer) unit);
                    return;
                }
                stack.add(llast);
            }
            // - 前面不是整数，所以就转换当前符号, 注意此时unit不会为 左括号, 所以必定为整数。
            stack.add(-(Integer)unit);
            return;
        }

        if (unit.equals(')')) {
            Object llast = stack.remove(size - 2);
            assert llast.equals('(');
            resolve(last, stack);
            return;
        }
        stack.add(last);
        stack.add(unit);

    }






    public static void main(String[] args) {
        Test224 t = new Test224();
        String s = "12+(124-148)-(123 + 456)";
//        s = "-(-(-123))";
        s = "(123+(456)+(-789))-(-(10)-(-(-10)))";
        System.out.print(s + " = ");
        int result = t.calculate(s);
        System.out.println(result);
    }
}
