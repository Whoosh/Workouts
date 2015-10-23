package project_euler.roblems_1_100.problem_60;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.Primes;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=60
 * // FIXME, все брутфорсят по разному на форуме, быстрее можно только если есть много memory. // этот брутфорс ~ 10-20сек
 */
public class PrimePair {

    private static final int N = 19000;
    private static final int LAST = 199999991;

    private static List<Integer> possiblyPrimes = Primes
            .getPrimes(N)
            .boxed()
            .collect(Collectors.toList());
    private static HashSet<Integer> allAvailablePrimes = Primes
            .getPrimes(N * 10000)
            .boxed()
            .collect(Collectors.toCollection(HashSet::new));

    public static void main(String[] args) {
        Element buffer;
        Element bufferChecker = new Element();
        final HashSet<Element> badPairs = new HashSet<>(N);
        final HashSet<Element> goodPairsOne = new HashSet<>(N);
        final HashSet<Element> goodPairsTwo = new HashSet<>(N);
        long start = System.nanoTime();
        for (int i = 1; i < 8; i++) {
            for (int j = i + 1; j < possiblyPrimes.size(); j++) {
                buffer = new Element(possiblyPrimes.get(i), possiblyPrimes.get(j));
                if (isPlentyOfConcatPrime(buffer.getSrc())) goodPairsOne.add(buffer);
                else badPairs.add(buffer);
            }
        }
        System.out.println("Stage One Done");
        for (Element goodPair : goodPairsOne) {
            for (Integer possiblyPrime : possiblyPrimes) {
                if (goodPair.getSrc()[1] > possiblyPrime) continue;
                bufferChecker.setVal(possiblyPrime, 1);
                bufferChecker.setVal(goodPair.getSrc()[1], 0);
                if (!badPairs.contains(bufferChecker)) {
                    buffer = new Element(goodPair.getSrc()[0], goodPair.getSrc()[1], possiblyPrime);
                    if (isPlentyOfConcatPrime(buffer.getSrc())) goodPairsTwo.add(buffer);
                }
            }
        }
        System.out.println("Stage Two Done");
        goodPairsOne.clear();
        for (Element goodPair : goodPairsTwo) {
            for (Integer possiblyPrime : possiblyPrimes) {
                if (goodPair.getSrc()[1] > possiblyPrime || goodPair.getSrc()[2] > possiblyPrime) continue;
                bufferChecker.setVal(possiblyPrime, 1);
                bufferChecker.setVal(goodPair.getSrc()[2], 0);
                if (!badPairs.contains(bufferChecker)) {
                    buffer = new Element(goodPair.getSrc()[0], goodPair.getSrc()[1], goodPair.getSrc()[2], possiblyPrime);
                    if (isPlentyOfConcatPrime(buffer.getSrc())) goodPairsOne.add(buffer);
                }
            }
        }
        System.out.println("Stage Three Done");
        goodPairsTwo.clear();
        for (Element goodPair : goodPairsOne) {
            for (Integer possiblyPrime : possiblyPrimes) {
                if (goodPair.getSrc()[1] > possiblyPrime
                        || goodPair.getSrc()[2] > possiblyPrime
                        || goodPair.getSrc()[3] > possiblyPrime) continue;
                bufferChecker.setVal(possiblyPrime, 1);
                bufferChecker.setVal(goodPair.getSrc()[2], 0);
                if (!badPairs.contains(bufferChecker)) {
                    buffer = new Element(goodPair.getSrc()[0], goodPair.getSrc()[1],
                            goodPair.getSrc()[2], goodPair.getSrc()[3], possiblyPrime);
                    if (isPlentyOfConcatPrime(buffer.getSrc())) goodPairsTwo.add(buffer);
                }
            }
        }
        System.out.println("Stage Four Done");
        for (Element result : goodPairsTwo) {
            System.out.println(result + " " + result.sum());
        }
        long end = System.nanoTime();
        System.out.println("Meaningless and ruthless nanoTime is - " + (end-start)/1000/1000/1000 + " s");
    }

    private static boolean isPlentyOfConcatPrime(int[] primes) {
        for (int i = 0; i < primes.length; i++) {
            for (int j = i + 1; j < primes.length; j++) {
                if (primes[i] == 0 || primes[j] == 0) break;
                final int c1 = concat(primes[i], primes[j]);
                if (c1 <= LAST) {
                    if (!allAvailablePrimes.contains(c1)) return false;
                    final int c2 = concat(primes[j], primes[i]);
                    if (!allAvailablePrimes.contains(c2)) return false;
                } else {
                    if (!CustomMathFunctions.isPrime(c1)) return false;
                    final int c2 = concat(primes[j], primes[i]);
                    if (!CustomMathFunctions.isPrime(c2)) return false;
                }
            }
        }
        return true;
    }

    private static int concat(int p, int p2) {
        int result = p;
        for (long i = p2; i > 0; i /= 10) result *= 10;
        return result + p2;
    }
}
