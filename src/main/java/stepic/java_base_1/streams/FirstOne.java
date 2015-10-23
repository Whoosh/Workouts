package stepic.java_base_1.streams;


import java.util.stream.IntStream;

public class FirstOne {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            pseudoRandomStream(i).limit(30).mapToObj(x -> " " + x).forEachOrdered(System.out::print);
            System.out.println();
        }
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, x -> mid((int) Math.pow(x, 2)));
    }

    public static int mid(int val) {
        String s = String.valueOf(val);
        if (s.length() == 1) return 0;
        if (s.length() < 5) return Integer.valueOf(s.substring(0, s.length() - 1));
        return Integer.valueOf(s.substring(s.length() - 4, s.length() - 1));
    }

    public static int cutFromToLimit(int val, int from, int limit) {
        return Integer.parseInt(String.valueOf(val).chars().skip(from).limit(limit).mapToObj(x -> String.valueOf((char) x)).reduce("0", String::concat));
    }
}
