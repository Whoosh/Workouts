package stepic.algs_mail_base_1.module_2;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Created by whoosh on 12/17/15.
 */
public class GoodSequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : scan.nextLine().toCharArray()) deque.add(c);
        ArrayDeque<Character> fill = fill(fill(deque, false), true);
        if (fill == null) {
            System.out.println("IMPOSSIBLE");
        } else {
            fill(fill, true).forEach(System.out::print);
        }
    }

    public static ArrayDeque<Character> fill(ArrayDeque<Character> seq, boolean fromBack) {
        if (seq == null) return null;
        ArrayDeque<Character> buffer = new ArrayDeque<>(seq.size());
        ArrayDeque<Character> inv = new ArrayDeque<>(seq.size());
        char b;
        while (!seq.isEmpty()) {
            b = fromBack ? getBackFor(seq.pollFirst()) : seq.pollLast();
            inv.addLast(b);
            if (isRightOriented(b)) {
                if (!buffer.isEmpty() && buffer.pollLast() != b) return null;
            } else {
                buffer.addLast(getBackFor(b));
            }
        }
        while (!buffer.isEmpty()) inv.addLast(buffer.pollLast());
        while (!inv.isEmpty()) seq.addLast(inv.pollLast());
        return seq;
    }

    private static boolean isRightOriented(char v) {
        return v == '[' || v == '(' || v == '{';
    }

    private static Character getBackFor(Character first) {
        if (first == '}') return '{';
        if (first == ']') return '[';
        if (first == ')') return '(';
        if (first == '{') return '}';
        if (first == '[') return ']';
        if (first == '(') return ')';
        return ' ';
    }
}
