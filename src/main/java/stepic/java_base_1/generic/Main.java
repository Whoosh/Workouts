package stepic.java_base_1.generic;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> src = new ArrayList<>(Integer.MAX_VALUE);
            BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
            String s = line.readLine();
            Scanner scanner = new Scanner(s).useDelimiter(" ");
            while (scanner.hasNext()) src.add(scanner.nextInt());
            for (int i = 0; i < src.size(); i++) {
                if ((i & 1) != 0) System.out.print(src.get(src.size() - i - (src.size() & 1)) + " ");
            }
        } catch (Exception ex) {
        }
    }
}
