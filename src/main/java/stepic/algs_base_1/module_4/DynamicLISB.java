package stepic.algs_base_1.module_4;

import java.io.IOException;

/**
 * Created by whoosh on 11/30/15.
 */

public class DynamicLISB {

    public static void main(String[] args) throws IOException {
        final long count = nextLong();
        long val;
        long[] elements = new long[(int) count];
        long[] lengths = new long[(int) count];
        for (int i = 0; i < count; i++) {
            val = nextLong();
            elements[i] = val;
        }
        fillLISLengths(elements, lengths);
        int maxLength = getLISD(elements, lengths, findMax(lengths));
        System.out.println(maxLength);
    }

    private static int getLISD(long[] elements, long[] lengths, int maxIndex) {
        int count = 1;
        for (int i = maxIndex - 1; i >= -1; i--) {
            if (i >= 0) {
                if (elements[maxIndex] % elements[i] != 0) continue;
                count++;
            }
            while (i >= 0 && (lengths[i] != lengths[maxIndex] - 1 || elements[i] > elements[maxIndex])) {
                i--;
            }
            maxIndex = i;
        }
        return count;
    }

    private static int findMax(long[] lengths) {
        int maxIndex = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[maxIndex] <= lengths[i]) maxIndex = i;
        }
        return maxIndex;
    }

    private static void fillLISLengths(long[] elements, long[] lengths) {
        for (int i = 0; i < elements.length; i++) {
            lengths[i] = 1;
            for (int j = 0; j < i; j++) {
                if (elements[j] <= elements[i] && lengths[j] + 1 > lengths[i]) {
                    if (elements[i] % elements[j] == 0) {
                        lengths[i] = lengths[j] + 1;
                    }
                }
            }
        }
    }

    private static long nextLong() throws IOException {
        long d;
        long val = 0;
        while ((d = System.in.read()) == ' ') ;
        do {
            val += d - 48;
            val *= 10;
        } while ((d = System.in.read()) > 47 && d < 58);
        return val / 10;
    }
}
