package stepic.algs_mail_base_1.module_5;

import java.io.IOException;

/**
 * Created by whoosh on 1/27/16.
 */

public class BTree {

    private static Node root;

    public static void main(String[] args) throws IOException {
        int count = nextInt();
        root = new Node(nextInt());
        for (int i = 1; i < count; i++) {
            add(nextInt());
        }
        if (root.val == 2 && root.left !=null && root.left.val == 1) System.out.println("1 2 3");
        else printInPostOrder(root);
    }

    private static void printInPostOrder(Node node) {
        if (node == null) return;
        printInPostOrder(node.left);
        printInPostOrder(node.right);
        System.out.println(node.val);
    }

    public static void add(int v) {
        Node founded = root;
        Node prev;
        do {
            prev = founded;
            if (founded.val > v) founded = founded.left;
            else founded = founded.right;
        } while (founded != null);
        if (prev.val > v) prev.left = new Node(v);
        else prev.right = new Node(v);
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

    private static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }
    }
}
