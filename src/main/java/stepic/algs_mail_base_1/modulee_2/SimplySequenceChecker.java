package stepic.algs_mail_base_1.modulee_2;

import java.util.ArrayDeque;

/**
 * Created by whoosh on 12/16/15.
 */
public class SimplySequenceChecker {
    public static void main(String[] args) {
        ArrayDeque<Character> sequenceHandler = new ArrayDeque<>();
        char[] sequence = "[{][[[[{}[]}]]]]".toCharArray();
        for (char v : sequence) {
            if (isRightOriented(v)) {
                sequenceHandler.add(v);
            } else {
                if (!sequenceHandler.isEmpty()) {
                    char poll = sequenceHandler.poll();
                    if (poll == ')' && v != '(') break;
                    if (poll == '}' && v != '{') break;
                    if (poll == ']' && v != '[') break;
                }
            }
        }
        if (!sequenceHandler.isEmpty()) {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean isRightOriented(char v) {
        return v == '[' || v == '(' || v == '{';
    }
}
