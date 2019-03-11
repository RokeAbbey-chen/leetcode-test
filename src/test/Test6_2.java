package test;

import java.util.Arrays;

/*
* he string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
* (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

//题目意思看不懂的话可以可以看chrome书签中题文件夹中相关部分的解释
*
* */
public class Test6_2 {

    public static void main(String[] args){
        String s = "abcdefghijklmn";
        Test6_2 t = new Test6_2();
//        s = "";
//        s = "abc";

        int rowNum = 5;
        rowNum = 1;
        rowNum = 0;
        System.out.print(t.convert(s, rowNum));
    }


    public String convert(String s, int numRows) {
        char[] chs = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int row = 0; row < numRows; row ++){

            boolean flag = true; //true代表穿越的是v字形状; false代表穿越的是^形
            int step = 1;
            for(int j = row; j < chs.length ; j += step) {
                sb.append(chs[j]);
                step = flag ? numRows - 1 - row << 1 : row << 1;
                if (step == 0) { //当step == 0为true的时候, 就说明现在肯定是位于最顶端或者最低端, 所以需要换一种走路方式
                    flag = !flag;
                    step = flag ? numRows - 1 - row << 1 : row << 1;
                    if(step == 0) //当step == 0再次为true的时候, 就说明rowNum肯定为1,那么step就设置为1
                        step = 1;
                }
                flag = !flag;
            }
        }
        return sb.toString();
    }
}
