package stepic.statistic;

import java.util.stream.DoubleStream;

/**
 * Created by whoosh on 5/13/16.
 */
public class Task_1_3_10 {

    private static double values[] = {17.71, 17.98, 17.39, 17.74, 19.18, 18.19, 18.23, 19.51, 15.08, 18.34, 16.68, 18.61, 16.62, 16.51, 17.5, 17.9, 16.52, 18.99, 17.34, 18.27, 17.13, 17.23, 17.05, 17.23, 18.08, 17.34, 16.38, 17.54, 19.34, 17.01, 19.87, 16.67, 17.38, 17.22, 16.59, 17.57, 16.12, 17.74, 18.57, 16.78};
    public static void main(String[] args) {
        printStatInclusive(15, 15.75);
        double before = 15.75;
        for (double val = 16.5; val <= 21; val += 0.75) {
            printStatNonInclusive(before, val);
            before = val;
        }
    }

    public static void printStatNonInclusive(double from, double to) {
        System.out.printf("%.3f, ",(double) DoubleStream.of(values).filter(x -> x > from && x <= to).count() / values.length);
    }

    public static void printStatInclusive(double from, double to) {
        System.out.printf("%.3f, ",(double) DoubleStream.of(values).filter(x -> x >= from && x <= to).count() / values.length);
    }
}
