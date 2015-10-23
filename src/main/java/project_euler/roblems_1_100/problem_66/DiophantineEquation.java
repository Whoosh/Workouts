package project_euler.roblems_1_100.problem_66;


import java.math.BigInteger;

import static java.math.BigInteger.*;

/**
 * https://projecteuler.net/problem=66
 * <p>
 * https://ru.wikipedia.org/wiki/%D0%A3%D1%80%D0%B0%D0%B2%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5_%D0%9F%D0%B5%D0%BB%D0%BB%D1%8F
 * <p>
 * Несложно видеть, что при больших x и y, являющихся решениями уравнения Пелля,
 * отношение x/y должно быть близким к \sqrt{n}. Оказывается, что верно и более сильное утверждение:
 * такая дробь должна быть подходящей дробью для  \sqrt{n};(c)
 * <p>
 * Необходимо приближать наши числитель и знаминатель к каждому корню от D
 * А единственное первое решение и будет максимальным.
 *
 * http://www.ams.org/notices/200202/fea-lenstra.pdf англ версия
 * http://www.mccme.ru/free-books/mmmf-lectures/book.13.pdf русская версия
 *
 * В лоб задача вообще, помоему не решается.
 *
 * Ещё http://en.wikipedia.org/wiki/Chakravala_method
 */

public class DiophantineEquation {

    public static void main(String[] args) {
        int dResult = 0;
        BigInteger xResult = ONE, sqrt;
        BigInteger x, x1, x2;
        BigInteger y, y1, y2;
        BigInteger m, d, a;
        for (int mainD = 2; mainD <= 1000; mainD++) {
            sqrt = valueOf((long) Math.sqrt(mainD));
            if (pow(sqrt).equals(valueOf(mainD))) continue;

            m = ZERO;
            d = ONE;
            a = sqrt;

            x1 = ONE;
            x = a;
            y1 = ZERO;
            y = ONE;

            while (!formula(x, y, mainD)) {
                x2 = x1;
                x1 = x;
                y2 = y1;
                y1 = y;

                m = d.multiply(a).subtract(m);
                d = (valueOf(mainD).subtract(pow(m))).divide(d);
                a = (sqrt.add(m)).divide(d);

                x = a.multiply(x1).add(x2);
                y = a.multiply(y1).add(y2);
             }
            if (xResult.compareTo(x) < 0) {
                xResult = x;
                dResult = mainD;
            }
        }
        System.out.println(dResult);
    }

    public static boolean formula(BigInteger x, BigInteger y, int d) {
        return pow(x).subtract(pow(y).multiply(valueOf(d))).equals(valueOf(1));
    }

    public static BigInteger pow(BigInteger a) {
        return a.multiply(a);
    }
}
