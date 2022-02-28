
import java.util.LinkedList;
import java.util.Queue;

public class SkelskeyBSTST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {

    private Node root;

    private class Node {

        private final Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    
    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    // nonrecursive get implementation
    private Value getFast(Key key) {
        if (isEmpty()) {
            return null;
        }

        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        } // while

        return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // nonrecursive put implementation
    private void putFast(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 1);
        } else {
            Node x = root;
            Node parent = null;

            while (x != null) {
                parent = x;
                if (key.compareTo(x.key) < 0) {
                    x = x.left;
                } else if (key.compareTo(x.key) > 0) {
                    x = x.right;
                } else {
                    x.val = val;
                    return;
                }
            }

            x = root;
            while (x != null) {
                x.N = x.N + 1;
                if (key.compareTo(x.key) < 0) {
                    x = x.left;
                } else if (key.compareTo(x.key) > 0) {
                    x = x.right;
                }
            }

            Node newNode = new Node(key, val, 1);
            if (key.compareTo(parent.key) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

        }
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    // copied from floor and reversed
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return ceiling(x.right, key); // changed left to right
        }
        Node t = ceiling(x.left, key); // changed right to left
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    // deleteMin() from lecture copied and reversed.
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size(Key lo, Key hi) {
        // how many nodes exist with lo <= key <= hi
        // work from hi moving down through its children until we reach null or lo
        if (lo.compareTo(hi) > 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    
    public void balance() {
        LinkedList<Node> nodes = new LinkedList<Node>();
        orderFill(root, nodes);

        int n = nodes.size();
        root = balance(nodes, 0, n - 1);
        updateSize(root);
    }

    public Node balance(LinkedList<Node> nodes, int start, int stop) {
        if (start > stop) {
            return null;
        }

        int mid = (start + stop) / 2;
        if ((start + stop) % 2 == 1) {
            mid++;
        }

        Node midNode = nodes.get(mid);

        midNode.left = balance(nodes, start, mid - 1);
        midNode.right = balance(nodes, mid + 1, stop);

        return midNode;
    }

    public void orderFill(Node x, LinkedList<Node> nodes) {
        if (x == null) {
            return;
        }
        orderFill(x.left, nodes);
        nodes.add(x);
        
        orderFill(x.right, nodes);
    }

    
    public void updateSize(Node x) {
        if (x == null) {
            return;
        }

        x.N = size(x);
        updateSize(x.left);
        updateSize(x.right);
    }

    public void printLevel(Key key) {

        Queue<Node> q = new LinkedList<Node>();
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                break;
            }
        }

        if (x == null) 
            return;

        q.add(x);
        while (!q.isEmpty()) {
            x = q.poll();
            System.out.println(x.val);

            if (x.left != null) {
                q.add(x.left);
            }

            if (x.right != null) {
                q.add(x.right);
            }
        }

    }

}
