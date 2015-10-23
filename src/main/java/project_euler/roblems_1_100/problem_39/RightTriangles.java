package project_euler.roblems_1_100.problem_39;


import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

/**
 * https://projecteuler.net/problem=39
 */
public class RightTriangles implements Starter {

    public static final int PROBLEM = 1000;

    public static void main(String[] args) {
        new RightTriangles().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        int result = 0, k = 0;
        for (int i = PROBLEM / 2, b; i < PROBLEM; i += 10) {
            b = getSolutionsFor(i);
            if (b > result) {
                k = i;
                result = b;
            }
        }
        System.out.println(result + " " + k);
    }

    private static int getSolutionsFor(int max) {
        int nSolution = 0;
        for (int a = 3; a < max; a++)
            for (int b = a; (b + a * 2) <= max; b++)
                for (int c = a; c < max; c++)
                    if (sum(a, b, c, max) && isPythagorasThree(a, b, c)) nSolution++;
        return nSolution;
    }

    public static boolean sum(int a, int b, int c, int max) {
        return (a + b + c) == max;
    }

    public static boolean isPythagorasThree(int a, int b, int c) {
        return (pow(a) + pow(b)) == pow(c);
    }

    public static int pow(int val) {
        return val * val;
    }
}
