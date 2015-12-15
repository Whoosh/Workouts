package stepic.algs_csc_base_1.module_2.theme_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by whoosh on 11/12/15.
 */

public class HuffmansDecode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int codeLength = scanner.nextInt();
        scanner.nextLine();
        HashMap<String, Character> mapOfCodes = new HashMap<>();
        ArrayList<String> sortedCodes;
        String buffer;
        for (int i = 0; i < count; i++) {
            buffer = scanner.nextLine();
            mapOfCodes.put(buffer.substring(buffer.indexOf(' ') + 1), buffer.charAt(0));
        }
        sortedCodes = new ArrayList<>(mapOfCodes.keySet());
        Collections.sort(sortedCodes, (o1, o2) -> o2.length() - o1.length());

        String code = scanner.nextLine();
        StringBuilder result = new StringBuilder();

        while (code.length() != 0) {
            for (String sortedCode : sortedCodes) {
                if (code.length() < sortedCode.length()) continue;
                String s = code.substring(0, sortedCode.length());
                if (mapOfCodes.containsKey(s)) {
                    result.append(mapOfCodes.get(s));
                    code = code.substring(sortedCode.length());
                }
            }
        }

        System.out.println(result.toString());
    }
}
