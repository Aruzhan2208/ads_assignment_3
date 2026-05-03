import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {

    private Node root;
    private int size; 

    public class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node curr = root;
        Node parent = null;

        while (curr != null) {
            parent = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                curr = curr.left;
            } else if (cmp > 0) {
                curr = curr.right;
            } else {
                curr.value = value; 
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = new Node(key, value);
        else parent.right = new Node(key, value);
        size++;
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.value;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node curr = root;

        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            if (key.compareTo(curr.key) < 0) curr = curr.left;
            else curr = curr.right;
        }

        if (curr == null) return; 

        if (curr.left == null || curr.right == null) {
            Node newCurr = (curr.left == null) ? curr.right : curr.left;

            if (parent == null) root = newCurr;
            else if (curr == parent.left) parent.left = newCurr;
            else parent.right = newCurr;
            
            size--;
            return;
        }

        Node p = null;
        Node temp = curr.right;
        while (temp.left != null) {
            p = temp;
            temp = temp.left;
        }

        if (p != null) p.left = temp.right;
        else curr.right = temp.right;

        curr.key = temp.key;
        curr.value = temp.value;
        size--;
    }

    @Override
    public Iterator<Node> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            pushLeft(node.right); 
            return node;
        }
    }
}
