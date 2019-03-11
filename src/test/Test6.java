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

*
* */
public class Test6 {

    public static void main(String[] args){
        String s = "abcdefghijklmn";
        int rowNum = 5;
        Test6 t = new Test6();
        s = "";
        s = "abc";
        System.out.print(t.convert(s, rowNum));
    }

    private static class Node implements Comparable<Node>{
        Character value;
        Integer row;
        Integer col;

        public Node(Character value, Integer row, Integer col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            int result = row.compareTo(o.row);
            if(result == 0)
                return col.compareTo(o.col);
            return result;
        }
    }

    public String convert(String s, int numRows) {
        char[] chs = s.toCharArray();
        Node[] nodes = new Node[chs.length];
        int row = 0;
        int col = 0;
        boolean flag = true; //true代表行增长, 列不动, false代表行递减,列增长
        for(int i = 0; i < chs.length; i++){
            nodes[i] = new Node(chs[i], row, col);
            if(flag && row == numRows - 1)
                flag = false;
            if(!flag && row == 0)
                flag = true;
            if(flag)
                row ++;
            else {
                row --;
                col ++;
            }
        }

        Arrays.sort(nodes);
        StringBuffer sb = new StringBuffer();
        for(Node node : nodes)
            sb.append(node.value);
        return sb.toString();
    }
}
