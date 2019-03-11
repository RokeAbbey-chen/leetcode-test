package xiaobai;

import java.util.Arrays;
import java.util.Scanner;

//chess
public class Test4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i ++) {
            int N = input.nextInt();
            int M = input.nextInt();
            System.out.println(solve(N, M));
        }
    }
    public static long solve(int N, int M){
        final int MOD = 1000000007;
        if (N > M){
            int temp = N;
            N = M;
            M = temp;
        }
        int result = M - N + 1;
        if (result != 1) {
            result %= MOD;
            for (int i = 1; i < N; i++) {
                result *= (M - N + 1);
                result %= MOD;
            }
        }
        return result;
    }
}
