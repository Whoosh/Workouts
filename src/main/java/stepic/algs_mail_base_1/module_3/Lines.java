package stepic.algs_mail_base_1.module_3;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by whoosh on 1/23/16.
 */
public class Lines {
    public static void main(String[] args) throws IOException {
        int count = nextInt();
        int[][] map = new int[count][2];
        for (int i = 0; i < count; i++) {
            map[i][0] = nextInt();
            map[i][1] = nextInt();
        }
        Arrays.sort(map, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        for (int i = 0; i < count; i++) {
            System.out.print(map[i][0]);
            System.out.print(' ');
            System.out.print(map[i][1]);
            System.out.println();
        }
    }

    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        boolean l = false;
        if (d == '-') {
            l = true;
            d = System.in.read();
        }
        do {
            val += d - 48;
            if ((d = System.in.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return l ? val * -1 : val;
    }
}
