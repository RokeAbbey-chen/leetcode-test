package test;

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
public class Test44 {
    public static void main(String[] args){
        Test44 t = new Test44();
        String s = "aa";
        String p = "a";

        p = "aa";
        s = "aaa";
        p = "*";
        s = "aa";
        p = "a*";
        s = "ab";
        p = "?*";
        s = "aab";
        p = "c*a*b";
        s = "";
        p = "*****";
        p = "*";
        p = "";
        s = "abefcdgiescdfimde";
        p = "ab*cd?i*de";

        s = "a";
        p = "";

        s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
        p = "a*******b"; //超时
        s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        System.out.println(t.isMatch(s, p));
    }
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();

        return match(str, pat, 0, 0);
    }

    private boolean match(char[] str, char[] pat, int curS, int curP){
        if(curS == str.length){
            int i = 0;
            for(i = curP; i < pat.length && pat[i] == '*'; i++);
            if(i == pat.length)
                return true;
            else return false;
        }

        if(curP == pat.length){
            if(curP > 0 && pat[curP - 1] == '*')
                return true;
            return false;
        }

        if(pat[curP] == '?')
            return match(str, pat, curS + 1, curP + 1);
        else if(pat[curP] == '*'){
            while(curP + 1 < pat.length && pat[curP + 1] == '*') curP ++;
            for(int i = curS; i <= str.length; i++)
                if(match(str, pat, i, curP + 1))
                    return true;
            return false;
        }
        else
            return pat[curP] == str[curS] && match(str, pat, curS + 1, curP + 1);

    }
}
