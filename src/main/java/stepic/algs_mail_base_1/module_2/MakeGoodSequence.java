package stepic.algs_mail_base_1.module_2;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by whoosh on 12/16/15.
 */
// can repair even sequence like {]}{]}
public class MakeGoodSequence {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] sequence = scan.nextLine().toCharArray();
        ArrayDeque<Character> sequenceHandler = new ArrayDeque<>();
        String result = "";
        String end = "";
        ArrayDeque<Character> backEnd = new ArrayDeque<>();
        ArrayDeque<Character> resultHead = new ArrayDeque<>();
        for (char c : sequence) sequenceHandler.addLast(c);
        Character first;
        while ((first = sequenceHandler.poll()) != null) {
            if (isLeftOriented(first)) {
                resultHead.addFirst(first);
                resultHead.addFirst(getBackFor(first));
            } else {
                final Character finalFirst = getBackFor(first);
                Optional<Character> any = sequenceHandler.stream().filter(x -> finalFirst == x).findAny();
                if (any.isPresent()) {
                    sequenceHandler.remove(any.get());
                }
                resultHead.addLast(first);
                backEnd.addFirst(finalFirst);
            }
        }
        Character s;
        while ((s = backEnd.poll()) != null) end += s;
        while ((s = resultHead.poll()) != null) result += s;
        System.out.println(result + end);
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

    private static boolean isLeftOriented(char v) {
        return v == ']' || v == ')' || v == '}';
    }
}
