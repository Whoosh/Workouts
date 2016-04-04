package random_tasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by whoosh on 4/3/16.
 */
public class TopologySortTest {

    @Test
    public void SimplyArrayTestRecursively() {
        int[][] graph = new int[][]{{3}, {}, {1}, {1, 2}};
        int[] actual = TopologySort.sortArrayByRecursion(graph);
        int[] expected = {1, 2, 3, 0};
        for (int i = actual.length - 1; i >= 0; i--) System.out.print(actual[i] + " ");
        System.out.println(Arrays.toString(actual));
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void SimplyArrayTestWithCycleInGraphRecursively() {
        int[][] graph = new int[][]{{3,1,2}, {1,2,3}, {1,3}, {1, 2}};
        int[] actual = TopologySort.sortArrayByRecursion(graph);
        int[] expected = {};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void SimplyArrayTestByCycle() {
        int[][] graph = new int[][]{{3}, {}, {1}, {1, 2}};
        int[] actual = TopologySort.sortArrayByCycle(graph);
        int[] expected = {1, 2, 3, 0};
        for (int i = actual.length - 1; i >= 0; i--) System.out.print(actual[i] + " ");
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
    }
}
