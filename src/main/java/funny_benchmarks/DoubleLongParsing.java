package funny_benchmarks;

import com.google.common.primitives.Doubles;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Istomin Alexander
 * @since 04/03/2018
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DoubleLongParsing {

  private static List<String> source;

  static {
    int size = 1_500_000;
    source = new ArrayList<>(size);
    source.addAll(new Random().doubles(0, 50).limit(size / 3)
      .mapToObj(String::valueOf)
      .collect(Collectors.toList()));
    source.addAll(new Random().ints(size, 0, 10_000_000)
      .mapToObj(String::valueOf).collect(Collectors.toList()));
  }

  public double doubleFirst(List<String> source) {
    double i = 0;
    for (String s : source) {
      Double d = Doubles.tryParse(s);
      if (d != null && (((d % 1) != 0) || s.contains("."))) {
        i += d;
      }
      else {
        i += Long.valueOf(s);
      }
      i++;
    }
    return i;
  }

  public double longFirst(List<String> source) {
    double i = 0;
    for (String s : source) {
      try {
        i += Long.valueOf(s);
      }
      catch (Exception ex) {
        i += Double.valueOf(s);
      }
      i++;
    }
    return i;
  }

  public double withDotCheck(List<String> source) {
    double i = 0;
    for (String s : source) {
      if (s.contains(".") || s.contains("E") || s.contains("e")) {
        i += Double.valueOf(s);
        continue;
      }
      try {
        i += Long.valueOf(s);
      }
      catch (Exception ex) {
        i += Double.valueOf(s);
      }
      i++;
    }
    return i;
  }

  public double withDotCheckPlusGuava(List<String> source) {
    double i = 0;
    for (String s : source) {
      if (s.contains(".") || s.contains("E") || s.contains("e")) {
        Double aDouble = Doubles.tryParse(s);
        if (aDouble != null) {
          i += aDouble;
          continue;
        }
      }
      try {
        i += Long.valueOf(s);
      }
      catch (Exception ex) {
        i += Double.valueOf(s);
      }
      i++;
    }
    return i;
  }

  public double alwaysAsDouble(List<String> source) {
    double i = 0;
    for (String s : source) {
      i += Double.valueOf(s);
    }
    return i;
  }

  public double withDotCheckWithoutEx(List<String> source) {
    double i = 0;
    for (String s : source) {
      if (s.contains(".") || s.contains("E") || s.contains("e")) {
        i += Double.valueOf(s);
      }
      else {
        i += Long.valueOf(s);
      }
      i++;
    }
    return i;
  }

  public static void main(String[] args) {
    Options opt = new OptionsBuilder()
      .include(DoubleLongParsing.class.getSimpleName())
      .warmupIterations(20)
      .measurementIterations(20)
      .forks(1)
      .build();
    try {
      new Runner(opt).run();
    }
    catch (RunnerException e) {
      System.out.println("Benchmark broken, caught by " + e);
    }
  }

  @Benchmark
  public static double withDotCheck() {
    return new DoubleLongParsing().withDotCheck(source);
  }

  @Benchmark
  public static double longFirst() {
    return new DoubleLongParsing().longFirst(source);
  }

  @Benchmark
  public static double doubleFirst() {
    return new DoubleLongParsing().doubleFirst(source);
  }

  @Benchmark
  public static double withDotCheckPlusGuava() {
    return new DoubleLongParsing().withDotCheckPlusGuava(source);
  }

  @Benchmark
  public static double alwaysAsDouble() {
    return new DoubleLongParsing().alwaysAsDouble(source);
  }

  @Benchmark
  public static double withDotCheckWithoutEx() {
    return new DoubleLongParsing().withDotCheckWithoutEx(source);
  }
}
