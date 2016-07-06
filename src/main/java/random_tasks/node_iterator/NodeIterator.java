package random_tasks.node_iterator;

public class NodeIterator {

    private Node node;
    private boolean hasNext;

    public NodeIterator(Node node) {
        this.node = node;
        hasNext = true;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Node next() {
        if (node.getLeft() != null) {
            return node = node.getLeft();
        }
        if (node.getRight() != null) {
            return node = node.getRight();
        }
        if (node.getParent() == null) {
            hasNext = false;
            return node;
        }
        while (node.getParent().getRight() == null || node != node.getParent().getLeft()) {
            node = node.getParent();
            if (node.getParent() == null) {
                hasNext = false;
                return node;
            }
        }
        return node = node.getParent().getRight();
    }

    private class Node {

        private Node parent;
        private Node left;
        private Node right;

        public Node getParent() {
            return parent;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

}
