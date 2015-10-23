package project_euler.roblems_1_100.problem_14;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;
import project_euler.sub_code.TakeWhile;

import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=14
 */

public class CollatzProblem implements Starter {

    private static final long SEED = 1000000;

    public static void main(String[] args) throws Exception {
        new CollatzProblem().start();
    }

    @StartWithLazzyBenchmark
    public void calculate() {
        long index = 0, result = 0;
        for (long i = SEED, b; i > 0; --i) {
            b = TakeWhile.longTakeWhileOrdered(LongStream.iterate(i, x -> (x & 1) > 0 ? x * 3 + 1 : x / 2), x -> x != 1).count() + 1;
            if (result < b) {
                result = b;
                index = i;
            }
        }
        System.out.println(index + " " + result);
    }
}
