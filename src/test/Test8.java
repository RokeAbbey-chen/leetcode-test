package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.


*
* */
public class Test8 {
    public static void main(String[] args){
        Test8 t = new Test8();

        String str = "12345abcde1000";
        str = "12345";
        str = "    +1234abd100";
        str = "9223372036854775809";
        str = "-2147483647";
        System.out.println(t.myAtoi(str));
    }
    public int myAtoi(String str) {
        if(str == null)
            return 0;
        str = str.trim();
        if(str.length() == 0)
            return 0;

        Pattern pattern = Pattern.compile("^(?:\\+|-)?\\d+");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            if(matcher.end() - matcher.start() > 11)
                return str.charAt(0) == '-'? Integer.MIN_VALUE: Integer.MAX_VALUE;
            long value = 0;
            try {
                value = Long.parseLong(str.substring(matcher.start(), matcher.end()));
                if(value > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                else if(value < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
            }catch (NumberFormatException e){
                return 0;
            }

            return (int) value;
        }
        return 0;
    }
}
