package stepic.algs_csc_base_1.module_4;

import java.util.Arrays;

/**
 * Created by whoosh on 12/10/15.
 */
public class KnapsackWithRep {
    public static void main(String[] args) {
        int[] c = {30, 14, 16, 9};
        int[] weights = {6, 3, 4, 2};
        int W = 10;
        int[] d = new int[W + 1];
        for (int w = 0; w <= W; w++) {
            for (int i = 0; i < c.length; i++) {
                if (weights[i] <= w) {
                    d[w] = Math.max(d[w], d[w - weights[i]] + c[i]);
                }
            }
        }
        System.out.println(Arrays.toString(d));
    }
}
