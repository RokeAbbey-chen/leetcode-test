package test;

import java.util.ArrayList;
import java.util.List;
//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//        For example, given n = 3, a solution set is:

public class Test22_1 {

    private int n = 0;
    private List<String> list = new ArrayList<>();
    public static void main(String[] args){
        Test22_1 t = new Test22_1();
        int n = 5;
        n = 3;
        List<String> list = t.generateParenthesis(n);

        System.out.println(list + "\n" + list.size());
    }
    public List<String> generateParenthesis(int n) {
        this.n = n;
        list.clear();
        StringBuilder sb = new StringBuilder();
        generate(0, 0, sb);
        return list;
    }

    private void generate(int left, int right, StringBuilder sb){
        if(left == n && right == n) {
            list.add(sb.toString());
            return ;
        }
        if(left < n) {
            sb.append('(');
            generate(left + 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(left > right && right < n) {
            sb.append(')');
            generate(left, right + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
