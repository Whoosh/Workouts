package stepic.algs_base_1.module_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by whoosh on 11/10/15.
 */

public class LinesAndPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int LR[][] = new int[n][];
        for (int i = 0; i < n; i++) LR[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
        Arrays.sort(LR, (o1, o2) -> o1[1] - o2[1]);
        ArrayList<Integer> result = new ArrayList<>(n);
        for (int i = 0, p; i < n; ) {
            p = LR[i][1];
            result.add(p);
            while (++i < n && p >= LR[i][0]);
        }
        System.out.println(result.size());
        result.forEach(x->System.out.print(x+" "));
    }
}
