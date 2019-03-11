package test;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Test$1 t1 = null;
        System.out.println(new Object(){
            public void sayHello(){
                System.out.println("hello");
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }.clone().getClass().getName());
        System.out.println(new Object(){
            public void sayHello(){
                System.out.println("hello");
            }
        }.getClass().getName());
    }
}
