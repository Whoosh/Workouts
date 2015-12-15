package stepic.algs_csc_base_1.module_2.theme_3;

import java.util.Scanner;

/**
 * Created by whoosh on 11/12/15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opCount = scanner.nextInt();
        ArrayMaxHeap arrayMaxHeap = new ArrayMaxHeap(opCount);
        String command = scanner.nextLine();
        for(int i=0; i<opCount; i++){
            command = scanner.nextLine();
            if(command.equals("ExtractMax")){
                System.out.println(arrayMaxHeap.extractMax());
            }else {
                arrayMaxHeap.insert(Integer.parseInt(command.replace("Insert ","")));
            }
        }
    }

    private static class ArrayMaxHeap {

        private int[] heap;
        private int end = 0;

        public ArrayMaxHeap(int capacity) {
            heap = new int[capacity];
        }

        public int extractMax() {
            int max = heap[0];
            swap(0, end - 1);
            heap[end - 1] = 0;
            end--;
            siftDown(0);
            return max;
        }

        public void insert(int val) {
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
    }
}
