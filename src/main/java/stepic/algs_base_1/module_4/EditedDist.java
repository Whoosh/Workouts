package stepic.algs_base_1.module_4;

import java.util.Scanner;

/**
 * Created by whoosh on 12/10/15.
 */
public class EditedDist {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] a = scanner.nextLine().toCharArray();
        char[] b = scanner.nextLine().toCharArray();
        byte[][] map = new byte[a.length + 1][b.length + 1];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = Byte.MAX_VALUE;
            }
        }
        for (int i = 0; i < map.length; i++) map[i][0] = (byte) (i);
        for (int j = 0; j < map[0].length; j++) map[0][j] = (byte) (j);

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                map[i][j] = (byte) min(map[i][j - 1] + 1, map[i - 1][j] + 1, map[i - 1][j - 1] + getSwapPrice(a[i - 1], b[j - 1]));
            }
        }
        System.out.println(map[a.length][b.length]);
    }

    public static int getSwapPrice(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
