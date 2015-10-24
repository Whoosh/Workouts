package leetcode.problem_1;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Solution {

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(Solution.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            System.out.println("Benchmark broken, caught by " + e);
        }
    }

    private static int[] map;

    static {
        int[] ints = {1, 1, 1, 1, 1, 1, 102, 102, 11, 10, 10, 10, 6, 11, 1, 1, 1, 11, 2, 7, 11, 15};
        map = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < map.length; i++) map[i] = (random.nextInt(1000) + 10) * -1;
        System.arraycopy(ints, 0, map, map.length / 2, ints.length);
    }


    @Benchmark
    public static void run() {
        new Solution().twoSum(map, 9);
    }

    @Benchmark
    public static void run2() {
        new Solution().twoSum2(map, 9);
    }

    // initialization new memory take more time than simply brute force for little N
    // O(N^2) speed and O(1) memory
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i + 1, j + 1};
            }
        }
        return new int[2];
    }

    // for huge N brut force gonna make you dead
    // O(N) speed cuz we have O(1) for search but affect there is O(N) addition memory.
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (target == nums[map.get(nums[i])] + nums[i]) return new int[]{map.get(nums[i]) + 1, i + 1};
            }
            if (map.containsKey(target - nums[i])) return new int[]{map.get(target - nums[i]) + 1, i + 1};
            map.put(nums[i], i);
        }
        return new int[2];
    }
}

