package stepic.algs_base_1.module_3;

import java.util.Scanner;

/**
 * Created by whoosh on 11/19/15.
 */
public class MergeSwap {

    private static long swapCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[scanner.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(a, 0, a.length - 1);
        System.out.println(swapCount);
    }

    public static int[] mergeSort(int[] a, int start, int end) {
        if (end <= start) return new int[]{a[start]};
        int mid = start + (end - start) / 2;
        int[] da = mergeSort(a, start, mid);
        int[] db = mergeSort(a, mid + 1, end);
        return merge(da, db);
    }

    public static int[] merge(int[] a, int[] b) {
        int i = a.length - 1;
        int j = b.length - 1;
        int[] res = new int[a.length + b.length];
        int k = res.length;
        while (--k >= 0) {
            if (i >= 0 && (j < 0 || a[i] > b[j])) {
                res[k] = a[i--];
                swapCount += j + 1;
            } else res[k] = b[j--];
        }
        return res;
    }

    public static int[] mergeDirect(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int k = 0, i = 0, j = 0;
        while (i < a.length && b.length > j) c[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        while (i < a.length) c[k++] = a[i++];
        while (j < b.length) c[k++] = b[j++];
        return c;
    }

    public static int[] mergeBack(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int k = c.length - 1, i = a.length - 1, j = b.length - 1;
        while (j >= 0 && i >= 0) {
            if (a[i] >= b[j]) c[k--] = a[i--];
            else c[k--] = b[j--];
        }
        while (i >= 0) c[k--] = a[i--];
        while (j >= 0) c[k--] = b[j--];
        return c;
    }
}
