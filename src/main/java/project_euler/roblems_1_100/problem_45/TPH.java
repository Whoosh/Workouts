package project_euler.roblems_1_100.problem_45;

import java.util.Set;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=45
 */
public class TPH {

    private final static long LIMIT = 1000000;
    private final static int PRE_T = 285;
    private final static int PRE_P = 165;
    private final static int PRE_H = 144;

    public static void main(String[] args) {
        Set<Long> buffer_T = makeSequence(PRE_T, n -> n * (n + 1) / 2).collect(Collectors.toSet());
        Set<Long> buffer_P = makeSequence(PRE_P, n -> n * (3 * n - 1) / 2).collect(Collectors.toSet());
        System.out.println(
                makeSequence(PRE_H, n -> n * (2 * n - 1))
                        .filter(buffer_P::contains)
                        .filter(buffer_T::contains)
                        .findFirst());
    }

    private static Stream<Long> makeSequence(int starter, LongUnaryOperator function) {
        return LongStream
                .iterate(starter, n -> ++n)
                .map(function)
                .limit(LIMIT)
                .boxed();
    }
}
