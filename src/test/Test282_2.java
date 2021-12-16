package test;

import java.util.ArrayList;
import java.util.List;

public class Test282_2 {
    public List<String> addOperators(String num, int target) {

    }

    public void help(String exp, int target, long wholeValue,
                     long firstFactor, long secondFactor, char lastOp, List<String> ans) {
        if (wholeValue == target) ans.add(exp);

        long deno = (long)1e10;

        while (deno > 0) {
            long f = secondFactor / deno;
            long s = secondFactor % deno;
            if (f == 0) continue;
            calc(firstFactor, secondFactor, lastOp, false);

        }
    }

    public long calc(long first, long second, char lastOp, boolean reverse) {
        switch (lastOp) {
            case '+': return !reverse ? first + second : first - second;
            case '-': return !reverse ? first - second : first + second;
            default : return !reverse ? first * second : first / second;
        }
    }

}
