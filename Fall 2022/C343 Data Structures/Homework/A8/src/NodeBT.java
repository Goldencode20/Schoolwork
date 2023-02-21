import com.sun.source.tree.Tree;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeBT<E extends Comparable<E>> extends BinaryTree<E> {
    private final E data;
    private final BinaryTree<E> left, right;
    private final int height;

    NodeBT (E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = Math.max(left.height(), right.height()) + 1;
    }

    /**
     * This method is used as a helper for the AVL methods. It is like a constructor
     * but it applies the necessary rotations if needed to make sure the tree
     * is balanced.
     *
     * There are three cases to consider:
     *
     * left.height > right.height + 1
     *
     *   the situation looks like this:
     *
     *                 data
     *              /        \
     *        left          right
     *       /     \
     *   left2     right2
     *
     *   We definitely must rotate the entire tree to the right
     *   We might have to rotate left to the left
     *
     * right.height > left.height + 1, which is symmetric
     *
     * otherwise we just build the tree without any rotations
     */
    static <E extends Comparable<E>> NodeBT<E> mkBalancedNodeBT (E data, BinaryTree<E> left, BinaryTree<E> right) { //TODO I need to account for when left.getRight it bigger than left.getLeft

         NodeBT<E> node = new NodeBT<>(data, left, right);
         try {
         NodeBT<E> newLeft = new NodeBT<>(left.getData(), left.getLeftBT(), left.getRightBT());
         if(!newLeft.isBalanced()){
         newLeft = newLeft.mayBeRotateRight();
         newLeft = newLeft.mayBeRotateLeft();
         if(!newLeft.isBalanced()){
         newLeft = mkBalancedNodeBT(newLeft.data, newLeft.left, newLeft.right);
         }
         }
         node = new NodeBT<>(node.data, newLeft, node.right);
         } catch (EmptyTreeE e) {
         //System.out.println("Error 1");
         }
         try {
         NodeBT<E> newRight = new NodeBT<>(right.getData(), right.getLeftBT(), right.getRightBT());
         if(!newRight.isBalanced()){
         newRight = newRight.mayBeRotateRight();
         newRight = newRight.mayBeRotateLeft();
         if(!newRight.isBalanced()){
         newRight = mkBalancedNodeBT(newRight.data, newRight.left, newRight.right);
         }
         }
         node = new NodeBT<>(node.data, node.left, newRight);
         } catch (EmptyTreeE e) {
         //System.out.println("Error 2");
         }
         if (!node.isBalanced()) {
         node = node.mayBeRotateRight();
         node = node.mayBeRotateLeft();
         if(!node.isBalanced()){
         node = mkBalancedNodeBT(node.data, node.left, node.right);
         }
         }
         //System.out.println(node.isBalanced());
         return node;

        /**
        NodeBT<E> node = new NodeBT<>(data, left, right);
        BinaryTree<E> newLeft = left;
        BinaryTree<E> newRight = right;
        if (node.height >= 4){
            //TODO check height diffrence then rotate
            if (left.height() > right.height() + 1) {

            }
            try {
                newLeft = mkBalancedNodeBT(left.getData(), left.getLeftBT(), left.getRightBT());
            } catch (EmptyTreeE e) { }
            try {
                newRight = mkBalancedNodeBT(right.getData(), right.getLeftBT(), right.getRightBT());
            } catch (EmptyTreeE e) {}
            node = new NodeBT<E>(data, newLeft, newRight);
        } else if (node.height() == 3) {
            node = node.mayBeRotateRight();
            node = node.mayBeRotateLeft();
        }

        return node;
         **/
    }

    // Access fields

    E getData () { return data; }
    BinaryTree<E> getLeftBT () { return left; }
    BinaryTree<E> getRightBT () { return right; }

    // Basic properties

    boolean isEmpty () { return false; }
    int height() { return height; }
    boolean isBalanced() { return Math.abs(left.height() - right.height()) < 2; }

    // Traversals that return lists

    /**
     * The next three methods return a list of the data at each node in preorder,
     * inorder, or postorder
     */
    List<E> preOrderList() {
        List<E> returnMe = new ArrayList<>();
        returnMe.add(this.data);
        for (E e : this.left.preOrderList()) {
            returnMe.add(e);
        }
        for (E e : this.right.preOrderList()) {
            returnMe.add(e);
        }
        return returnMe;
    }

    List<E> inOrderList() {
        List<E> returnMe = new ArrayList<>();
        for (E e : this.left.inOrderList()) {
            returnMe.add(e);
        }
        returnMe.add(this.data);
        for (E e : this.right.inOrderList()) {
            returnMe.add(e);
        }
        return returnMe;
    }

    List<E> postOrderList() {
        List<E> returnMe = new ArrayList<>();
        for (E e : this.left.postOrderList()) {
            returnMe.add(e);
        }
        for (E e : this.right.postOrderList()) {
            returnMe.add(e);
        }
        returnMe.add(this.data);
        return returnMe;
    }

    // Basic insert: always insert to the left but swaps the tree after every insert
    // to make sure the tree is balanced

    BinaryTree<E> insert (E elem) {
        return new NodeBT<>(data, right, left.insert(elem));
    }

    // Helpers for BST/AVL methods

    /**
     * Here is an example. Let the current tree be;
     *
     *              3
     *            /   \
     *           2     5
     *         /      /  \
     *        1      4    6
     *
     * the method returns a record
     *
     *              3
     *            /   \
     *           2     5
     *         /      /
     *        1      4
     *
     *
     * and the second component is the left 6
     *
     */
    TreeAndLeaf<E> extractRightMost () { //TODO why does this bring the deleted item back
            try {
                if(this.height() > 1){
                    TreeAndLeaf<E> temp = right.extractRightMost();
                    return new TreeAndLeaf<>(new NodeBT<E>(data, left, temp.tree()), temp.leaf());
                }
            } catch (EmptyTreeE e) { }
        return new TreeAndLeaf<E>(left, data);
    }

    /**
     * Exactly like the method above but ensure the tree is balanced
     */
    TreeAndLeaf<E> balancedExtractRightMost () {
        try {
            if(this.height() > 1){
                TreeAndLeaf<E> temp = right.extractRightMost();
                return new TreeAndLeaf<>(mkBalancedNodeBT(data, left, temp.tree()), temp.leaf());
            }
        } catch (EmptyTreeE e) { }
        return new TreeAndLeaf<E>(left, data);
    }

    NodeBT<E> mayBeRotateLeft () {
        if (left.height() < right.height()) return rotateLeft();
        else return this;
    }

    NodeBT<E> mayBeRotateRight () {
        if (right.height() < left.height()) return rotateRight();
        else return this;
    }

    /**
     * Here is an example. If the current tree is:
     *
     *                5
     *             /    \
     *           2       8
     *                 /  \
     *                6    9
     *
     * we return
     *
     *                8
     *             /    \
     *           5       9
     *         /  \
     *       2     6
     *
     */
    //TODO I might have left and right backwards
    NodeBT<E> rotateRight (){
        try {
            NodeBT newRight = new NodeBT(data, left.getRightBT(), right);
            return new NodeBT<>(left.getData(), left.getLeftBT(), newRight);
        } catch (EmptyTreeE e) {
            throw new Error();
        }
    }

    /**
     * Symmetric to the method above
     */
    NodeBT<E> rotateLeft () {
        try {
            NodeBT newLeft = new NodeBT(data, left, right.getLeftBT());
            return new NodeBT<>(right.getData(), newLeft, right.getRightBT());
        } catch (EmptyTreeE e) {
            throw new Error();
        }
    }

    // BST insertions, lookups, and deletions

    public BinaryTree<E> insertBST(E elem) {
        if (elem.compareTo(data) < 0)
            return new NodeBT<>(data, left.insertBST(elem), right);
        else return new NodeBT<>(data, left, right.insertBST(elem));
    }

    public boolean findBST(E elem) {
        int comp = elem.compareTo(data);
        if (comp < 0) return left.findBST(elem);
        else if (comp > 0) return right.findBST(elem);
        else return true;
    }

    public BinaryTree<E> deleteBST (E elem) {
        int comp = elem.compareTo(data);
        if (comp < 0) return new NodeBT<>(data,left.deleteBST(elem),right);
        else if (comp > 0) return new NodeBT<>(data,left,right.deleteBST(elem));
        else {
            try {
                TreeAndLeaf<E> treeLeaf = left.extractRightMost();
                return new NodeBT<>(treeLeaf.leaf(), treeLeaf.tree(), right);
            }
            catch (EmptyTreeE e) { return right; }
        }
    }

    // AVL insertions, lookups, and deletions

    /**
     * The following methods are similar to the BST variants but must
     * ensure that the trees are always balanced
     *
     */
    public BinaryTree<E> insertAVL(E elem) {
        BinaryTree<E> temp;
        if (elem.compareTo(data) < 0) {
            BinaryTree<E> newLeft = left.insertAVL(elem);
            temp = mkBalancedNodeBT(data, newLeft, right);
        } else {
            BinaryTree<E> newRight = right.insertAVL(elem);
            temp = mkBalancedNodeBT(data, left, newRight);
        }
        return temp;
    }

    public boolean findAVL (E elem) {
        return findBST(elem);
    }

    public BinaryTree<E> deleteAVL (E elem) { //TODO I need to check
        int comp = elem.compareTo(data);
        if (comp < 0) return mkBalancedNodeBT(data,left.deleteAVL(elem),right);
        else if (comp > 0) return mkBalancedNodeBT(data,left,right.deleteAVL(elem));
        else {
            try {
                TreeAndLeaf<E> treeLeaf = left.extractRightMost();
                return mkBalancedNodeBT(treeLeaf.leaf(), treeLeaf.tree(), right);
            }
            catch (EmptyTreeE e) { return right; }
        }
    }

    // Printable interface

    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(data); }

}