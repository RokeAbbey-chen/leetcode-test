package xiaobai;

/*
* Problem: A/B
* */
public class Test5 {
    public static void main(String[] args){
        int n = 1000;
        int B = 53;

        n = 87;
        B = 123456789;
        System.out.println(solve(n, B));
    }
    public static int solve(int n, int B){
        int A = 0;
        for (int i = 1; ;i ++ ){
            A += B;
            A %= 9973;
            if (A == n){
                return i;
            }
        }
    }
}
