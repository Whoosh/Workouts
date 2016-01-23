package stepic.algs_mail_base_1.module_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Created by whoosh on 1/23/16.
 */
public class InSort {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        int[] ints = Stream.of(data.readLine().split(" ")).filter(x->!x.equals("")).map(x->Integer.parseInt(x)).mapToInt(x->x).toArray();
        for (int i = 0; i < ints.length; i++) {
            swap(ints, findMinIndexOfValues(ints, i), i);
        }
        for (int anInt : ints) {
            System.out.print(anInt);
            System.out.print(' ');
        }
    }

    public static int findMinIndexOfValues(int[] ints, int from) {
        int index = from;
        int v = Integer.MAX_VALUE;
        for (int i = from; i < ints.length; i++) {
            if (ints[i] < v) {
                index = i;
                v = ints[i];
            }
        }
        return index;
    }

    public static void swap(int[] ints, int i, int j) {
        int a = ints[i];
        ints[i] = ints[j];
        ints[j] = a;
    }
}
