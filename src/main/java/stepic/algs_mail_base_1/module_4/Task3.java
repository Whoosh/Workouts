package stepic.algs_mail_base_1.module_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by whoosh on 1/25/16.
 */
public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> b = new HashSet<>();
        ArrayList<String> f = new ArrayList<>();
        do {
            f.add(reader.readLine());
        }while (reader.ready());
        for (String s : f) {
            final String[] split = s.split(" ");
            if (split[0].equals("+")) printResult(b.add(split[1]));
            else if (split[0].equals("?")) printResult(b.contains(split[1]));
            else if (split[0].equals("-")) printResult(b.remove(split[1]));
        }
    }

    public static void printResult(boolean g) {
        System.out.println(g ? "OK" : "FAIL");
    }
}
