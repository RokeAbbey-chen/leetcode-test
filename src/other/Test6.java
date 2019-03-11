package other;

public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i ++){
            a(i);
            Thread.sleep(1000);
        }
    }

    private static void a(int num){
        final int b = num;
        new Thread(()->{
            try {
                for (int i = 0; i < 5; i ++) {
                    System.out.println(b);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
