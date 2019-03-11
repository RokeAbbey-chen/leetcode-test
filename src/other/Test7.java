package other;

import java.util.Scanner;

public class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        String sign;
        num = scanner.nextInt();
        sign = scanner.next();
        StringBuilder sb = new StringBuilder();


        int an =(int)(Math.sqrt((((num + 1) >> 1 ) << 2) + 1) + 0.05);
        System.out.println(an);
        int outputed = 0;
        for (int i = 0, target = 1; target <= ((num + 1)>> 1) - outputed ; i ++){
            for (int j = 0; j < target; j ++){
                sb.append(sign);
            }
            sb.append('\n');
            outputed += target;
            target += 2;
        }
        System.out.print(sb.reverse());
//        String[] strs = sb.toString().split("\n");
    }
}
