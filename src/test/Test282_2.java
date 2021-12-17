package test;

import java.util.ArrayList;
import java.util.List;

public class Test282_2 {
    public List<String> addOperators(String num, int target) {
        ArrayList<String> ans = new ArrayList<>();
        help(num, 0, 0, 0, "", ans, target);
        return ans;
    }

    public void help(String num, int start, long last0, long last1, String exp, ArrayList<String> ans, long target){
//        System.out.println("exp:" + exp + ", = " + last0);
        int len = num.length();
        if (last0 == target && start == len)
            ans.add(exp);

        for (int i = start + 1; i <= len; i ++) {
            String operand = num.substring(start, i);
            if (operand.length() >= 2 && '0' == operand.charAt(0)) continue;
            long operandInt = Long.parseLong(operand);

            if (0 == start) {
                help(num, i,last0 + operandInt, operandInt, operand, ans, target);
            } else {
                help(num, i,last0 + operandInt, operandInt, exp + '+' + operand, ans, target);
                help(num, i,last0 - operandInt, -operandInt, exp + '-' + operand, ans, target);
                help(num, i, last0 - last1 + last1 * operandInt,
                        last1 * operandInt, exp + '*' + operand, ans, target);
            }
        }
    }



    public static void main(String[] args) {
        String num = "28920032";
        int target = 124;
//        String num = "003";
//        int target = 3;

//        String num = "3456237490";
//        int target = 9197;

        Test282_2 t = new Test282_2();
        Test282 t2 = new Test282();
        List<String> result = t.addOperators(num, target);
        System.out.println(result);

        List<String> result2 = t2.addOperators(num, target);
        System.out.println(result2);
    }

}
