/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    // implement quadratic probing
    protected int findIndex(String key) {
      int j = 1;  // quadratic probing starts at 1 not 0
      int initial = hashFunction(key);
      int i = initial;
      int count = 0;
  
      while (table[i] != null && !table[i].equals(key)) {
        i = (initial + (j * j)) % tableSize();  // finds next index
        j++;  // increase the factor
        count++;
  
        if (getProbeCount() > tableSize() && count > tableSize()) {
            // cant find the key in the table size
            return -1;
        }
        incProbeCount();  // add to the search counter
      }
      incProbeCount();
      return i;
  }
  
}
