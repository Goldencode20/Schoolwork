import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

class CycleDetection extends GenericDFS {
    boolean hasCycle;

    /**
     *
     * One way to detect cycles in a graph is to keep a list of current
     * ancestors.
     *
     * When you enter a node, you add it to the list of current ancestors
     * before visiting its children; when you finish visiting the children you
     * remove the node from the current list of ancestors.
     *
     * While visiting the children, if you touch a node that is its own
     * ancestor then you have detected a cycle.
     *
     */

    CycleDetection(Hashtable<Node, List<Node>> neighbors, Node start) {
        super(neighbors);
        // TODO
        ArrayList<Node> ancestors = new ArrayList<>();
        setExitConsumer(node -> ancestors.remove(node));
        setTouchConsumer(node -> {if(hasCycle || ancestors.contains(node)) { hasCycle = true;}});
        setEnterConsumer(node -> ancestors.add(node));
        traverse(start);
    }
}
