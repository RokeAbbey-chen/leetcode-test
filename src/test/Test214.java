package test;

/*
* Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.


* */
public class Test214 {
    public static void main(String[] args){
        Test214 t = new Test214();
        String s = "aacecaaa";
        s = "abcd";
        s = "";
        s = "acdefbhijklm";
        s = "abcddcba";
        s = "abcdedcba";
        s = "abcdedcbafgfabcdedcbahijk";
        s = "aaa";
        s = "abbacd";
        s = "aaaaa";
        System.out.println(t.shortestPalindrome(s));
    }
    public String shortestPalindrome(String s) {
        //System.out.println("chs1 = " + s);
        char[] chs1 = s.toCharArray();
        char[] chs2 = new char[(chs1.length << 1) + 1];
        for(int i = 0; i < chs2.length; i++)
            chs2[i] = (i & 1) == 0? '#': chs1[i >> 1];
        int index = fun(chs2);
        boolean flag = chs2[index] == '#';
        if(flag)
            index --;
        index >>= 1;
        index += flag? index + 1 : index;
        StringBuffer sb = new StringBuffer();
        for(int i = chs1.length - 1; i > index; i --)
            sb.append(chs1[i]);
        return sb.toString() + s;
    }

    public int fun(char[] chs){
        //System.out.println("chs2 = " + String.valueOf(chs));
        int[] rads = new int[(chs.length >> 1) + 1];
        int j = 0;
        int k = 0;
        for(int i = 0; i < rads.length; i += k){
            while(i - j >= 0 && i + j < chs.length && chs[i - j] == chs[i + j]) j++;
            j--;
            rads[i] = j;
//            //System.out.print("rads[" + i + "] = " + rads[i] + ",, ");
            for(k = 1; k <= j && i + k < rads.length && rads[i - k] != j - k; k++) {
                rads[i + k] = Math.min(rads[i - k], j - k);
//                //System.out.print("rads[" + (i + k) + "] = " + rads[i + k] + ", ");
            }

            j = Math.max(j - k, 0);
        }
        int maxIndex = 0;
        for(int i = 0; i < rads.length; i++)
            if(rads[i] == i && rads[maxIndex] < rads[i])
                maxIndex = i;
        //System.out.println("maxIndex = " + maxIndex);
        return maxIndex;
    }
}
