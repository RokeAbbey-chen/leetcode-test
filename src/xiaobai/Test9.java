package xiaobai;

import java.util.Scanner;

public class Test9 {

    private static int[] pre;
    private static int[] mid;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
            int T = input.nextInt();
            int[] p = new int[T];
            int[] m = new int[T];
            for (int i = 0; i < T; i++) {
                p[i] = input.nextInt();
            }
            for (int i = 0; i < T; i++) {
                m[i] = input.nextInt();
            }
//            m[i] = input.nextInt();
            pre = p;
            mid = m;
            sb = new StringBuilder();
            solve(0, pre.length - 1, 0, mid.length - 1);
            if (sb.length() > 0){
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.print(sb.toString());
        }
    }
    public static void solve(int prestart, int preend, int midstart, int midend){
        if (preend < prestart || midend < midstart){ return; }
        int root = pre[prestart];
        int rIndex = search(mid, root, midstart, midend);
        solve(prestart + 1, prestart + rIndex - midstart, midstart, rIndex - 1);
        solve(preend - (midend - rIndex - 1), preend, rIndex + 1, midend);
        sb.append(root);
        sb.append(' ');
    }

    private static int search(int[] ar, int target, int start, int end){
        for (int i = start; i <= end; i ++){
            if (ar[i] == target){
                return i;
            }
        }
        return -1;
    }
}
