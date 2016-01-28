package stepic.algs_mail_base_1.module_5;

import java.io.IOException;

/**
 * Created by whoosh on 1/27/16.
 */
public class DTree {
    public static void main(String[] args) throws IOException {
        int count = nextInt();
        int k = nextInt();
        int v = nextInt();
        NaiveTree naiveTree = new NaiveTree(k, v);
        IndexedTreap<Integer, Integer> treap = new IndexedTreap<>(k, v);
        for (int i = 1; i < count; i++) {
            k = nextInt();
            v = nextInt();
            naiveTree.add(k, v);
            treap = IndexedTreap.merge(treap, new IndexedTreap<>(k, v));
        }
        System.out.println(Math.abs(treap.deph(treap) - naiveTree.deph(naiveTree.root)) + 1);
    }

    public static class IndexedTreap<P extends Comparable<P>, V> {

        private final int size;
        private final P priority;
        private final V value;

        private final IndexedTreap<P, V> left;
        private final IndexedTreap<P, V> right;

        public IndexedTreap(P priority, V value) {
            this.size = 1;
            this.priority = priority;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public int deph(IndexedTreap<P, V> node) {
            if (node == null) return 0;
            int deph1 = deph(node.left);
            int deph2 = deph(node.right);
            if (deph1 < deph2) return deph2 + 1;
            return deph1 + 1;
        }

        public IndexedTreap(IndexedTreap<P, V> top, IndexedTreap<P, V> left, IndexedTreap<P, V> right) {
            this.size = 1 + getSize(left) + getSize(right);
            this.priority = top.priority;
            this.value = top.value;
            this.left = left;
            this.right = right;
        }

        public static <Y extends Comparable<Y>, C> int getSize(IndexedTreap<Y, C> treap) {
            return treap != null ? treap.size : 0;
        }

        public IndexedTreap<P, V> get(int i) {

            int index = getSize(this.left);

            int c = index - i;

            if (c == 0) {
                return this;
            } else if (c < 0) {
                return this.right != null ? this.right.get(i - index) : null;
            } else {
                return this.left != null ? this.left.get(i) : null;
            }

        }

        public IndexedTreap<P, V> put(int i, P priority, V value, boolean replace) {
            IndexedTreap<P, V> el = new IndexedTreap<P, V>(priority, value);
            Split<P, V> split = split(i, replace);
            return merge(merge(split.getLesser(), el), split.getGreater());
        }

        public V getValue() {
            return value;
        }

        public int size() {
            return getSize(this);
        }

        public static <P extends Comparable<P>, V> IndexedTreap<P, V> merge(IndexedTreap<P, V> less, IndexedTreap<P, V> greater) {
            if (less == null) return greater;
            if (greater == null) return less;

            int c = less.priority.compareTo(greater.priority);

            if (c >= 0) {
                return new IndexedTreap<P, V>(less, less.left, merge(less.right, greater));
            } else {
                return new IndexedTreap<P, V>(greater, merge(less, greater.left), greater.right);
            }
        }


        public Split<P, V> split(int i, boolean deleteEquals) {
            int index = getSize(this.left);
            int c = index - i;
            if (c == 0 && deleteEquals) {
                System.out.println("delete");
                return new Split<P, V>(this.left, this.right, this);
            }
            if (c <= 0) {
                Split<P, V> rightSplit = this.right != null ? this.right.split(i - index, deleteEquals) : null;
                if (rightSplit != null) {
                    return new Split<P, V>(new IndexedTreap<P, V>(this, this.left, rightSplit.getLesser()), rightSplit.getGreater(), rightSplit.getDeleted());
                } else {
                    return new Split<P, V>(new IndexedTreap<P, V>(this, this.left, null), null, null);
                }
            } else {
                Split<P, V> leftSplit = this.left != null ? this.left.split(i, deleteEquals) : null;
                if (leftSplit != null) {
                    return new Split<P, V>(leftSplit.getLesser(), new IndexedTreap<P, V>(this, leftSplit.getGreater(), this.right), leftSplit.getDeleted());
                } else {
                    return new Split<P, V>(null, new IndexedTreap<P, V>(this, null, this.right), null);
                }
            }
        }

        public final static class Split<P extends Comparable<P>, V> {

            private final IndexedTreap<P, V> lesser;
            private final IndexedTreap<P, V> greater;
            private final IndexedTreap<P, V> deleted;

            public Split(IndexedTreap<P, V> lesser, IndexedTreap<P, V> greater, IndexedTreap<P, V> deleted) {
                this.lesser = lesser;
                this.greater = greater;
                this.deleted = deleted;
            }

            public IndexedTreap<P, V> getLesser() {
                return lesser;
            }

            public IndexedTreap<P, V> getGreater() {
                return greater;
            }

            public IndexedTreap<P, V> getDeleted() {
                return deleted;
            }

        }
    }

    private static class NaiveTree {

        Node root;

        NaiveTree(int firstK, int firstV) {
            root = new Node(firstK, firstV);
        }

        public void add(int k, int v) {
            Node founded = root;
            Node prev;
            do {
                prev = founded;
                if (founded.val > v) founded = founded.left;
                else founded = founded.right;
            } while (founded != null);
            if (prev.val > v) prev.left = new Node(v, k);
            else prev.right = new Node(v, k);
        }

        public int deph(Node node) {
            if (node == null) return 0;
            int deph1 = deph(node.left);
            int deph2 = deph(node.right);
            if (deph1 < deph2) return deph2 + 1;
            return deph1 + 1;
        }
    }


    private static class Node {
        Node left;
        Node right;
        int val;
        int key;

        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }


        public Node(int key, int val, Node left, Node right) {
            this.left = left;
            this.right = right;
        }
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
