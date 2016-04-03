package java_stuff.features;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * Created by whoosh on 3/2/16.
 */
public class SimplyExamples {

    private static Supplier<Long> throwOrGet = () -> {
        double v = new Random().nextDouble();
        if (v < 0.5) throw new NullPointerException("GG");
        return (long) (v * Math.pow(10, 10));
    };

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int good = 0;
        int bad = 0;
        boolean a = false;
        for (int i = 0; i < 1000; i++) {
            a = CompletableFuture.supplyAsync(throwOrGet).thenAccept(System.out::println)
                    .exceptionally(throwable -> null).isCancelled();
            if (a) {
                good++;
            } else bad++;
            System.out.println(a);
        }
        System.out.println(bad);
        System.out.println(good);
        System.out.println(bad + good);
    }
}
