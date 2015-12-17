package stepic.algs_mail_base_1.modulee_2;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by whoosh on 12/16/15.
 */


public class GreedyMan {

    public static void main(String[] args) throws IOException {
        int count = nextInt();
        ArrayMaxHeap bag = new ArrayMaxHeap(count);
        for (int i = 0; i < count; i++) bag.add(nextInt());
        int weight = nextInt();
        Queue<Integer> goodElements = new LinkedList<>();
        int sum, result = 0;
        Integer b;
        while (!bag.isEmpty()) {
            result++;
            sum = 0;
            while (!bag.isEmpty()) {
                b = bag.poll();
                if (sum + b > weight) {
                    bag.add(b);
                    break;
                } else {
                    sum += b;
                    if (b != 1) goodElements.add(b);
                }
            }
            for (Integer goodElement : goodElements) {
                bag.add(goodElement / 2);
            }
            goodElements = new LinkedList<>();
        }
        System.out.print(result);
    }

    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        boolean l = false;
        if (d == '-') {
            l = true;
            d = System.in.read();
        }
        do {
            val += d - 48;
            if ((d = System.in.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return l ? val * -1 : val;
    }

    private static class ArrayMaxHeap {

        private int[] heap;
        private int end = 0;

        public ArrayMaxHeap(int capacity) {
            heap = new int[capacity];
        }

        public int poll() {
            int max = heap[0];
            swap(0, end - 1);
            heap[end - 1] = 0;
            end--;
            siftDown(0);
            return max;
        }

        public void addAll(Collection<Integer> any) {
            for (Integer integer : any) {
                add(integer);
            }
        }

        public boolean isEmpty() {
            return end == 0;
        }

        public void add(int val) {
            heap[end] = val;
            siftUp(end);
            end++;
        }

        private void siftUp(int i) {
            if (i == 0) return;
            int index = getParentIndex(i);
            if (heap[i] > heap[index]) {
                swap(i, index);
                siftUp(index);
            } else return;
        }

        private void siftDown(int i) {
            if (i >= end) return;
            if (heap[i] > getRight(i) && heap[i] > getLeft(i)) return;
            if (getLeft(i) > getRight(i)) {
                swap(i, leftIndex(i));
                siftDown(leftIndex(i));
            } else {
                swap(i, rightIndex(i));
                siftDown(rightIndex(i));
            }
        }

        private void swap(int i, int j) {
            int a = heap[i];
            heap[i] = heap[j];
            heap[j] = a;
        }

        private int leftIndex(int i) {
            return ((i + 1) * 2) - 1;
        }

        private int rightIndex(int i) {
            return (i + 1) * 2;
        }

        private int getParentIndex(int i) {
            if (i == 0) return 0;
            return (i + 1) / 2 - 1;
        }

        private int getRight(int i) {
            int index = rightIndex(i);
            if (index >= end + 1) return Integer.MIN_VALUE;
            else return heap[index];
        }

        private int getLeft(int i) {
            int index = leftIndex(i);
            if (index >= end + 1) return Integer.MIN_VALUE;
            else return heap[index];
        }

        public int size() {
            return end;
        }

        public int get(int index) {
            return heap[index];
        }
    }
}
