package stepic.algs_csc_base_1.module_2.theme_1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by whoosh on 11/10/15.
 */

public class Bag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        double bagSize = scanner.nextInt();
        int[][] items = new int[count][];
        for (int i = 0; i < count; i++) {
            items[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
        }
        Arrays.sort(items, (o1, o2) -> Double.compare(((double) o2[0] / o2[1]), (double) o1[0] / o1[1]));
        double result = 0;
        for (int i = 0; bagSize != 0 && i < count; i++) {
            if (bagSize < items[i][1]) {
                result += ((double)items[i][0] / items[i][1]) * bagSize;
                bagSize = 0;
            } else {
                bagSize -= items[i][1];
                result += items[i][0];
            }
        }
        System.out.printf("%1.3f ", result);
    }
}
