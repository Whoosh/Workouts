package project_euler.roblems_1_100.problem_74;

import project_euler.sub_code.CustomMathFunctions;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by whoosh on 4/1/16.
 */
public class DigitFactorialChains {

    private final static int N = 1000000;
    private final static int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    private final static HashMap<Long, HashSet<Long>> starterOnChain = new HashMap<>(N);

    public static void main(String[] args) {
        Long startPoint;
        HashSet<Long> chain;
        for (int i = 1; i < N; i++) {
            chain = new HashSet<>();
            starterOnChain.put((long) i, chain);
            startPoint = getNextOfChain((long) i);
            chain.add((long) i);
            while (!chain.contains(startPoint)) {
                chain.add(startPoint);
                startPoint = getNextOfChain(startPoint);
            }
        }
        System.out.println(starterOnChain.values().stream().filter(x -> x.size() == 60).count());
    }

    public static Long getNextOfChain(Long v) {
        Long startPoint = 0L;
        for (Integer integer : CustomMathFunctions.longToArray(v)) startPoint += factorials[integer];
        return startPoint;
    }

}
