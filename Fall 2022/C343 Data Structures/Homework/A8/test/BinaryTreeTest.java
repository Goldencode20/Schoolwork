import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void insertBST() {
        Collection<Integer> elems;
        BinaryTree<Integer> btree, otree;

        elems = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        btree = BinaryTree.mkBalanced(elems);
        assertTrue(btree.isBalanced());
        assertEquals(4, btree.height());

        TreePrinter.print(btree);

        elems = Arrays.asList(1,2,3,4,5);
        btree = BinaryTree.mkBalanced(elems);
        otree = BinaryTree.mkBST(elems);

        TreePrinter.print(btree);
        TreePrinter.print(otree);
    }

    @Test
    void iter () {
        Collection<Integer> elems;
        BinaryTree<Integer> btree;

        elems = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        btree = BinaryTree.mkBalanced(elems);

        TreePrinter.print(btree);
        for (Iterator<Integer> iter = btree.preOrder(); iter.hasNext(); )
            System.out.print(iter.next() + " ");
        System.out.println();
        for (Iterator<Integer> iter = btree.inOrder(); iter.hasNext(); )
            System.out.print(iter.next() + " ");
        System.out.println();
        for (Iterator<Integer> iter = btree.postOrder(); iter.hasNext(); )
            System.out.print(iter.next() + " ");
        System.out.println();
        for (Iterator<Integer> iter = btree.breadthFirst(); iter.hasNext(); )
            System.out.print(iter.next() + " ");
    }

    @Test
    void deleteBST () {
        Collection<Integer> elems;
        BinaryTree<Integer> btree, stree;

        elems = Arrays.asList(8,2,6,4,5,7,12,11,9,10,1,14,13,3,15);
        btree = BinaryTree.mkBST(elems);
        TreePrinter.print(btree);

        stree = btree.deleteBST(6);
        TreePrinter.print(stree);

        stree = btree.deleteBST(11);
        TreePrinter.print(stree);

        stree = btree.deleteBST(8);
        TreePrinter.print(stree);
    }

    @Test
    void insertAVL () {
        Collection<Integer> elems;
        BinaryTree<Integer> btree;

        elems = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        btree = BinaryTree.mkAVL(elems);

        TreePrinter.print(btree);

        assertTrue(btree.isBalanced());

        Collection<Integer> elems1;
        BinaryTree<Integer> btree1;

        elems1 = Arrays.asList(15,13,11,9,7,5,3,1,2,4,6,8,10,12,14);
        btree1 = BinaryTree.mkAVL(elems1);

        TreePrinter.print(btree1);

        assertTrue(btree1.isBalanced());
    }

    @Test
    void deleteAVL () {
        Collection<Integer> elems;
        BinaryTree<Integer> btree;

        elems = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        btree = BinaryTree.mkAVL(elems);

        TreePrinter.print(btree);

        btree = btree.deleteAVL(1);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(3);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(2);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(7);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(4);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(6);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(13);
        TreePrinter.print(btree);

        btree = btree.deleteAVL(15);
        TreePrinter.print(btree);

        assertTrue(btree.isBalanced());

    }

    @Test
    void smallAVL() {
        Collection<Integer> elems;
        BinaryTree<Integer> btree;

        elems = Arrays.asList(1,2,3,4,5,6);
        btree = BinaryTree.mkAVL(elems);

        TreePrinter.print(btree);
        assertTrue(btree.isBalanced());
    }

    @Test
    void extractRightMost() {
        Collection<Integer> elems;
        BinaryTree<Integer> btree;

        elems = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        btree = BinaryTree.mkAVL(elems);

        TreePrinter.print(btree);
        try {
            System.out.println(btree.extractRightMost().leaf());
            TreePrinter.print(btree.extractRightMost().tree());
        } catch (EmptyTreeE e){
            System.out.println("Error");
        }

        elems = Arrays.asList(3,6,5,4,3,2,1);
        btree = BinaryTree.mkBST(elems);

        TreePrinter.print(btree);
        try {
            System.out.println(btree.extractRightMost().leaf());
            TreePrinter.print(btree.extractRightMost().tree());
        } catch (EmptyTreeE e){
            System.out.println("Error");
        }
    }
}