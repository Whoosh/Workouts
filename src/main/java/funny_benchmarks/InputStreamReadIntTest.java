package funny_benchmarks;

import org.openjdk.jmh.annotations.*;
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
 * Created by whoosh on 12/18/15.
 */

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class InputStreamReadIntTest {

    private static int DATA_LEN = 8000000;
    private static int FROM = 1000000000;
    private static BufferedReader reader;

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(InputStreamReadIntTest.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(10)
                .forks(1)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            System.out.println("Benchmark broken, caught by " + e);
        }
    }

    @Setup
    public void prepareData() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for (int i = 0; i < DATA_LEN; i++) {
            builder.append(FROM + (i & 1) == 0 ? -i : i);
            builder.append(" ");
        }
        ByteArrayInputStream byteBuffer = new ByteArrayInputStream(builder.toString().getBytes());
        reader = new BufferedReader(new InputStreamReader(byteBuffer));
    }


    @Benchmark
    public int first() throws IOException {
        int result = 0;
        for (int i = 0; i < DATA_LEN; i++) result += nextInt(reader);
        return result;
    }

    @Benchmark
    public int second() throws IOException {
        int result = 0;
        for (int i = 0; i < DATA_LEN; i++) result += nextInt2(reader);
        return result;
    }

    @Benchmark
    public int third() throws IOException {
        int result = 0;
        for (int i = 0; i < DATA_LEN; i++) result += nextLong(reader);
        return result;
    }


    private static int nextLong(BufferedReader br) throws IOException {
        try {
            int d, i = 0;
            char[] res = new char[20];
            while ((d = br.read()) == 32) ;
            do {
                res[i++] = (char) d;
            } while (Character.isDigit(d = br.read()));
            return Integer.parseInt(String.valueOf(res, 0, i), 10);
        } catch (Exception ex) {
            return 0;
        }
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
        return l ? -val : val;
    }
}
