package project_euler.roblems_1_100.problem_60;

import java.util.Arrays;

public class Element{
    private int[] primes;
    private static final int COUNT = 5;

    public Element(int... primes) {
        this.primes = new int[COUNT];
        System.arraycopy(primes, 0, this.primes, 0, primes.length);
    }

    public int sum() {
        int result = 0;
        for (int i : primes) result += i;
        return result;
    }

    public int[] getSrc() {
        return primes;
    }

    public void setVal(int val, int index) {
        primes[index] = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (!Arrays.equals(primes, element.primes)) return false;

        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(primes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(primes);
    }
}
