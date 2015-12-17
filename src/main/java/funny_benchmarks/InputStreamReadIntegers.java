package funny_benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by whoosh on 12/17/15.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class InputStreamReadIntegers {

    private static int DATA_LEN = 5000000;

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(InputStreamReadIntegers.class.getSimpleName())
                .warmupIterations(2)
                .measurementIterations(10)
                .forks(1)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            System.out.println("Benchmark broken, caught by " + e);
        }
    }

    private BufferedReader prepareData() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for (int i = 0; i < DATA_LEN; i++) {
            builder.append((i & 1) == 0 ? i * -1 : i);
            builder.append(" ");
        }
        ByteArrayInputStream byteBuffer = new ByteArrayInputStream(builder.toString().getBytes());
        return new BufferedReader(new InputStreamReader(byteBuffer));
    }


    @Benchmark
    public void first() throws IOException {
        BufferedReader reader = prepareData();
        int result = 0;
        for (int i = 0; i < DATA_LEN; i++) {
            result += nextInt(reader);
        }
        System.out.println(result);
    }

    @Benchmark
    public void second() throws IOException {
        BufferedReader reader = prepareData();
        int result = 0;
        for (int i = 0; i < DATA_LEN; i++) {
            result += nextInt2(reader);
        }
        System.out.println(result);
    }

    private static int nextInt2(BufferedReader br) throws IOException {
        int d;
        while ((d = br.read()) == 32) ;
        boolean flag = d == 45 ? true : false;
        int val = flag ? Character.getNumericValue(d = br.read()) : Character.getNumericValue(d);
        while (Character.isDigit(d = br.read())) {
            val *= 10;
            val += Character.getNumericValue(d);
        }
        return flag ? -val : val;
    }

    private static int nextInt(BufferedReader br) throws IOException {
        int d;
        int val = 0;
        while ((d = br.read()) == 32) ;
        boolean l = false;
        if (d == 45) {
            l = true;
            d = br.read();
        }
        do {
            val += d - 48;
            if ((d = br.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return l ? val * -1 : val;
    }
}
