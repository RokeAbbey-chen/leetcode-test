package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
*
* [
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
* */
public class Test22 {
    private static class Parentheses implements Cloneable{
        public Parentheses firstSon;
        public Parentheses brother;
        public void init(int n){
            if(n == 0)
                return;
            this.firstSon = new Parentheses();
            Parentheses tmp = this.firstSon;
            for(int i = 1; i < n; i ++) {
                tmp.brother = new Parentheses();
                tmp = tmp.brother;
            }
        }

        public Queue<Parentheses> getDeepQueue(){
            Queue<Parentheses> q = new LinkedList<>();
            if(this.firstSon != null) {
                q.add(this.firstSon);
                q.addAll(this.firstSon.getDeepQueue());
            }
            if(this.brother != null) {
                q.add(this.brother);
                q.addAll(this.brother.getDeepQueue());
            }
            return q;
        }

        @Override
        public String toString(){
            return "(" + (this.firstSon == null ? "" : this.firstSon.toString()) + ")" +
                    this.brother == null ? "" : this.brother.toString();
        }

        @Override
        public int hashCode(){
            return toString().hashCode();
        }

        @Override
        public Parentheses clone() throws CloneNotSupportedException {
            Parentheses thisClone = (Parentheses) super.clone();
            if(this.firstSon != null)
                thisClone.firstSon = this.firstSon.clone();
            if(this.brother != null)
                thisClone.brother = this.brother.clone();
            return thisClone;
        }
    }
    public List<String> generateParenthesis(int n) {
        Parentheses head = new Parentheses();
        head.init(n);
        Queue<Parentheses> queue = new LinkedList<>();
        try {
            queue.add(head.clone());
            Parentheses node = head.firstSon;
            head.firstSon = head.firstSon.brother;
//            Queue<Parentheses>
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
