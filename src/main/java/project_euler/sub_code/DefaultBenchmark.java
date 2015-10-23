package project_euler.sub_code;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class DefaultBenchmark {
    public static void run(Class<?> clazz){
        Options opt = new OptionsBuilder()
                .include(clazz.getSimpleName())
                .warmupIterations(2)
                .measurementIterations(5)
                .forks(1)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            System.out.println("Benchmark broken, caught by "+e);
        }
    }
}
