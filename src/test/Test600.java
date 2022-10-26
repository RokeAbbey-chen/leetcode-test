package test;

import java.util.HashMap;
import java.util.Map;

public class Test600 {

    static Map<Integer, Integer> log = new HashMap();
    static int[] An = new int[33];
    static {
        for (int i = 0; i < 32; i++)
            log.put(1 << i, i);

        An[0] = 1;An[1] = 2;An[2] = 3;An[3] = 5;
        for (int i = 4; i < 33; i ++) {
            An[i] = (An[i - 2] << 1) + An[i - 3];
        }
    }


    public int findIntegers(int n) {
        if (n <= 1) return n + 1;
        int n2 = n;
        int[] bits = new int[33];
        int count = 0;
        while (n2 > 0) {
            int num = ((n2 - 1) ^ n2) + 1 >> 1;
            int lg = log.get(num);
            bits[count++] = lg;
//            System.out.print(lg + " ");
            n2 -= num;
        }
        System.out.println();
        int result = An[bits[count - 1]];
//        System.out.println("result0: " + result);
        int i;
        for (i = count - 2; i >= 0; i --) {
            result += An[bits[i]];
//            System.out.println("i = " + i + ", result = " + result + ", bits[i] = " + bits[i] + ", An = " + An[bits[i]]);
            if (bits[i] != bits[i + 1] - 1) {
//                System.out.println("result = " + result + ", bits[i] = " + bits[i] + ", bits[i + 1] = " + bits[i + 1]);
//                result ++;
            } else break;
        }
        if (-1 == i) result ++;
        return result;
    }

    public static void main(String[] args) {
        Test600 t = new Test600();
        int n = 9;
//        n = 158766;
//        n = 159022;
//        n = 10;
//        n = 0;
//        n = 2;
        n = 4693;
        int result = t.findIntegers(n);
        System.out.println(result);
    }
}
