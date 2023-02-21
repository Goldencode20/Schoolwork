import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

class SeqAccessE extends Exception {}
class SeqFullE extends Exception {}

// The general picture to keep in mind is the following:
//
// |-------------------------|
// | 4 5 6 _ _ _ _ _ _ 1 2 3 |
// |-------------------------|
//         /\        /\      /\
//        left      right  capacity
//
// We maintain two pointers: left and right. The left
// pointer starts at 0 and is incremented for insertions
// and decremented for deletions. The right pointer
// starts at the other end and behaves symmetrically.

/**
 * General rules:
 *   - please do not change any method signatures
 *   - please do not change the declarations of the instance variables
 *   - please do not change any of the given methods and do
 *     remove the parts of the methods that are given to you
 *   - only edit the parts marked with TODO
 */

class LRFiniteSequence<E> {
    private final Optional<E>[] elements;
    private final int capacity;
    private int left;
    private int right;
    private int size;

    @SuppressWarnings("unchecked")
    LRFiniteSequence(int capacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        this.capacity = capacity;
        left = 0;
        right = capacity - 1;
        size = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    private boolean sizeConsistent() {
        return size == left + (capacity - 1) - right;
    }

    /**
     * The method returns the element at the given index.
     * If the index is out of bounds, the SeqAccessE exception
     * is thrown.
     * If the index is in bounds but the element at the given
     * index is Optional.empty the SeqAccessE exception
     * is also thrown.
     */
    public E get (int index) throws SeqAccessE {
        if (index < 0 || index > this.getCapacity() - 1) {
            throw new SeqAccessE();
        } else if (!this.elements[index].isPresent()) {
            throw new SeqAccessE();
        }
        return this.elements[index].get();
        //return null; // TODO Check
    }

    /**
     * If the sequence is full, the method throws the SeqFullE exception.
     * Otherwise adds the element to the sequence adjusting all pointers
     * appropriately.
     */
    public void insertLeft (E elem) throws SeqFullE {
        // TODO
        if (this.isFull()) {
            throw new SeqFullE();
        }
        //Add element
        this.elements[left] = Optional.ofNullable(elem);
        this.left++;
        this.size++;
        assert sizeConsistent(); // do not remove this line
    }

    /**
     * Same contract as insertLeft
     */
    public void insertRight (E elem) throws SeqFullE {
        // TODO
        if (this.isFull()) {
            throw new SeqFullE();
        }
        //Add element
        elements[right] = Optional.ofNullable(elem);
        this.right--;
        this.size++;
        assert sizeConsistent();
    }

    /**
     * If the left pointer is out of bounds, throw the SeqAccessE exception
     * If the left pointer is in bounds but points to an element that
     *   is Optional.empty, also throw the SeqAccessE exception
     * Otherwise return the element that left points to adjusting all
     * instance variables appropriately
     */
    public E removeLeft () throws SeqAccessE {
        // TODO
        if (this.left != 0) {
            this.left--;
        }
        if (this.left < 0 || this.left > this.right) {
            throw new SeqAccessE();
        } else if (!this.elements[this.left].isPresent()) {
            throw new SeqAccessE();
        }
        E removed = this.elements[this.left].get();
        this.elements[this.left] = Optional.empty();
        this.size--;
        assert sizeConsistent(); // keep this line immediately before the return
        return removed; // TODO
    }

    /**
     * The contract is similar to removeLeft
     */
    public E removeRight () throws SeqAccessE {
        // TODO
        if (this.right != this.getCapacity() - 1) {
            this.right++;
        }
        if (this.right < this.left || this.right > this.getCapacity() - 1) {
            throw new SeqAccessE();
        } else if (!this.elements[this.right].isPresent()) {
            throw new SeqAccessE();
        }
        E removed = this.elements[this.right].get();
        this.elements[this.right] = Optional.empty();
        this.size--;
        assert sizeConsistent(); // keep this line immediately before the return
        return removed; // TODO
    }

    void insertLeftIfNotFull (E elem) {
        if (isFull()) return;
        try {
            insertLeft(elem);
        } catch (SeqFullE e) {
            throw new Error("Internal bug!");
        }
    }

    void insertRightIfNotFull (E elem) {
        if (isFull()) return;
        try {
            insertRight(elem);
        } catch (SeqFullE e) {
            throw new Error("Internal bug!");
        }
    }

    void removeLeftIfNotEmpty () {
        if (left == 0) return;
        try {
            removeLeft();
        }
        catch (SeqAccessE e) {
            throw new Error("Internal bug!");
        }
    }

    void removeRightIfNotEmpty () {
        if (right == capacity - 1) return;
        try {
            removeRight();
        }
        catch (SeqAccessE e) {
            throw new Error("Internal bug!");
        }
    }
}