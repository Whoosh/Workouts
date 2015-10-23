package coursera.algs_1.que;

public class Subset {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (int i = 1; i < args.length; i++) {
            randomizedQueue.enqueue(args[i]);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
