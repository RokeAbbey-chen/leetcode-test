package xiaobai;

public class Test12 {


    public static void test1(String str, int b){
        String a = new String("aaaa");
        String c = a + "aaa";
        System.out.println("aaaa" == str);
        System.out.println("aaaa" == a);
        System.out.println("aaaa" == a.intern());
    }

    public static void main(String[] args) {
        Test12.test1("aaaaaaaa", 1);
    }
}
