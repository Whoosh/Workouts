package stepic.statistic;

import org.apache.commons.math3.special.Erf;

import java.util.stream.DoubleStream;

/**
 * Created by whoosh on 5/15/16.
 */
public class Task_1_3_14 {

    private static double[] weight = {66, 74, 77, 72, 67, 77, 76, 77, 72, 76};
    private static double[] height = {170, 182, 183, 180, 175, 181, 187, 181, 178, 187};

    public static void main(String[] args) {
        double mid_x = DoubleStream.of(weight).average().getAsDouble();
        double mid_y = DoubleStream.of(height).average().getAsDouble();


        double d_x = DoubleStream.of(weight).map(x -> Math.pow((x - mid_x), 2) / weight.length).sum();
        double d_y = DoubleStream.of(height).map(x -> Math.pow((x - mid_y), 2) / height.length).sum();


        System.out.println(mid_y);
        System.out.println(mid_x);
        System.out.println(d_x);
        System.out.println(d_y);

        double sOfD = Math.sqrt(d_x) * Math.sqrt(d_y);
        double sum = 0;

        for (int i = 0; i < weight.length; i++) {
            sum += (weight[i] - mid_x) * (height[i] - mid_y);
        }

        System.out.println(sum / sOfD);
    }


    public static double exp(int x, double lambda) {
        return 1 - Math.pow(Math.E, -lambda * x);
    }

    public static double geom(double x, double n) {
        return 1 - Math.pow(x, n + 1);
    }

    public static double normal(double x, double n, double q) {
        return (1 + Erf.erf((x - n / Math.sqrt(2 * Math.pow(q, 2))))) / 2;
    }
}
