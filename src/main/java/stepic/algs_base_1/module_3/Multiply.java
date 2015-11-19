package stepic.algs_base_1.module_3;

/**
 * Created by whoosh on 11/19/15.
 */
public class Multiply {

    public static void main(String[] args) {
        System.out.println(multiply(9, 5));
        System.out.println(right(15, 2));
        System.out.println(left(12, 2));
        System.out.println(mKaratsuba(9,5));
    }

    private static int multiply(int x, int y) {
        if (y == 0) return 0;
        int z = multiply(x, y / 2);
        return (z & 1) == 1 ? z + z : x + z + z;
    }

    private static int mKaratsuba(int x, int y) {

        int n = maxSize(x, y);

        if (n == 1) return x * y;

        int xL = left(x, n / 2 + 1);
        int xR = right(x, n / 2);
        int yL = left(y, n / 2 + 1);
        int yR = right(y, n / 2);

        int r1 = mKaratsuba(xL, yL);
        int r2 = mKaratsuba(xR, yR);
        int r3 = mKaratsuba(xL + xR, yL + yR);

        return r1 * (int) Math.pow(2, 2 * n / 2) + (r3 - r1 - r2) * (int) Math.pow(2, 2 * n / 2) + r2;
    }

    private static int right(int val, int n) {
        int dv = 0;
        for (; n > 0; n--, dv += val & 1, val >>= 1) dv <<= 1;
        return dv;
    }

    private static int left(int val, int n) {
        for (; n > 0; n--, val >>= 1) ;
        return val;
    }

    private static int maxSize(int x, int y) {
        int dx = 0, dy = 0;
        for (; x > 0; x = x / 10) dx++;
        for (; y > 0; y = y / 10) dy++;
        return dx > dy ? dx : dy;
    }
}
