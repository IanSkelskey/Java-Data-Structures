import java.util.LinkedList;

/**
 * LinearProbingHT class
 * Defined as: hashing to an occupied index, looking for the next available space.
 * @author Ian Skelskey
 * @version 1.0
 * @param <Key>
 * @param <Value>
*/

public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {

  // Member variables
  private int M; // hash table size
  private int N; // number of key-value pairs

  private Entry<Key,Value>[] pairs; // array for key-value pairs

  LinearProbingHT() {
    this(997);
  }

  LinearProbingHT(int size){
    this.M = size;
    this.N = 0;

    this.pairs = new Entry[M];
  }

  public int hash(Key key){
    return (key.hashCode() & 0x7fffffff) % M;
  }

  // put key-value pair into the table
  @Override
  public void put(Key key, Value val) {
    for(int i = hash(key); pairs[i] != null; i = (i + 1) % M){
      if(pairs[i].getKey().equals(key)){
        pairs[i].setValue(val);
        return;
      }
    pairs[i] = new Entry<>(key,val);
    N++;
    }
  }

  // get value paired with key
  @Override
  public Value get(Key key) {
    for(int i = hash(key); pairs[i] != null; i = (i + 1) % M){
      if(pairs[i].getKey().equals(key)){
        return pairs[i].getValue();
      }
    }
    return null;
  }

  // remove key (and its value) from table
  @Override
  public void delete(Key key) {
    for(int i = hash(key); pairs[i] != null; i = (i + 1) % M){
      if(pairs[i].getKey().equals(key)){
        pairs[i].setValue(null);
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

  // number of key-value pairs

  @Override
  public int size() {
    return N;
  }

  // all keys in the table
  @Override
  public Iterable<Key> keys() {
    LinkedList<Key> keyRing = new LinkedList<>();
    for(int i = 0; i < M; i++){
      if(pairs[i] != null)
        keyRing.add(pairs[i].getKey());
    }
    return keyRing;
  }

  private class Entry<Key, Value>{
  private Key key;
  private Value value;

  public Entry(Key key, Value value){
    if (value == null || key == null)
      throw new IllegalArgumentException("value or key cannot be null");

    this.key = key;
    this.value = value;
  }

  public Key getKey(){
    return this.key;
  }

  public Value getValue(){
    return this.value;
  }

  public void setValue(Value val){
    this.value = val;
  }
}
  
}

