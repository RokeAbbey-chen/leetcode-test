package xiaobai;

import java.util.Scanner;
import java.util.Stack;

public class Test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String line;
        char[] chs;
        for (int i = 0; i < n && input.hasNextLine(); i ++){
            line = input.nextLine();
            chs = line.toCharArray();
//            System.out.println(fun(chs));
        }
        System.out.println(fun("{{()}}".toCharArray()));
        System.out.println(fun("{{(()}}".toCharArray()));
    }

    private static boolean fun(char[] chs){
        Stack<Character> stack = new Stack<>();
        for (char c : chs){
            if (c == '{' || c == '('){
                stack.push(c);
            }
            else if (stack.isEmpty()){
                return false;
            }
            else if (c == '}' && stack.peek() == '{'
                    || c == ')' && stack.peek() == '('){
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
