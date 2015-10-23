package project_euler.sub_code;

import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class IntStreams {
    public static IntStream rangeClosed(int from, int to) {
        return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(new OfIntFromTo(from, to), 0), false);
    }
}
