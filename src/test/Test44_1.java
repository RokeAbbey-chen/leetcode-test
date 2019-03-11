package test;

import java.util.ArrayList;

/*
* '?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*
*
* "abefcdgiescdfimde"
"ab*cd?i*de"



"aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba"
"a*******b" //超时
*
* //超时 换新思路
*"abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"

* */
public class Test44_1 {
    public static void main(String[] args){
//        Test44_1 t = new Test44_1();
//        int[] next = t.getNext("abcdabceabcdabcgfabcdg".toCharArray());
//        for(int i : next)
//            System.out.print(i + " ");
//        System.out.println();

//        System.out.println("".split("a").length);
//        System.out.println("a".split("a").length);

        String s = "aa";
        String p = "a";

//        p = "aa";
//        s = "aaa";
//        p = "*";
//
//        s = "abcdefg";
//        p = "a*d*g*";
//        p = "a*d*g";
//        p = "a*d*gc";
//
//        s = "abefcdgiescdfimde";
//        p = "ab*cd?i*de";
////
//        s = "a";
//        p = "";
////
        s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
//        p = "a*******b"; //超时
        p = "a********a";
//        s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
//        p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        s = "zacabz";
        p = "*a?b*";
//        s = "leetcode";
//        p = "*e*t?d*";

//        s = "";
//        p = "a";

//        s = "b";
//        p = "?*?";
//        s = "baab";
//        p = "*?ab*";
//        s = "hi";
//        p = "*?";
//        Arrays.

        Test44_1 t = new Test44_1();
        t.printNext("?ab");
        System.out.println(t.isMatch(s, p));
    }


    private static final String HEAD_TAG = "^headtag";
    private static final String TAIL_TAG = "tailtag$";
    public boolean isMatch(String s, String p) {
        s = HEAD_TAG + s + TAIL_TAG;
        p = HEAD_TAG + p + TAIL_TAG;
        String[] tempPs = p.split("\\*");
        int num = 0;
        for(String pItem: tempPs) {
            if(pItem.length() != 0) {
                num++;
            }
        }
        char[][] ps = new char[num][];
        int[][] nexts = new int[num][];
        int i = 0;
        for(String pItem: tempPs) {
            if (pItem.length() != 0) {
                ps[i] = pItem.toCharArray();
                nexts[i] = getNext(ps[i++]);
//                i++;
            }
        }
        return isMatch(s.toCharArray(), ps, nexts);
    }

    public boolean isMatch(char[] s, char[][] ps, int[][] nexts){
        int sIndex = 0;
        for(int psIndex = 0;
                psIndex < nexts.length &&
                (sIndex =  kmpMatch(s, ps[psIndex], nexts[psIndex], sIndex)) != -1;
                psIndex++){};
        return sIndex == s.length;
    }


    private int kmpMatch(char[] s, char[] p, int[] next, int start){
        int i = start, j = 0;
        for(; i < s.length && j < p.length;) {
            if(j == -1){ i++; j = 0;}
            if (i < s.length && (p[j] == '?' || s[i] == p[j])) {j++; i++;}
//            else if(matchHead) return -1;
            else { j = next[j];}
        }
        return j == p.length? i: -1;
    }
// abcdabceabcdabcef
    private int[] getNext(char[] pat){
        int[] next = new int[pat.length];
        next[0] = -1;
        int i = 0, j = -1;
        for(; i < pat.length; ){
            if(j == -1 || pat[i] == pat[j] || pat[i] == '?' || pat[j] == '?'){
                i++; j++;
//                if(i >= pat.length) break;
                if(pat[i] == pat[j])
                    next[i] = next[j];
                else
                    next[i] = j;
            }
            else{
                j = next[j];
            }
        }
        return next;
    }

    private void printNext(String s){
        int[] next = getNext(s.toCharArray());
        for(int i : next) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
