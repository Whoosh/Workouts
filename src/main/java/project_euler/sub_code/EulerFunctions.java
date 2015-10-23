package project_euler.sub_code;


import java.util.Collection;
import java.util.Iterator;

public class EulerFunctions {

    private Collection<Integer> primes;

    public EulerFunctions(Collection<Integer> primes) {
        this.primes = primes;
    }

    public int totient(int x) {
        return phi(x);
    }

    private int phi(int n) {
        if (primes.contains(n)) return n - 1;
        if ((n & 1) == 0) {
            final int m = n >> 1;
            return (m & 1) == 0 ? phi(m) << 1 : phi(m);
        }
        int p, o, d;
        for (Iterator<Integer> iterator = primes.iterator(); iterator.hasNext() && (p = iterator.next()) <= n; ) {
            if (n % p != 0) continue;
            o = n / p;
            d = CustomMathFunctions.gcd(p, o);
            return d == 1 ? phi(p) * phi(o) : phi(p) * phi(o) * d / phi(d);
        }
        return 0;
    }
}
