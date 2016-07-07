package leetcode.problems_0_100.problem_33;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * Created by whoosh on 2/14/16.
 */
public class Solution {
    public static void main(String[] args) {
        Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, bigIntegers -> new BigInteger[]{bigIntegers[1],bigIntegers[0].add(bigIntegers[1])})
                .forEach(x-> System.out.println(x[0]));
    }
}
