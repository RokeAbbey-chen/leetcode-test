package test;

/*
* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

*
* */
public class Test5 {
    public static void main(String[] args){
        Test5 t = new Test5();
        String s = "1028abcdedcba1001001";
        s = "";
        s = "a";
        s = "abccbdefe";
//        s = "babad";
//        s = "ccc";
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 100000; i++)
            sb.append('c');
        s = sb.toString();
        System.out.println(t.longestPalindrome(s));
    }
    public String longestPalindrome(String s) {
        if(s == null || s.length() <= 0)
            return "";
        char[] chs1 = s.toCharArray();
        char[] chs2 = new char[(chs1.length << 1) + 1];
        for(int i = 0; i < chs2.length ;i++)
            chs2[i] =  (i & 1) == 1 ? chs1[i >> 1] : '#';
        //System.out.println("chs2 = " + String.valueOf(chs2));
        chs2 = fun(chs2);
        chs1 = new char[chs1.length];
        int j = 0;
        for(int i = 0; i < chs2.length; i++)
            if(chs2[i] != '#')
                chs1[j++] = chs2[i];

        return String.valueOf(chs1, 0, j);

    }

    public char[] fun(char[] chs){
        int[] rads = new int[chs.length];
        int j = 0;
        int k = 1;
        int index = 0;
        int sum = 0;
        for(int i = 0;i < chs.length; i += k){
            while(i - j >= 0 && i + j < chs.length && chs[i - j] == chs[i + j]){
                j++;
                sum ++;
            }
            j--;
//            sum += j;
            rads[i] = j;
//            if(i - j - 1 < 0)
//                continue;
            //System.out.println("rads[" + i + "] = " + rads[i]);
            //System.out.println("j = " + j + ", value = " + chs[index] + ", index = " + index ++);
            for(k = 1; k <= j && rads[i - k] != j - k; k++) {
                rads[i + k] = Math.min(rads[i - k], j - k);
                sum ++;
                //System.out.print(", rads[" + (i + k) + "] = " + rads[i + k]);
            }
            j = Math.max(j - k, 0);
        }
        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i <  rads.length; i++)
            if(max < rads[i]) {
                max = rads[i];
                maxIndex = i;
            }

        //System.out.println(" max = " + max + ", maxIndex = " + maxIndex);
        System.out.println("sum = " + sum );
        char[] result = new char[(max << 1) + 1];
        System.arraycopy(chs, maxIndex - max, result, 0, (max << 1) + 1);
        return result;
    }


}
