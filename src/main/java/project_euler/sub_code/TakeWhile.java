package project_euler.sub_code;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TakeWhile {

    public static <T> Stream<T> takeWhileOrdered(Stream<T> stream, Predicate<T> predicate) {
        Spliterator<T> spliterator = stream.spliterator();
        return StreamSupport.
                stream(Spliterators.spliteratorUnknownSize(
                        new TakeWhileIterator<>(Spliterators.iterator(spliterator), predicate),
                        spliterator.characteristics() | Spliterator.ORDERED), false);
    }

    public static LongStream longTakeWhileOrdered(LongStream stream, Predicate<Long> predicate) {
        Spliterator spliterator = stream.spliterator();
        WhileLongIterator whileLongIterator = new WhileLongIterator(Spliterators.iterator(spliterator), predicate);
        return StreamSupport.longStream(
                Spliterators.spliteratorUnknownSize(
                        whileLongIterator, spliterator.characteristics() | Spliterator.ORDERED), false);
    }

}

