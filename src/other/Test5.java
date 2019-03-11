package other;

import java.util.ArrayList;

public class Test5 {
    public static void main(String[] args) {
        int[] inta = {1, 1, 1, 1, 1};
        int[] inta2 = {1, 1, 1, 1, 1};

        System.out.println(inta.hashCode() + "   " + inta2.hashCode());
        inta[0] = inta[1] = 0;
        System.out.println(inta.hashCode() + "   " + inta2.hashCode());

        ArrayList<Integer> a = new ArrayList<>();
        a.hashCode();
    }
}
