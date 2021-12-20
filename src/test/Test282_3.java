package test;

import java.util.ArrayList;
import java.util.List;

public class Test282_3 {
    public List<String> addOperators(String num, int target) {
        ArrayList<String> ans = new ArrayList<>();
        help((long)Math.pow(10, num.length() - 1), Long.parseLong(num), "", 0, 0, target, ans, true);
        return ans;
    }

    public void help(long start, long num, String exp, long last0, long last1, long target,
                     ArrayList<String> ans, boolean flag) {
//        System.out.println("start:" + start + ", num:" + num + ", last0:" + last0 + ", last1:" + last1);
        if (0 == start && last0 == target) {
            ans.add(exp);
            return;
        }

        while (start > 0) {
            long n0 = num / start;
            long n1 = num % start;
            if (flag) {
                help(start / 10, n1, String.valueOf(n0), n0, n0, target, ans, false);
            } else {
                help(start / 10, n1, exp + '+' + n0, last0 + n0, n0, target, ans, false);
                help(start / 10, n1, exp + '-' + n0, last0 - n0, -n0, target, ans, false);
                help(start / 10, n1, exp + "*" + n0, last0 - last1 + n0 * last1, n0 * last1, target, ans, false);
            }
            start /= 10;
            if (0 == n0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Test282_3 t = new Test282_3();
        Test282_2 t2 = new Test282_2();
        String num = "0012090710";
        int target = 26;
        List<String> result = t.addOperators(num, target);
        List<String> result2 = t2.addOperators(num, target);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result.size() + ", " + result2.size());
    }
}
