import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		/* Matrices
		 * 
		 */
	    int[][] data1 = new int[0][0];
	    int[][] data2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
	    int[][] data3 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
	    int[][] data4 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
	    int[][] data5 = { { 1, 4, 7 }, { 2, 5, 8 } };

	    SkelskeyMatrix m1 = new SkelskeyMatrix(data1);
	    SkelskeyMatrix m2 = new SkelskeyMatrix(data2);
	    SkelskeyMatrix m3 = new SkelskeyMatrix(data3);
	    SkelskeyMatrix m4 = new SkelskeyMatrix(data4);
	    SkelskeyMatrix m5 = new SkelskeyMatrix(data5);

	    System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
	    System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
	    System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

	    // check for reference issues
	    System.out.println("m2 -->\n" + m2);
	    data2[1][1] = 101;
	    System.out.println("m2 -->\n" + m2);

	    // test equals
	    System.out.println("m2==null: " + m2.equals(null)); // false
	    System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX")); // false
	    System.out.println("m2==m1: " + m2.equals(m1)); // false
	    System.out.println("m2==m2: " + m2.equals(m2)); // true
	    System.out.println("m2==m3: " + m2.equals(m3)); // false
	    System.out.println("m3==m4: " + m3.equals(m4)); // true

	    // test operations (valid)
	    System.out.println("m1 + m1:\n" + m1.plus(m1));
	    System.out.println("2 * m2:\n" + m2.scale(2));
	    System.out.println("m2 + m3:\n" + m2.plus(m3));
	    System.out.println("m2 - m3:\n" + m2.minus(m3));
	    System.out.println("3 * m5:\n" + m5.scale(3));

	    // not tested... multiply(). you know what to do.
	    System.out.println("m2 * m3:\n" + m2.multiply(m3));

	    // test operations (invalid)
	    // System.out.println("m1 + m2" + m1.plus(m2));
	    // System.out.println("m1 + m5" + m1.plus(m5));
	    // System.out.println("m1 - m2" + m1.minus(m2));
	    
	    /* Deques
	     * 
	     */
	    SkelskeyDeque<Integer> deque = new SkelskeyDeque<>();

	    // standard queue behavior
	    deque.enqueueBack(3);
	    deque.enqueueBack(7);
	    deque.enqueueBack(4);
	    deque.dequeueFront();
	    deque.enqueueBack(9);
	    deque.enqueueBack(8);
	    deque.dequeueFront();
	    System.out.println("size: " + deque.size());
	    System.out.println("contents:\n" + deque.toString());

	    // deque features
	    System.out.println(deque.dequeueFront());
	    deque.enqueueFront(1);
	    deque.enqueueFront(11);
	    deque.enqueueFront(3);
	    deque.enqueueFront(5);
	    System.out.println(deque.dequeueBack());
	    System.out.println(deque.dequeueBack());
	    System.out.println(deque.last());
	    deque.dequeueFront();
	    deque.dequeueFront();
	    System.out.println(deque.first());
	    System.out.println("size: " + deque.size());
	    System.out.println("contents:\n" + deque.toString());
	    
	    /* BSTST
	     * 
	     */
        SkelskeyBSTST<Integer, String> bst = new SkelskeyBSTST();

        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");

        System.out.println("Before balance:");
        bst.printLevel(10); // root

        System.out.println("After balance:");
        bst.balance();
        bst.printLevel(5); // root
        
	    /* HashTables
	     * 
	     */
        System.out.println("TwoProbeChainHT: ");
        testIntegers(new TwoProbeChainHT<Integer, Integer>());
        testStrings(new TwoProbeChainHT<String, Integer>());

        System.out.println("GeneralProbingHT: ");
        testIntegers(new LinearProbingHT<Integer, Integer>());
        testStrings(new LinearProbingHT<String, Integer>());
        
        System.out.println("QuadProbingHT: ");
        testIntegers(new QuadProbingHT<Integer, Integer>());
        testStrings(new QuadProbingHT<String, Integer>());
	}

	
	
    /**
     * Test integer operations on symbol table implementation. No JUnit; ugly.
     * 
     * @param st An object implementing a symbol table.
     */
    public static void testIntegers(SymbolTable<Integer, Integer> st) {
        System.out.println("*INTEGER TESTING*");
        
        System.out.println("  Testing creation and basic methods... ");
        
        //populate initial symbol table.
        Set<Integer> keys = new HashSet<>(Arrays.asList(-42341145, -72, -91, -45, -43, 0, 34, 2, 71, 48, 38334343));
        st.put(-42341145, 58);
        st.put(-72, 2);
        st.put(-91, 36);
        st.put(-45, 90);
        st.put(-43, 51);
        st.put(0, 4);
        st.put(34, 3);
        st.put(2, 96);
        st.put(71, 19);
        st.put(48, 42);
        st.put(38334343, 92);       

        assert(!st.isEmpty())           : "symbol table is empty after inserting elemetns";
        assert(st.size() == 11)         : "does not contain correct number of elements";
        assert(st.contains(-42341145))  : "added key -42341145 does not exist";
        assert(st.contains(0))          : "added key 0 does not exist" ;
        assert(st.contains(38334343))   : "added key 38334343 does not exist";
        assert(!st.contains(-62341145)) : "contains unknown key -62341145";
        assert(!st.contains(-1))        : "contains unknown key -1";
        assert(!st.contains(58334343))  : "contains unknown key -58334343";

        Set<Integer> stKeys = new HashSet<>();
        for(Integer i : st.keys())
            stKeys.add(i);
        assert(stKeys.equals(keys))     : "keys do not match expected";

        //note: the following code does not check if keys is maintained properl- it should.
        
        System.out.println("  Testing put()... ");
        
        //add new key
        int size = st.size();
        st.put(99, 42);
        assert(st.size() == size + 1)   : "size did not update.";
        assert(st.contains(99))         : "does not contain new key";
        assert(st.get(99) == 42)        : "does not return correct value";
        
        //update existing key
        size = st.size();
        st.put(-72, 2);
        assert(st.size() == size)       : "size changed";
        assert(st.contains(-72))        : "does not contain updated key";
        assert(st.get(-72) == 2)        : "does not return updated value";
                         
            
        System.out.println("  Testing get... ");
        
        //get key not there
        size = st.size();
        Integer ret = st.get(10);
        assert(ret == null)             : "returned non-null for key that doesn't exist";
        assert(st.size() == size)       : "size changed";        
        assert(!st.contains(10))        : "a key that doesn't exist appeared after get'ing it";

        //get key there (2, 96)
        size = st.size();
        ret = st.get(2);
        assert(ret == 96)               : "returned incorrect value for key";
        assert(st.size() == size)       : "size changed";        
        assert(st.contains(2))          : "key vanished after get'ing it";
        
        
        System.out.println("  Testing delete... ");
        
        //delete key not there
        size = st.size();
        st.delete(49);
        assert(st.get(49) == null)      : "returned non-null for key that was deleted";
        assert(st.size() == size)       : "size changed";        
        assert(!st.contains(49))        : "a missing key is contained after it was deleted";

        //delete key there  (48, 42)
        size = st.size();
        st.delete(48);
        assert(st.get(48) == null)      : "returned non-null for key that was deleted";
        assert(st.size() == size - 1)   : "size did not update";        
        assert(!st.contains(48))        : "a deleted key is still contained";
        
        System.out.println("  DONE\n");
    }
    
    
    
    /**
     * Test string operations on symbol table implementation. No JUnit; ugly.
     * 
     * @param st An object implementing a symbol table.
     */
    public static void testStrings(SymbolTable<String, Integer> st) {
        
        System.out.println("*STRING TESTING*");
        System.out.println("  Testing creation and basic methods... ");
        
        //populate initial symbol table.
        Set<String> keys = new HashSet<>(Arrays.asList("DFKDJSFS", "DAFDW", "XZC", "adsfas", "a", "B", "112323", "<Object>", "AAAA", "A"));
        st.put("DFKDJSFS", 21);
        st.put("DAFDW", 52);
        st.put("XZC", 5);
        st.put("adsfas", 8);
        st.put("a", 58);
        st.put("B", 0);
        st.put("112323", 84);
        st.put("<Object>", 743564);
        st.put("AAAA", 7);
        st.put("A", 1);
        
        assert(!st.isEmpty())           : "symbol table is empty after inserting elemetns";
        assert(st.size() == 10)         : "does not contain correct number of elements";
        assert(st.contains("112323"))  : "added key -42341145 does not exist";
        assert(st.contains("a"))          : "added key 0 does not exist" ;
        assert(st.contains("DFKDJSFS"))   : "added key 38334343 does not exist";
        assert(!st.contains("b")) : "contains unknown key -62341145";
        assert(!st.contains("AA"))        : "contains unknown key -1";
        assert(!st.contains("FDFDSFSFDSFDS"))  : "contains unknown key -58334343";

        Set<String> stKeys = new HashSet<>();
        for(String i : st.keys())
            stKeys.add(i);
        assert(stKeys.equals(keys))     : "keys do not match expected";

        //note: the following code does not check if keys is maintained properl- it should.
        
        System.out.println("  Testing put()... ");
        
        //add new key
        int size = st.size();
        st.put("TEST", 42);
        assert(st.size() == size + 1)   : "size did not update.";
        assert(st.contains("TEST"))         : "does not contain new key";
        assert(st.get("TEST") == 42)        : "does not return correct value";
        
        //update existing key
        size = st.size();
        st.put("AAAA", 2);
        assert(st.size() == size)       : "size changed";
        assert(st.contains("AAAA"))     : "does not contain updated key";
        assert(st.get("AAAA") == 2)     : "does not return updated value";
                         
            
        System.out.println("  Testing get... ");
        
        //get key not there
        size = st.size();
        Integer ret = st.get("TEST2");
        assert(ret == null)             : "returned non-null for key that doesn't exist";
        assert(st.size() == size)       : "size changed";        
        assert(!st.contains("TEST2"))   : "a key that doesn't exist appeared after get'ing it";

        //get key there ("DAFDW", 52)
        size = st.size();
        ret = st.get("DAFDW");
        assert(ret == 52)               : "returned incorrect value for key";
        assert(st.size() == size)       : "size changed";        
        assert(st.contains("DAFDW"))          : "key vanished after get'ing it";
        
        
        System.out.println("  Testing delete... ");
        
        //delete key not there
        size = st.size();
        st.delete("<Object>ZZZ");
        assert(st.get("<Object>ZZZ") == null)   : "returned non-null for key that was deleted";
        assert(st.size() == size)               : "size changed";        
        assert(!st.contains("<Object>ZZZ"))     : "a missing key is contained after it was deleted";

        //delete key there  ("<Object>", 743564)
        size = st.size();
        st.delete("<Object>");
        assert(st.get("<Object>") == null)      : "returned non-null for key that was deleted";
        assert(st.size() == size - 1)           : "size did not update";        
        assert(!st.contains("<Object>"))        : "a deleted key is still contained";
        
        System.out.println("  DONE\n");
    }
}
