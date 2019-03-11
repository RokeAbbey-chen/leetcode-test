package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*
* */
public class Test17 {
    public static void main(String[] args){
        Test17 t = new Test17();
        String digits = "2349";
        digits = "99";
        digits = "7";
        digits = "678";
        System.out.println(t.letterCombinations(digits));
    }
    public List<String> letterCombinations(String digits) {
        char[] chs = digits.toCharArray();
        List<StringBuffer> result = new ArrayList<>();


        int size = 1;//result.size();
        for(int i = 0; i < chs.length; i++){
            int count = 3;
            if(chs[i] == '9' || chs[i] == '7')
                count = 4;

            for(int j = i == 0 ? 0 : size;
                j < size * count;
                j++)
                result.add(new StringBuffer());

            size = result.size();
        }

        size = result.size();

//        System.out.println("size = " + size);
        int step = 1;
        for(int i = chs.length - 1; i >= 0; i--){
            char[] chs2 = map.get(chs[i]);
            int mod = chs[i] == '9' || chs[i] == '7' ? 4 : 3;
            for(int j = 0; j < size; j ++)
                result.get(j).append(chs2[ j / step % mod]);
            step *= mod;
        }

        List<String> result2 = new ArrayList<>();
        for(int i = 0;i < size; i++)
            result2.add(result.get(i).reverse().toString());
        return result2;
    }

    private Map<Character, char[]> map = new HashMap<>();
    {
        for(int i = 2; i <= 6; i++)
            map.put((char)('0' + i), new char[]{(char)('a' + 3 * (i - 2)), (char)('b' + 3 * (i - 2)), (char)('c' + 3 * (i - 2))});
        map.put('7', "pqrs".toCharArray());
        map.put('8', "tuv".toCharArray());
        map.put('9', "wxyz".toCharArray());
    }

}
