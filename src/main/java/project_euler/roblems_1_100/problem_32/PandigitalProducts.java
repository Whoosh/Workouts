package project_euler.roblems_1_100.problem_32;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.HashSet;

/**
 * https://projecteuler.net/problem=32
 */
public class PandigitalProducts implements Starter {

    public static final int PAN_LIMIT = 9;
    public static HashSet<Integer> products = new HashSet<>();
    public static HashSet<Integer> buffer = new HashSet<>();

    public static void main(String[] args) {
        new PandigitalProducts().start();
    }

    @StartWithLazzyBenchmark
    public void run(){
        for (int a = 1, size, product; a < 8000; a++) {
            for (int b = 1; b < 8000; b++) {
                product = a * b;
                size = getDigitsCount(a) + getDigitsCount(b) + getDigitsCount(product);
                if (size > PAN_LIMIT || products.contains(product)) break;
                if (isPandigital(a, b, product, size)) products.add(product);
            }
        }
        System.out.println(products.stream().mapToLong(x -> x).sum());
    }

    public static boolean isPandigital(int a, int b, int product, int size) {
        return size == PAN_LIMIT && pan(a, b, product);
    }

    private static boolean pan(int a, int b, int product) {
        buffer.clear();
        setInBuffer(a);
        setInBuffer(b);
        setInBuffer(product);
        return buffer.size() == PAN_LIMIT && !buffer.contains(0);
    }

    private static void setInBuffer(int val) {
        for (int i = val; i != 0; i /= 10) buffer.add(i % 10);
    }

    private static int getDigitsCount(int a) {
        int count = 0;
        for (int i = a; i != 0; i /= 10) count++;
        return count;
    }
}
