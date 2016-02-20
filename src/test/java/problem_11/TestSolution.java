package problem_11;

import leetcode.problems_0_100.problem_11.Solution;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by whoosh on 2/7/16.
 */

public class TestSolution {

    @Test
    public void maxAreaTest() {
        int first = Solution.maxArea(new int[]{1, 1});
        Assert.assertEquals(1, first);
        int second = Solution.maxArea(new int[]{1,1,3,5,1,2,6,1,10,2,18,1,29,1,23,4,1,2,5,7,8,5,3,1,3,67,8,8,1});
        Assert.assertEquals(377, second);
        int third = Solution.maxArea(new int[]{0});
        Assert.assertEquals(0, third);
        int fourth = Solution.maxArea(new int[]{});
        Assert.assertEquals(0, fourth);
        int fifth = Solution.maxArea(new int[]{3,4,5,1,2,3,5,2,5});
        Assert.assertEquals(30, fifth);
    }
}
