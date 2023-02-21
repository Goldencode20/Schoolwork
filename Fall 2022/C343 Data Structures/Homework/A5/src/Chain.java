import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a wrapper around our finite sequence implementation
 * that specializes the element type to a hashtable entry
 *
 * It delegates most methods to the finite sequence class
 */

public class Chain<K,V> implements Iterable<HEntry<K,V>> {
    private final FS<HEntry<K,V>> entries;

    // capacity will always be a prime number
    Chain () { entries = new FS<>(7); }

    // for debugging

    FS<HEntry<K,V>> getEntries () { return entries; }

    //

    int size () { return entries.size(); }

    boolean isEmpty () { return entries.isEmpty(); }

    /**
     * No entries with duplicate keys are allowed.
     *
     * If the given entry has a key that is already in the chain,
     * the entry is NOT inserted and the method returns false
     *
     * Otherwise, the entry is inserted to the left of the
     * sequence, and the method returns true
     */
    boolean insert (HEntry<K,V> e) {
        if (!this.containsKey(e.key())) {
            entries.insertLeft(e);
            return true;
        }
        return false;
    }

    /**
     * If the current chain does not have an entry with the given key,
     * throw the NoSuchElementException
     *
     * Otherwise, return the value corresponding to the given key
     */
    V get (K key) {
        if (!this.containsKey(key)){
            throw new NoSuchElementException();
        }

        V returnMe = null;
        for (HEntry<K,V> e : entries){
            if(e.key() == key){
                returnMe = e.value();
            }
        }
        return returnMe;
    }

    /**
     * Checks if the current chain has an entry with the given key
     */
    boolean containsKey (K key) {
        for (HEntry<K,V> e : entries){
            if (e.key() == key){
                return true;
            }
        }
        return false;
    }

    public Iterator<HEntry<K,V>> iterator () {
        return entries.iterator();
    }

    public String toString () {
        return entries.toString();
    }
}
