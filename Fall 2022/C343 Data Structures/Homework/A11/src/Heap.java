import java.util.*;

public class Heap {
    /**
     * The heap is represented as an array as explained in class.
     * We want to get a node from its index and vice-versa. The
     * HashMap indices maps each node to its index. It must
     * be maintained consistently: in other words if the nodes
     * move in the array, their index must be updated.
     */
    private final List<Node> nodes;
    private final HashMap<Node,Integer> indices;
    private int size;

    Heap(Set<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.size = nodes.size();
        this.indices = new HashMap<>();
        for (int i = 0; i < size; i++)  indices.put(this.nodes.get(i), i);
        heapify();
    }

    /**
     * Assumes the array of nodes is initialized but the nodes are in no
     * particular order.
     *
     * As explained in class, the goal is to re-arrange the nodes so that
     * they form a proper min-heap where each is less than its children.
     */
    void heapify() {
        // TODO
    }

    boolean isEmpty() {
        return size == 0;
    }

    /**
     * The next few methods return the appropriate node if it is within bounds.
     * Otherwise, they return an Optional.empty
     */
    Optional<Node> getParent(Node n) {
        /*
        Optional returnMe = Optional.empty();
        if (!n.getPreviousEdge().isEmpty()) {
            returnMe = Optional.of(n.getPreviousEdge().get().source());
        }
        return returnMe;
        */
        if (indices.get(n) == 0) {
            return Optional.empty();
        }
        int parentLoc = indices.get(n);
        parentLoc = parentLoc / 2;
         return Optional.of(nodes.get(parentLoc));
    } // Should work

    Optional<Node> getLeftChild(Node n) {
        Optional<Node> returnMe = Optional.empty();
        int leftLoc = indices.get(n) * 2;
        if (leftLoc > size) { //TODO might need to be =>
            returnMe = Optional.of(nodes.get(leftLoc));
        }
        return returnMe; // TODO
    }

    Optional<Node> getRightChild(Node n) {
        Optional<Node> returnMe = Optional.empty();
        int rightLoc = indices.get(n) * 2;
        if (rightLoc > size) { //TODO might need to be =>
            returnMe = Optional.of(nodes.get(rightLoc));
        }
        return returnMe; // TODO
    }

    Optional<Node> getMinChild(Node n) {
        return null; // TODO
    }

    /**
     * Swaps the two given nodes in the array
     * of nodes making sure we also update their
     * indices.
     */
    void swap(Node n1, Node n2) {
        // TODO
    }

    /**
     * Recursively move this node down until the heap property
     * is established
     */
    void moveDown(Node n) {
        if (!getMinChild(n).isEmpty()){
            swap(n, getMinChild(n).get());
        }
        // TODO
    }

    /**
     * Recursively move this node up until the heap property
     * is established
     */
    void moveUp(Node n) {
        if (!getParent(n).isEmpty()){
            swap(getParent(n).get() ,n);
        }
    }

    void insert(Node n) {
        indices.put(n,size);
        nodes.set(size++, n);
        moveUp(n);
    }

    /**
     * Return the minimum node in the heap (which is at the root).
     * Re-arrange the heap appropriately
     */
    Node extractMin() {
        return null; // TODO
    }
}

