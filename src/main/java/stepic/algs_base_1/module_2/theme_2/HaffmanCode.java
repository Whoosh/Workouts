package stepic.algs_base_1.module_2.theme_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by whoosh on 11/11/15.
 */

public class HaffmanCode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] src = scanner.nextLine().toCharArray();
        HashMap<Character, Integer> filter = new HashMap<>();
        for (Character c : src) {
            if (filter.containsKey(c)) {
                filter.put(c, filter.get(c) + 1);
            } else {
                filter.put(c, 1);
            }
        }
        BitTree bitTree = new BitTree();
        for (Map.Entry<Character, Integer> e : filter.entrySet()) {
            bitTree.insert(new Node(e.getValue(), e.getKey()));
        }

        for (; bitTree.size() != 1; ) {
            Node lMin = bitTree.min();
            Node rMin = bitTree.min();
            lMin.append("1");
            rMin.append("0");
            Node k = new Node(lMin.priority + rMin.priority);
            k.isLeaf = false;
            k.left = lMin;
            k.right = rMin;
            bitTree.insert(k);
        }

        Node min = bitTree.min();

        StringBuilder code = new StringBuilder();
        findSmt(min,code);
        StringBuilder result = new StringBuilder();
        if(filter.size() == 1){
            buthurt.put(src[0],"0");
        }
        for (char c : src) {
            result.append(buthurt.get(c));
        }
        System.out.println(filter.size()+" "+result.length());
        for (Map.Entry<Character, String> entry : buthurt.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
        System.out.print(result);
    }

    private static HashMap<Character, String> buthurt = new HashMap<>();

    private static String findSmt(Node min, StringBuilder code) {
        if (min == null) return "";
        if (min.isLeaf) {
            buthurt.put(min.val, code.toString());
        } else {
            code.append("1");
            findSmt(min.left, code);
            code.deleteCharAt(code.length() - 1);

            code.append("0");
            findSmt(min.right, code);
            code.deleteCharAt(code.length() - 1);
        }
        return "";
    }


    private static class Node implements Comparable<Node> {
        int priority;
        char val = '-';
        Node left;
        Node right;
        String code = "";
        boolean isLeaf = true;
        private static int id = 0;
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

        public void append(String v) {
            code += v;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (priority != node.priority) return false;
            return val == node.val;

        }

        @Override
        public int hashCode() {
            int result = priority;
            result = 31 * result + (int) val;
            return result;
        }

        @Override
        public int compareTo(Node o) {
            int s = priority - o.priority;
            int z = s == 0 ? val - o.val : s;
            return z == 0 ? ID - o.ID : z;
        }

        @Override
        public String toString() {
            return String.valueOf(priority);
        }
    }

    private static class BitTree {

        TreeSet<Node> map = new TreeSet<>();

        public int size() {
            return map.size();
        }

        public void insert(Node n) {
            map.add(n);
        }

        public Node min() {
            return map.pollFirst();
        }

        @Override
        public String toString() {
            return map.toString();
        }
    }
}
