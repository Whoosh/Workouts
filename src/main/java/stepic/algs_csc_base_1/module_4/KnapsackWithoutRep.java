package stepic.algs_csc_base_1.module_4;

import java.io.IOException;

/**
 * Created by whoosh on 12/10/15.
 */

public class KnapsackWithoutRep {

    public static void main(String[] args) throws IOException {
        int W = nextInt();
        int count = nextInt();
        int[] weights = new int[count];
        int[] price;
        int[][] d = new int[count + 1][W + 1];
        for (int i = 0; i < count; i++) weights[i] = nextInt();
        price = weights.clone();

        for (int i = 1; i <= count; i++) {
            for (int w = 1; w <= W; w++) {
                d[i][w] = d[i - 1][w];
                if (weights[i - 1] <= w) {
                    d[i][w] = Math.max(d[i][w], d[i - 1][w - weights[i - 1]] + price[i - 1]);
                }
            }
        }
        System.out.println(d[count][W]);
    }


    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        do {
            val += d - 48;
            if ((d = System.in.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return val;
    }
}
