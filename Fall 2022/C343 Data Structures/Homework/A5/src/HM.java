/* Hash maps with chaining */

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

// ------------------------------------------------------------------------

/**
 * A hashmap with chaining
 *
 * The starter code is missing a few methods marked with TODO as usual.
 * Additionally, the given code has a SUBTLE BUG that was intentionally
 * introduced. The given tests will not pass unless you correct this bug
 *
 */

public class HM<K,V> {
    private int capacity;
    private int size;
    private Function<? super K, Integer> hfun;
    private Chain<K, V>[] chains;

    /**
     * Use a default hash function built upon Java's hashCode
     */
    HM(int capacity) {
        this(capacity, k -> k.hashCode() % capacity);
    }

    /**
     * Use the given hash function
     */
    @SuppressWarnings("unchecked")
    HM(int capacity, Function<? super K, Integer> hfun) {
        this.size = 0;
        this.capacity = capacity;
        this.hfun = hfun;
        chains = (Chain<K, V>[]) Array.newInstance(Chain.class, capacity);
        for (int i = 0; i < chains.length; i++){
            Chain<K,V> chain = new Chain<>();
            chains[i] = chain;
        }
    }

    // for debugging

    public Chain<K, V>[] getChains() { return chains; }

    //

    int size () { return size; }

    @SuppressWarnings("unchecked")
    void clear() {
        this.size = 0;
        chains = (Chain<K, V>[]) Array.newInstance(Chain.class, capacity);
        Arrays.fill(chains, new Chain<>());
    }

    /**
     * If the given key already occurs in the hash table, return false without inserting
     * anything
     *
     * Otherwise, insert the key-value entry in the appropriate chain and return true
     */
    boolean put(K key, V value) {
        if (this.containsKey(key)) {return false;}
        HEntry<K,V> kvPair = new HEntry<>(key, value);
        this.chains[hfun.apply(key)].insert(kvPair);
        this.size++;
        return true;
    }

    /**
     * Returns the value associated with the given key or throw a NoSuchElementException
     * if the given key does not occur in the hash table
     */
    V get(K key) {
        if (!this.containsKey(key)){
            throw new NoSuchElementException();
        }
        return chains[hfun.apply(key)].get(key);
    }

    /**
     * Checks if the hash table has an entry with the given key
     */
    boolean containsKey(K key) {
        for (Chain<K, V> k : chains){
            if (k.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method increases the capacity of the hash table to the next prime
     * after 2 * capacity
     *
     * It then iterates over all the current entries, and inserts them in the
     * larger table
     */
    @SuppressWarnings("unchecked")
    void rehash() {
        int newCapacity = BigInteger.valueOf(capacity* 2L).nextProbablePrime().intValue();
        // TODO
        capacity = newCapacity;
        ArrayList<HEntry<K,V>> holdTemp = new ArrayList<>();
        for (Chain<K,V> e : chains){
            Iterator temp = e.iterator();
            while (temp.hasNext()){
                HEntry<K,V> chain = (HEntry<K, V>) temp.next();
                holdTemp.add(chain);
            }
        }
        Chain<K,V>[] newChains = (Chain<K, V>[]) Array.newInstance(Chain.class, newCapacity);
        HM<K,V> newHM = new HM(newCapacity);
        for (int i = 0; i < newCapacity; i++){
            Chain<K,V> chain = new Chain<>();
            newChains[i] = chain;
        }
        for (HEntry<K, V> e : holdTemp){
            newHM.getChains()[newHM.hfun.apply(e.key())].insert(e);
            newHM.size++;
        }
        this.size = newHM.size;
        this.capacity = newHM.capacity;
        this.hfun = newHM.hfun;
        chains = newHM.chains;
    }
}

// ------------------------------------------------------------------------