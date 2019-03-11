package xiaobai;

public class Test6 {
    public static void main(String[] args) {
        System.out.println(solve(1, 2, 3, 4, 5));
    }
    public static int solve(int x, int y, int m, int n, int L){
        if (x == y){
            return 0;
        }
        if (m < n && x < y){
            x += L;
        }
        else if (n < m && y < x){
            y += L;
        }

        return gc(Math.abs(x - y), Math.abs(m - n));

    }

    private static int gc(int a, int b){
        return a * b / lc(a, b);
    }
    private static int lc(int a, int b){
        int r = 0;
        if (a < b ){
            int temp = a;
            a = b;
            b = temp;
        }
        do {
            r = a % b;
            a = b;
            b = r;
        } while (r != 0);
        return a;
    }
}
