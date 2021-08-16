package test;

import javax.sound.midi.Soundbank;

public class Test76 {
    public String minWindow(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[] tCounts = new int[52];
        int[] sCounts = new int[52];
        long flag = 0;
        long satisfiedFlag = getSatisfiedFlag(tChar, tCounts);
        int start = 0, end = 0;
        final int MAX_END = 600000;
        int minStringStart = 0, minStringEnd = MAX_END;

        for (; start < sChar.length && tCounts[getIndex(sChar[start])] <= 0; start ++) {}
        if (start >= sChar.length) {
            return "";
        }

        end = start;
        for (; end < sChar.length; end ++) {
            int index = getIndex(sChar[end]);
            sCounts[index] ++;
            if (sCounts[index] >= tCounts[index]) {
                flag |= 1L << index;
            }

            int index2 = getIndex(sChar[start]);
            while (start <= end && sCounts[index2] > tCounts[index2]) {
                sCounts[index2] --;
                start ++;
                index2 = getIndex(sChar[start]);
            }

            if ((~flag & satisfiedFlag) == 0
                    && end + 1 - start < minStringEnd + 1 - minStringStart) {
                minStringEnd = end;
                minStringStart = start;
            }
        }
        if (minStringEnd == MAX_END){
            return "";
        }
        return String.valueOf(sChar, minStringStart, minStringEnd + 1 - minStringStart);

    }

    public int getIndex(char c) {
        int index;
        if (c < 'a') {
            index = c - 'A';
        } else {
            index = c - 'a' + 26;
        }
        return index;
    }

    public long getSatisfiedFlag(char[] tChar, int[] tCounts) {
        long satisfiedFlag = 0;
        for (int i = 0; i < tChar.length; i++) {
            char tc = tChar[i];
            int index = getIndex(tc);
            satisfiedFlag |= 1L << index;
            tCounts[index] ++;
        }
        return satisfiedFlag;
    }

    public static void main(String[] args) {
        Test76 test = new Test76();
        String s = "abcdefabddeg";
        String t = "dafd";

        s = "dasjqbkpoldjqwiudjnlsalkudiwljdashkl";
        t = "sjlk";
//        s = "abc";
//        t = "abcc";
        String r = test.minWindow(s, t);
        System.out.println("r:" + r);
    }
}
