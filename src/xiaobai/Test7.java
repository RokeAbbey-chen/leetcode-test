package xiaobai;

import java.util.Arrays;

//果汁
public class Test7 {
    public static void main(String[] args) {
        int[] frs = {1, 2, 9};
        System.out.println(solve(frs));
    }
    public static int solve(int[] frs){
        if (frs.length <= 1){
            return 0;
        }
        Arrays.sort(frs);
        int result = frs[0] + frs[1];
        for (int i = 2; i < frs.length; i ++){
            result <<= 1;
            result += frs[i];
        }
        return result;
    }
}
