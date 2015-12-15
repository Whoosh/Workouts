package stepic.algs_csc_base_1.module_4;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by whoosh on 13/11/15.
 */

public class InvCalc {

    private static HashMap<Integer, Integer> result = new HashMap<>(1000000);
    private static HashMap<Integer, Integer> lens = new HashMap<>(1000000);

    public static void main(String[] args) throws IOException {
        int value = nextInt();
        result.put(1, 0);
        result.put(2, 1);
        result.put(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Integer a, b, c;
        Integer al, bl, cl;
        HashMap<Integer, Integer> inner = new HashMap<>();
        for (int i = 2; i <= value; i++) {
            a = tree(i);
            b = two(i);
            c = i - 1;
            if (lens.containsKey(a)) al = lens.get(a);
            else al = calcLen(a);
            if (lens.containsKey(b)) bl = lens.get(b);
            else bl = calcLen(b);
            if (lens.containsKey(c)) cl = lens.get(c);
            else cl = calcLen(c);
            inner.put(al, a);
            inner.put(bl, b);
            inner.put(cl, c);
            result.put(i, inner.get(Math.min(al, Math.min(bl, cl))));
        }
        Integer next = result.get(value);
        ArrayDeque<Integer> reverse = new ArrayDeque<>();
        while (next != 0) {
            reverse.addFirst(next);
            next = result.get(next);
        }
        System.out.println(reverse.size());
        for (Integer v : reverse) {
            System.out.print(v);
            System.out.print(' ');
        }
        System.out.println(value);
    }

    private static Integer calcLen(Integer startPoint) {
        int len = 0;
        Integer val = result.get(startPoint);
        while (val != null) {
            val = result.get(val);
            len++;
            if (val != null && val.equals(Integer.MAX_VALUE)) {
                len = Integer.MAX_VALUE;
                break;
            }
        }
        lens.put(startPoint, len);
        return len;
    }

    private static int tree(int val) {
        return val % 3 == 0 ? val / 3 : Integer.MAX_VALUE;
    }

    private static int two(int val) {
        return (val & 1) == 0 ? val >> 1 : Integer.MAX_VALUE;
    }

    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        boolean sign = false;
        do {
            if (d == '-') {
                sign = true;
                continue;
            }
            val += d - 48;
            val *= 10;
        } while ((d = System.in.read()) > 47 && d < 58);
        if (sign) val *= -1;
        return val / 10;
    }
}
