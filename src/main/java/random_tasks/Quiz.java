package random_tasks;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

class Quiz {

  private static final int PRIMES_LIMIT = 1_000_000;
  private static final BitSet SIEVE = generateSieve(PRIMES_LIMIT);

  public static void main(String[] args) {
    int number = ThreadLocalRandom.current().nextInt(9);
    System.out.println("Number " + number + " is prime ? = " + isPrime1(number));
    System.out.println("Number " + number + " is prime ? = " + isPrime2(number));
  }

  private static boolean isPrime1(int number) {
    return SIEVE.get(number);
  }

  public static boolean isPrime2(long n) {
    return n > 1 && LongStream.rangeClosed(2, (long) Math.sqrt(n)).noneMatch(x -> n % x == 0);
  }

  private static BitSet generateSieve(int end) {
    BitSet m = new BitSet();
    m.set(2, end, true);
    long k;
    for (int i = 2; (k = i * i) < end; i++) {
      for (int j = (int) k; j < end; j += i) {
        m.set(j, false);
      }
    }
    return m;
  }
}