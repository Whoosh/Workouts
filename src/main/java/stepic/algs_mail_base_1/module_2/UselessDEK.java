package stepic.algs_mail_base_1.module_2;

import java.io.IOException;
import java.util.ArrayDeque;

/**
 * Created by whoosh on 12/15/15.
 */

public class UselessDEK {
    public static void main(String[] args) throws IOException {
        int count = nextInt();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int command;
        for (int i = 0; i < count; i++) {
            command = nextInt();
            if (command == 1) deque.push(nextInt());
            if (command == 2) {
                Integer poll = deque.pollFirst();
                if (poll == null) poll = -1;
                if (poll != nextInt()) {
                    System.out.println("NO");
                    return;
                }
            }
            if (command == 3) deque.addLast(nextInt());
            if (command == 4) {
                Integer poll = deque.pollLast();
                if (poll == null) poll = -1;
                if (poll != nextInt()) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
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
}
