package leetcode.problems_100_200.problem_133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by whoosh on 3/29/16.
 */
public class CloneGraph {

    public static void main(String[] args) {
        UndirectedGraphNode zeroNode = new UndirectedGraphNode(0);
        UndirectedGraphNode oneNode = new UndirectedGraphNode(1);
        UndirectedGraphNode twoNode = new UndirectedGraphNode(2);
        UndirectedGraphNode treeNode = new UndirectedGraphNode(3);
        UndirectedGraphNode fourNode = new UndirectedGraphNode(4);
        UndirectedGraphNode fiveNode = new UndirectedGraphNode(5);
        zeroNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
            add(oneNode);
            add(fiveNode);
        }};
        oneNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
            add(twoNode);
            add(fiveNode);
        }};
        twoNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
            add(treeNode);
        }};
        treeNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
            add(fourNode);
            add(fourNode);
        }};
        fourNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
            add(fiveNode);
            add(fiveNode);
        }};
        fiveNode.neighbors = new ArrayList<UndirectedGraphNode>() {{
        }};
        cloneGraph(zeroNode);
    }

    private static HashMap<Integer, UndirectedGraphNode> map;
    private static HashMap<Integer, UndirectedGraphNode> f;

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return node;
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map = new HashMap<>();
        f = new HashMap<>();
        makeNewGraph(node, head);
        print(head);
        return head;
    }

    private static void makeNewGraph(UndirectedGraphNode oldNode, UndirectedGraphNode newNode) {
        if (map.containsKey(oldNode.label)) return;
        map.put(newNode.label, newNode);
        newNode.neighbors = new ArrayList<>();
        for (UndirectedGraphNode neighbor : oldNode.neighbors) {
            if (map.containsKey(neighbor.label)) {
                newNode.neighbors.add(map.get(neighbor.label));
            } else {
                if (!f.containsKey(neighbor.label)) {
                    UndirectedGraphNode n = new UndirectedGraphNode(neighbor.label);
                    newNode.neighbors.add(n);
                    f.put(n.label, n);
                } else newNode.neighbors.add(f.get(neighbor.label));
            }
        }
        for (int i = 0; i < newNode.neighbors.size(); i++) {
            makeNewGraph(oldNode.neighbors.get(i), newNode.neighbors.get(i));
        }
    }

    static void print(UndirectedGraphNode node) {
        if (mm.contains(node.label)) return;
        mm.add(node.label);
        System.out.print(node.label + " ");
        for (UndirectedGraphNode neighbor : node.neighbors) {
            System.out.print(neighbor.label + " ");
        }
        System.out.println();
        for (UndirectedGraphNode neighbor : node.neighbors) {
            print(neighbor);
        }
    }

    static HashSet<Integer> mm = new HashSet<>();

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
}
