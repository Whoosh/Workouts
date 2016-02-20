package problem_334;

import leetcode.problems_300_400.problem_334.Solution;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by whoosh on 2/20/16.
 */
public class SolutionTest {
    private static final int SIZE = 2000;

    @Test
    public void randomGenTest() {
        int[] map = new int[SIZE];
        for (int i = 0; i < map.length; i++) map[i] = i;
        for (int i = 0; i < SIZE; i++) {
            shuffleArray(map);
            Assert.assertEquals(Solution.increasingTriplet(map), brutForce(map));
        }
    }

    private boolean brutForce(int[] map) {
        for (int i = 0; i < map.length - 1; i++)
            for (int j = i + 1; j < map.length - 1; j++)
                for (int k = j + 1; k < map.length; k++) {
                    if (map[i] < map[j] && map[j] < map[k]) return true;
                }
        return false;
    }

    public static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        int index, a;
        for (int i = ar.length - 1; i > 0; i--) {
            index = rnd.nextInt(i + 1);
            a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
