
/**
 * TwoProbeChainHT class
 * Defined as: making each place in the array store a collection of all the elements that have that hash.
 * Stores a linked list of all elements hashed to that location
 *  at every array position to resolve collisions.
 *
 * @author Ian Skelskey
 * @version 1.0
 */
import java.util.LinkedList;

public class TwoProbeChainHT<Key, Value> implements SymbolTable<Key, Value> {

    private int M; // hash table size
    private int N; // number of key-value pairs

    private LinkedList<Entry>[] pairs;

    TwoProbeChainHT() {
        this(997); // large prime number
    }

    TwoProbeChainHT(int M) {
        this.M = M;
        this.N = 0;

        this.pairs = (LinkedList<Entry>[]) new LinkedList[M];

        for (int i = 0; i < M; i++) {
            this.pairs[i] = new LinkedList<>();
        }

    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }

    // put key-value pair into the table
    @Override
    public void put(Key key, Value val) {

        for (int i = 0; i < pairs[hash(key)].size(); i++) {
            if (pairs[hash(key)].get(i).getKey().equals(key)) {
                pairs[hash(key)].get(i).setValue(val);
                return;
            }
        }

        for (int i = 0; i < pairs[hash2(key)].size(); i++) {
            if (pairs[hash2(key)].get(i).getKey().equals(key)) {
                pairs[hash2(key)].get(i).setValue(val);
                return;
            }
        }

        if (pairs[hash(key)].size() > pairs[hash2(key)].size()) {
            pairs[hash2(key)].add(new Entry(key, val));
        } else {
            pairs[hash(key)].add(new Entry(key, val));
        }

        N++;

    }

    // get value paired with key
    @Override
    public Value get(Key key) {
        for (int i = 0; i < pairs[hash(key)].size(); i++) {
            if (pairs[hash(key)].get(i).getKey().equals(key)) {
                return (Value) pairs[hash(key)].get(i).getValue();
            }
        }

        for (int i = 0; i < pairs[hash2(key)].size(); i++) {
            if (pairs[hash2(key)].get(i).getKey().equals(key)) {
                return (Value) pairs[hash2(key)].get(i).getValue();
            }
        }
        return null;
    }

    // remove key (and its value) from table
    @Override
    public void delete(Key key) {
        for (int i = 0; i < pairs[hash(key)].size(); i++) {
            if (pairs[hash(key)].get(i).getKey().equals(key)) {
                pairs[hash(key)].remove(i);
                N--;
            }

        }

        for (int i = 0; i < pairs[hash2(key)].size(); i++) {
            if (pairs[hash2(key)].get(i).equals(key)) {
                pairs[hash2(key)].remove(i);
                N--;
            }
        }
    }

    // is there a value paired with key?
    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // is the table empty?
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> keyRing = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < pairs[i].size(); j++) {
                if (pairs[i] != null) {
                    keyRing.add((Key) pairs[i].get(j).getKey());
                }
            }
        }
        return keyRing;
    }

    private class Entry<Key, Value> {

        private Key key;
        private Value value;

        public Entry(Key key, Value value) {
            if (value == null || key == null) {
                throw new IllegalArgumentException("value or key cannot be null");
            }

            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return this.key;
        }

        public Value getValue() {
            return this.value;
        }

        public void setValue(Value val) {
            this.value = val;
        }
    }

}
