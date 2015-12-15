package stepic.algs_csc_base_1.module_2.theme_1;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by whoosh on 11/10/15.
 */
public class K {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt(), i = 0, s = 0;
        int z = k;
        HashSet<Integer> set = new HashSet<>();
        while (k > 0) {
            k -= ++i;
            s += i;
            set.add(i);
        }
        set.remove(s - z);
        System.out.println(set.size());
        set.forEach(x -> System.out.print(x + " "));
    }
}
