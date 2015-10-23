package project_euler.roblems_1_100.problem_23;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.IntStreams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */

public class NonAbundantSums {

    private final static int LIMIT_PROBLEM = 28123;
    private final static int MIN_ABUNDANT = 12;

    public static void main(String[] args) {
        int buffer;
        final Set<Integer> possiblySumsLowerThenLimit = new HashSet<>();
        final List<Integer> abundantNumbers = IntStreams
                .rangeClosed(MIN_ABUNDANT, LIMIT_PROBLEM)
                .parallel()
                .filter(NonAbundantSums::isAbundant)
                .boxed()
                .collect(Collectors.toList());
        for (int i = 0; i < abundantNumbers.size(); i++) {
            final Integer iN = abundantNumbers.get(i);
            for (int j = i; j < abundantNumbers.size(); j++) {
                buffer = abundantNumbers.get(j) + iN;
                if (buffer >= LIMIT_PROBLEM) break;
                possiblySumsLowerThenLimit.add(buffer);
            }
        }
        System.out.println(IntStreams.rangeClosed(1, LIMIT_PROBLEM).sum() - possiblySumsLowerThenLimit.stream().mapToInt(x -> x).sum());
    }

    public static boolean isAbundant(int n) {
        return CustomMathFunctions.sumDividersOf(n) > n;
    }
}
