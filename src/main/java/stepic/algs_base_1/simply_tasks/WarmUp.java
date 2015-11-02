package stepic.algs_base_1.simply_tasks;

/**
 * Created by whoosh on 11/2/15.
 */

public class WarmUp {

    public static void main(String[] args) {
        for (int i = 1; i <= 40; i++)
            System.out.println(fi(i));
    }

    private static int fi(int n) {
        int a = 0, b = 1, c;
        while (--n > 1) {
            c = b;
            b = a + b;
            a = c;
        }
        return a + b;
    }
}
