package leetcode.problem_15;

import leetcode.problems_0_100.problem_15.Solution;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by whoosh on 2/11/16.
 */
public class TestSolution {

    @Test
    public void randomSizeAndDataArrayTest(){
        int seed = 100;
        int[] data = new int[new Random().nextInt(seed)];
        Random random = new Random(seed);
        for(int i=0; i<data.length; i++){
            data[i] = random.nextInt(10);
            if((i&1) == 0) data[i] = -data[i];
        }
        List<List<Integer>> lists = Solution.threeSum(data);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
