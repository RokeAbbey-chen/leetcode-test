package test;

/*
* Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*
* */
public class Test10 {
    public static void main(String[] args){
        Test10 t = new Test10();
        String s = "abcdef";
        String p = "abc.ef";
        p = "abc.e.*a*b*";
        p = "h*z*a*b*c*.*.ef";
        p = "...*.*...";
        p = "";
        s = "";

        s = "aa";
        p = "a";
        p = "a*";
        p = "aaaa";

        s = "ab";
        p = ".*c";
        System.out.println(t.isMatch(s, p));
    }
    public boolean isMatch(String s, String p) {
        return isMatch(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    public boolean isMatch(char[] s, char[] p, int sStart, int pStart){

        if(pStart >= p.length && sStart >= s.length)
            return true;

        if(pStart >= p.length && sStart < s.length)
            return false;

        if(sStart >= s.length)
        {
            if(pStart + 1 > p.length)
                return false;
            int i = 0;
            for(i = pStart; i + 1 < p.length; i += 2)
                if(p[i + 1] != '*')
                    return false;
            if(i == p.length - 1)
                return false;
            return true;

        }
//        p = "h*z*a*bc.*.ef";
        char[] pa = new char[]{p[pStart], 0};
        if(pStart + 1 < p.length)
            pa[1] = p[pStart + 1];
        for(int i = sStart; i < s.length; i++){
            int d = match(pa, s[i]);
//            System.out.println("s[" + i + "] = " + s[i] + ", pa[0] = " + pa[0] + ", p[1] = " + pa[1]);
//            System.out.println("pStart = " + pStart);
            if(d != 0){
                boolean flag = isMatch(s, p, i + (2 - d), pStart + d);
                if(flag)
                    return true;
                else if(d == 2)
                    continue;
                else
                    return false;

            }
            else{
                if(pa[1] != '*')
                    return false;
                pStart += 2;
                updatePa(pa, p, pStart);
                i--;
            }
        }
        System.out.println("line 91");
        return isMatch(s, p, s.length, pStart);
    }
    public void updatePa(char[] pa, char[] p, int pStart){
        if(pStart < p.length)
            pa[0] = p[pStart];
        if(pStart + 1 < p.length)
            pa[1] = p[pStart + 1];
        else pa[1] = 0;
    }
    public int match(char[] pa, char c){
        if((pa[0] == c || pa[0] == '.') && pa[1] == '*')
            return 2;
        return pa[0] == c || pa[0] == '.'? 1: 0;
    }
}
