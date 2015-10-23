package project_euler.roblems_1_100.problem_62;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static project_euler.sub_code.CustomMathFunctions.longToArray;
import static project_euler.sub_code.CustomMathFunctions.toLongValue;

/**
 * https://projecteuler.net/problem=62
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CubicPermutation {

    private static final int LIMIT = 20000;
    private static HashMap<Long, Integer> sortedCubic = new HashMap<>(LIMIT);
    private static ArrayList<Long> cubic = new ArrayList<>(LIMIT);

    public static void main(String[] args) {
        DefaultBenchmark.run(CubicPermutation.class);
    }

    @Benchmark
    public static void run() {
        Long buffer = 0l;
        for (long i = 1000; i < LIMIT; i++) {
            buffer = pow(i);
            cubic.add(buffer);
            buffer = sortVal(buffer);
            if (!sortedCubic.containsKey(buffer)) sortedCubic.put(buffer, 1);
            else sortedCubic.put(buffer, sortedCubic.get(buffer) + 1);
        }
        for (Map.Entry<Long, Integer> entry : sortedCubic.entrySet()) {
            if (entry.getValue() == 5) {
                buffer = entry.getKey();
                break;
            }
        }
        for (Long cub : cubic) {
//            if (sortVal(cub) == (buffer)) //System.out.println(cub);
        }
    }

    public static long sortVal(long val) {
        final Integer[] digits = longToArray(val);
        Arrays.sort(digits, (x, y) -> y - x);
        return toLongValue(digits);
    }

    public static long pow(long i) {
        return i * i * i;
    }
}

