package test;

import java.util.Arrays;
import java.util.Comparator;

public class Test564 {

    public String nearestPalindromic(String n) {
        int len = n.length();
        long n2 = Long.parseLong(n);
        long n3 = Long.parseLong(n.substring(0, (len + 1) >> 1));
        long base = (long)Math.pow(10, len >> 1);
        long div = 1 == (len & 1) ? 10 : 1;
        long bases1 = base, basea1 = base;
        long divs1 = (len & 1) == 1 ? 10 : 1;
        long diva1 = (len & 1) == 1 ? 10 : 1;
        long n3s1 = n3 - 1;
        long n3a1 = n3 + 1;
        int magn3 = mag(n3), magn3s1 = mag(n3s1), magn3a1 = mag(n3a1);
        if (n3s1 == 0 || magn3s1 != magn3) {
            if ((len & 1) == 1) divs1 = 1;
            else {
                n3s1 = 10 * n3s1 + 9;
                divs1 = 10;
                bases1 /= 10;
            }
        }

        if (magn3a1 != magn3) {
            if ((len & 1) == 1) {
                n3a1 /= 10;
                diva1 = 1;
                basea1 *= 10;
            } else {
                diva1 = 10;
            }
        }

        Comparator<Long> cmp = (o0, o1)-> {
            long abs0 = Math.abs(n2 - o0);
            long abs1 = Math.abs(n2 - o1);
            if (abs0 < abs1) return -1;
            else if (abs0 > abs1) return 1;
            else if (o0 < o1) return -1;
            else if (o0 > o1) return 1;
            return 0;
        };
        Long[] candidates = {
                makeCandidate(n3, base, div),
                makeCandidate(n3s1, bases1, divs1),
                makeCandidate(n3a1, basea1, diva1)
        };
        if (candidates[0] == n2) {
            candidates[0] = candidates[1];
        }
        Arrays.sort(candidates, cmp);
        return candidates[0].toString();
    }

    public long makeCandidate(long head, long base, long div) {
        return head * base + Long.parseLong(new StringBuffer(head / div + "").reverse().toString());
    }

    public int mag(long num) {
        if (0 == num) return 1;
        int n = 0;
        while (num > 0) {n ++;num /= 10;}
        return n;
    }

    public static void main(String[] args) {
        Test564 t = new Test564();
//        String n = "12345678";
        String n = "23981997691";
//        String n = "10";
//        String n = "99899999";
//        String n = "1325060231";

        String result = t.nearestPalindromic(n);
        System.out.println(result);
    }

}
