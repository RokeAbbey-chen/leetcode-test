package test;

/*
* Determine whether an integer is a palindrome. Do this without extra space.
*
*
* */
public class Test9 {
    public static void main(String[] args){
        Test9 t = new Test9();
        System.out.println(t.isPalindrome(-2147447412));
    }
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int x2 = x;
        int x3 = 0;
        do{
            x3 *= 10;
            x3 += x2 % 10;
            x2 /= 10;
        }while(x2 != 0);
//        System.out.println("x3 = " + x3 + ", x = " + x);
        return x3 == x;
    }
}
