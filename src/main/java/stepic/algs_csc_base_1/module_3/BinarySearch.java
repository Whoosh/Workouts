package stepic.algs_csc_base_1.module_3;

import java.util.Scanner;

/**
 * Created by whoosh on 11/17/15.
 */
public class BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];
        for (int i = 0; i < array.length; i++) array[i] = scanner.nextInt();
        int[] values = new int[scanner.nextInt()];
        for (int i = 0; i < values.length; i++) values[i] = scanner.nextInt();
        for (int value : values) {
            System.out.print(binarySearch(array, value, 0, array.length - 1) + " ");
        }
    }

    private static int binarySearch(int[] array, int value, int from, int to) {
        final int mid = (to + from) / 2;
        if (to < from) return -1;
        if (array[mid] == value) return mid + 1;
        return value > array[mid] ? binarySearch(array, value, mid + 1, to) : binarySearch(array, value, from, mid - 1);
    }
}
