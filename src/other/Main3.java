package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main3 {
    public boolean judge(int[] arr, int s1, int e1){
        if (e1 <= s1){ return false; }
        return judge(arr,s1, (s1 + e1) >> 1, ((s1 + e1) >> 1) + 1, e1);
    }
    public boolean judge(int[] arr, int s1, int e1, int s2, int e2){
        if (judge(arr, s1, e1) || judge(arr, s2, e2)){
            return true;
        }

        for (int i = s1; i <= e1; i ++){
            for (int j = s2; j <= e2; j ++){
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Main3 m = new Main3();
        final int LEN = 10 * 10000;
        int[] nums = new int[LEN];
        for (int i = 0; i < LEN; i ++){
            nums[i] = LEN - i;
        }
        nums[LEN - 1] = -1;
        long t1 = System.currentTimeMillis();
        boolean result = m.judge(nums, 0, LEN - 1);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(result);

        OUT:
        for (int i = 0; i < LEN; i ++){
            for (int j = 0; j < i; j ++){
                if (nums[i] == nums[j]){
                    result = true;
                    break OUT;
                }
            }
        }
        long t3 = System.currentTimeMillis();
        System.out.println(t3 - t2);
        System.out.println(result);


        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i ++){
            if (nums[i] == nums[i - 1]){
                result = true;
                break;
            }
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3);
        System.out.println(result);

    }
}
