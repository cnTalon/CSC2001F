/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends HashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { 
      super(); 
    }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { 
      super(size); 
    }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    // implements linear probing
    protected int findIndex(String key) {
        int count = 0;
        int i = hashFunction(key);
    
        while (table[i] != null && !table[i].equals(key)) {
            i = (i + 1) % tableSize();  // finds next index
            count++;
            incProbeCount();    // add another probe (aka search)

            if (getProbeCount() > tableSize() && count >= tableSize()) {
                // cant find the key in the table size
                return -1;
            }
        }
        incProbeCount();
        return i;
        
    }    
}
