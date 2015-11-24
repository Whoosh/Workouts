package stepic.algs_base_1.module_3;

import java.util.Arrays;

/**
 * Created by whoosh on 11/22/15.
 */

public class QSort {
    public static void main(String[] args) {
        int[] a = new int[]{7, 6, 3, 4, 5, 2};
        qSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void qSort(int[] a, int l, int r) {
        if (r <= l) return;
        int i = l;
        int j = r;
        int m = a[l + (r - l) / 2];
        while (i <= j) {
            while (a[i] < m) i++;
            while (a[j] > m) j--;
            if (i <= j) swap(a, i++, j--);
        }
        if (l < j) qSort(a, l, j);
        if (i < r) qSort(a, i, r);
    }

    public static void swap(int a[], int i, int j) {
        int b = a[i];
        a[i] = a[j];
        a[j] = b;
    }
}
