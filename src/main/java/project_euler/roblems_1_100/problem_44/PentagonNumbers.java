package project_euler.roblems_1_100.problem_44;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=44
 */
public class PentagonNumbers implements Starter {

    private static final long N = 10000L;
    private static List<Long> pentagonals;
    private static Set<Long> buffer;

    public static void main(String[] args) {
        pentagonals = LongStream
                .iterate(1, x -> ++x)
                .limit(N)
                .map(n -> n * (3 * n - 1) / 2)
                .boxed()
                .collect(Collectors.toList());
        buffer = new HashSet<>(pentagonals);
        new PentagonNumbers().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        System.out.println(find());
    }

    private static Long find() {
        final int size = pentagonals.size();
        Optional<Long> result;
        for (int i = 0; i < size; i++) {
            final Long pI = pentagonals.get(i);
            result = IntStream
                    .range(i + 1, size)
                    .boxed()
                    .map(pentagonals::get)
                    .filter(pJ -> buffer.contains(pJ + pI) && buffer.contains(pJ - pI))
                    .findFirst();
            if (result.isPresent()) return result.get() - pI;
        }
        return 0L;
    }
}
