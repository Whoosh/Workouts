package leetcode.problem_7;

/**
 * Created by whoosh on 11/1/15.
 */
public class Solutiont {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        long m = x;
        m = m < 0 ? m * -1 : m;
        if (m < 10) return x;
        if (m > Integer.MAX_VALUE) return 0;
        long result = 0;
        for (int i = (int) m; i != 0; i /= 10) {
            result += i % 10;
            if (result > Integer.MAX_VALUE) return 0;
            result *= 10;
        }
        result /= 10;
        if (x < 0) result *= -1;
        return (int) result;
    }
}