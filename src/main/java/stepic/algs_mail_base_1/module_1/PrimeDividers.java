package stepic.algs_mail_base_1.module_1;


import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by whoosh on 12/15/15.
 */

public class PrimeDividers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int checkingValue = scanner.nextInt();
        if (checkingValue < 2) return;
        int[] primes = primes(checkingValue + 1).toArray();
        PriorityQueue<Integer> result = new PriorityQueue<>();
        while (checkingValue != 1) {
            for (int prime : primes) {
                if (prime > checkingValue) break;
                if (checkingValue % prime == 0) {
                    checkingValue = checkingValue / prime;
                    result.add(prime);
                }
            }
        }
        while (!result.isEmpty()) {
            System.out.print(result.poll());
            System.out.print(' ');
        }
    }

    public static IntStream primes(int to) {
        BitSet map = new BitSet();
        map.set(2, to, true);
        long k;
        for (int i = 2; (k = i * i) <= to; i++) {
            for (long j = k; j < to && k < Integer.MAX_VALUE; j += i) {
                map.set((int) j, false);
            }
        }
        return map.stream();
    }
}
