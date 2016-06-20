package stepic.statistic;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * Created by whoosh on 5/13/16.
 */
public class Task_1_3_12 {
    private static int[] data = {23, 24, 21, 23, 22, 21, 20, 21, 28, 25, 22, 22, 25, 21};

    public static void main(String[] args) {
        IntSummaryStatistics stat = IntStream.of(data).summaryStatistics();
        System.out.println(stat.getAverage());
        double d = IntStream.of(data)
                .mapToDouble(x -> Math.pow((x - stat.getAverage()), 2) / data.length).sum();
        System.out.println(d);
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
    }
}
