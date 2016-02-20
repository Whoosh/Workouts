package leetcode.problems_0_100.problem_5;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by whoosh on 10/27/15.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Solution {

    public static void main(String[] args) {
        runFirst();
        System.out.println(longestPalindrome("jh1k23saippuakivikauppiasasdjknksjdfbjbdbbdbbdbd"));
        System.out.println(longestPalindrome("add"));
    }

    private static void runFirst() {
        Options opt = new OptionsBuilder()
                .include(Solution.class.getName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            System.out.println("Benchmark broken, caught by " + e);
        }
    }

    @Benchmark
    public static void run2() {
        longestPalindrome2("jh1k23saippuakivikauppiasasdjknksjdfbjbdbbdbbdbd");
        longestPalindrome2(getString());
    }

    @Benchmark
    public static void run() {
        longestPalindrome("jh1k23saippuakivikauppiasasdjknksjdfbjbdbbdbbdbd");
        longestPalindrome(getString());
    }

    public static String getString(){
        int size = 5_000;
        StringBuilder builder = new StringBuilder(size);
        for(int i=0; i<size; i++){
            builder.append("a");
            builder.insert(i%125,(char) (i%50)+35);
        }
        return builder.toString();
    }

    public static String longestPalindrome2(String src) {
        int[] palindromeMap = new int[src.length() * 2 + 3];
        char[] markedString = new char[palindromeMap.length];
        char[] srcChars = src.toCharArray();

        int center = 0, right = 0, mirror = 0;
        int length = 0, pointOfPCenter = 0;

        markedString[0] = '\t';
        markedString[markedString.length - 1] = '\n';
        markedString[palindromeMap.length - 2] = '\r';

        for (int i = 0, i2 = 0; i < src.length(); i2 = 2 * ++i) {
            markedString[i2 + 1] = '\r';
            markedString[i2 + 2] = srcChars[i];
        }

        for (int i = 1; i < markedString.length - 1; i++, mirror = 2 * center - i) {
            if (right > i)
                palindromeMap[i] = Math.min(right - i, palindromeMap[mirror]);
            while (markedString[i + (1 + palindromeMap[i])] == markedString[i - (1 + palindromeMap[i])])
                palindromeMap[i]++;
            if (i + palindromeMap[i] > right) {
                center = i;
                right = i + palindromeMap[i];
            }
        }

        for (int i = 1; i < palindromeMap.length - 1; i++) {
            if (palindromeMap[i] > length) {
                length = palindromeMap[i];
                pointOfPCenter = i;
            }
        }
        return src.substring((pointOfPCenter - 1 - length) / 2, (pointOfPCenter - 1 + length) / 2);
    }

    public static String longestPalindrome(String s) {
        if (s == null) return null;
        if (s.isEmpty()) return s;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int max = 0;
        int max_i = 0;
        int max_j = 0;
        int start, end, mirrorPoint;
        for (int i = 0; i < len; i++) {
            if (max > len - i) break;
            mirrorPoint = len - 1;
            while (true) {
                while (chars[i] != chars[mirrorPoint]) mirrorPoint--;
                if (mirrorPoint == i) break;
                start = i;
                end = mirrorPoint;
                for (int j = i, k = mirrorPoint; j < k; j++, k--) {
                    if (chars[j] != chars[k]) {
                        end = 0;
                        start = 0;
                        break;
                    }
                }
                if (start != end) {
                    if (end - start > max) {
                        max = end - start;
                        max_i = start;
                        max_j = end;
                    }
                }
                mirrorPoint--;
            }
        }
        return s.substring(max_i, max_j + 1);
    }
}
