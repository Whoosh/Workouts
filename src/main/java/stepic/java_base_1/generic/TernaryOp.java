package stepic.java_base_1.generic;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by DX on 19.10.2015.
 */
public class TernaryOp {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println(safeStringLength.apply("123"));
        System.out.println(safeStringLength.apply(null));

    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return t -> {
            return condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
        };
    }
}
