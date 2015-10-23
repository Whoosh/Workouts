package project_euler.roblems_1_100.problem_19;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=19
 */
public class CountingSundays implements Starter {

    public static void main(String[] args) {
        new CountingSundays().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        System.out.println(LongStream.rangeClosed(1901, 2000)
                .map(x -> LongStream.rangeClosed(1, 12)
                        .filter(y -> LocalDate.of((int) x, (int) y, 1).getDayOfWeek() == DayOfWeek.SUNDAY).count())
                .sum());
    }

}
