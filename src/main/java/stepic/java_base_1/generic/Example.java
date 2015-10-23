package stepic.java_base_1.generic;

import java.util.stream.Stream;

/**
 * Created by DX on 19.10.2015.
 */
public class Example {
    static Stream<String> s = Stream.empty();

    public static void main(String[] args) {
        s.map(x ->x);
    }
}
