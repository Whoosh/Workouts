package stepic.algs_base_1.module_2.theme_2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by whoosh on 11/11/15.
 */

public class HuffmanCode {

    private static HashMap<Character, String> mapOfCodes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] src = scanner.nextLine().toCharArray();
        HashMap<Character, Integer> filter = new HashMap<>();
        TreeSet<Node> weightTree = new TreeSet<>();

        for (Character c : src) {
            if (filter.containsKey(c)) filter.put(c, filter.get(c) + 1);
            else filter.put(c, 1);
        }

        weightTree.addAll(filter.entrySet().stream().map(e -> new Node(e.getValue(), e.getKey())).collect(Collectors.toList()));

        for (; weightTree.size() != 1; ) {
            Node lMin = weightTree.pollFirst();
            Node rMin = weightTree.pollFirst();
            Node k = new Node(lMin.priority + rMin.priority);
            k.isLeaf = false;
            k.left = lMin;
            k.right = rMin;
            weightTree.add(k);
        }

        Node rootNode = weightTree.pollFirst();
        fillMapOfCodes(rootNode, new StringBuilder());
        printResult(filter, src);
    }

    private static void printResult(HashMap<Character, Integer> filter, char[] src) {
        StringBuilder result = new StringBuilder();
        if (filter.size() == 1) mapOfCodes.put(src[0], "0");
        for (char c : src) result.append(mapOfCodes.get(c));
        System.out.println(filter.size() + " " + result.length());
        mapOfCodes.entrySet().stream().forEach(entry-> System.out.println(entry.getKey() + ": " + entry.getValue()));
        System.out.print(result);
    }

    private static void fillMapOfCodes(Node min, StringBuilder code) {
        if (min.isLeaf) {
            mapOfCodes.put(min.val, code.toString());
        } else {
            code.append("1");
            fillMapOfCodes(min.left, code);
            code.deleteCharAt(code.length() - 1);
            code.append("0");
            fillMapOfCodes(min.right, code);
            code.deleteCharAt(code.length() - 1);
        }
    }

    private static class Node implements Comparable<Node> {

        private static int id = 0;
        private int priority;
        private char val = '-';
        private Node left;
        private Node right;
        private boolean isLeaf = true;
        private int ID;

        public Node(int p, char val) {
            ID = ++id;
            priority = p;
            this.val = val;
        }

        public Node(int p) {
            ID = ++id;
            priority = p;
        }

        @Override
        public int compareTo(Node o) {
            int s = priority - o.priority;
            int z = s == 0 ? val - o.val : s;
            return z == 0 ? ID - o.ID : z;
        }
    }
}
