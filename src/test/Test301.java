package test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/*
* Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
Credits:
Special thanks to @hpplayer for adding this problem and creating all test cases.
*
* */
/*
*
* 这个方法的思路其实还是很麻烦，所以没有实现. 这个的思路是这样的:
*   首先把string 开头的））））全部移除， 然后开始分段解决，
*   定义"一段"：
*       段起点： 初始为0
*       段终点： 从段起点开始， 在满足）比（多的情况下， 连续的最后一个）的坐标。 比如(((a)b)(cde))))(a 段终点就是15，因为在13处已经满足)>=(了，然后15处是那一片)的最后一个
*           这个时候下一段的段起点就是16（如果中间有字母的话再说）
*   各个段可以各自修整，得到一个list<string>, 所有的段的list<string>做笛卡尔积即可
*
*   理论上可行 ，但是写起来很麻烦， 而且没省多少时间，反而话费了很多空间
* */
public class Test301 {
    public List<String> removeInvalidParentheses(String s) {
        int index1 = 0; //第一个左括号的起始下标
        StringBuffer sb = new StringBuffer();
        char[] chs = s.toCharArray();
        int i;
        for(i = 0; i < chs.length && chs[i] != '('; i++)
            if(chs[i] != ')')
                sb.append(chs[i]);

        index1 = i;
        s = new String(chs, index1, chs.length);
        return null;
    }
    private List<String> func(String s){
        char[] chs = s.toCharArray();
        while(true){
            StringBuffer part = new StringBuffer();
            TreeMap<Integer, Integer[]> chmap = new TreeMap<>();
            boolean reverseFlag = false; //true表示进行过reverse, false没有
            int partStart = 0;
            int partEnd = 0; //不包含最后一个
            int newPartEnd = partEnd;

            int leftNum = 0; //记录在一段中左括号出现的次数
            int rightNum = 0; //记录在一段中有括号出现的次数
            StringBuffer sb = new StringBuffer();
            Integer[] curNum = {0, 0}; // 0号为（的数量， 1号为）的数量
        L1: for(; newPartEnd < chs.length; newPartEnd ++){
                sb.append(chs[newPartEnd]);
                switch (chs[newPartEnd]){
                    case '(':
                        if(rightNum >= leftNum && newPartEnd != partEnd)
                            break L1;
                        curNum[0] ++;
                        leftNum ++;
                        break;

                    case ')':
                        curNum[1] ++;
                        rightNum ++;
                        break;

                    default:
                        chmap.put(newPartEnd, curNum);
                        curNum = new Integer[]{0, 0};
                        break;
                }
            }
            if(leftNum > rightNum){
                reverseFlag = true;
                sb.reverse();
            }

            int diff = rightNum - leftNum;


        }
    }
    public List<String> rm(TreeMap<Integer, Integer[]> chmap, int target, int n, boolean reverseFlag){ //  n代表n个数字相加之和等于target
        if(n == 0){
            if(target == 0){
                Set<Integer> keys = chmap.keySet();

            }
        }
        return null;
    }

}
