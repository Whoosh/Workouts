package stepic.algs_csc_base_1.module_4;

import java.util.stream.IntStream;

/**
 * Created by whoosh on 14/12/15.
 */
// попытка защитана
public class Partition {

    public static void main(String[] args) {
        System.out.println(partition(new int[]{1, 2, 3, 4, 4, 5, 8}));
        System.out.println(partition(new int[]{2, 2, 3, 5}));
    }

    private static boolean partition(int[] values) {
        int weight = IntStream.of(values).sum() / 3;
        boolean[][][] map = new boolean[values.length + 1][weight + 1][weight + 1];
        map[0][0][0] = true;
        for (int i = 0; i < map.length; i++) map[i][0][0] = true;
        for (int i = 0; i < map[0].length; i++)
            for (int j = 0; j < map[0][0].length; j++)
                map[0][i][j] = (i | j) == 0;
        for (int k = 1; k < map.length; k++) {
            for (int j = 0; j <= weight; j++) {
                for (int i = 0; i <= weight; i++) {
                    map[k][j][i] = map[k - 1][j][i] | map[k - 1][j - values[k - 1]][i] | map[k - 1][j][i - values[k - 1]];
                }
            }
        }
        return map[values.length][weight][weight];
    }


}
