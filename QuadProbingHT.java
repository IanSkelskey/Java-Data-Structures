/**
 * TwoProbeChainHT class
 * Defined as: making each place in the array store a collection of all the elements that have that hash.
 * Stores a linked list of all elements hashed to that location 
 *  at every array position to resolve collisions.
 * @author Ian Skelskey
 * @version 1.0
 * @param <Key>
 * @param <Value>
*/

public class QuadProbingHT<Key, Value> extends LinearProbingHT<Key,Value> implements SymbolTable<Key,Value>{

  private int M;

  QuadProbingHT(){
    this(997);
  }

  QuadProbingHT(int M){
    super(M);
    this.M = M;
  }

  public int hash(Key key, int i) {
    return (((key.hashCode() & 0x7fffffff) + i * i) % M);
  }

}