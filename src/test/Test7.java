package test;

/*
* Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Note:
The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.


*
* */
public class Test7 {
    public static void main(String[] args){
        Test7 t = new Test7();
        int num = 10;
        num = 11;
        num = 1234567;
        num = 1234567899;
        num = Integer.MIN_VALUE;
        System.out.println(t.reverse(num));
    }
    public int reverse(int x) {

        long newX = 0;
        do{
            newX *= 10;
            newX += x % 10;
            x /= 10;
        }while(x != 0);
        if(newX > Integer.MAX_VALUE || newX < Integer.MIN_VALUE)
            return 0;
        return (int) newX;
    }
}
